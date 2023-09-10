package com.grupo18.nocountry.greenpoint.pointsystem;

import com.grupo18.nocountry.greenpoint.exceptions.IdNotFoundException;
import com.grupo18.nocountry.greenpoint.exceptions.InvalidRecycleCode;
import com.grupo18.nocountry.greenpoint.exceptions.RecyclableNotFound;
import com.grupo18.nocountry.greenpoint.recyclable.Recyclable;
import com.grupo18.nocountry.greenpoint.recyclable.RecyclableRepository;
import com.grupo18.nocountry.greenpoint.user.User;
import com.grupo18.nocountry.greenpoint.user.UserRepository;
import com.grupo18.nocountry.greenpoint.user.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointSystemServiceImpl implements PointSystemService{

    private final RecyclableDetailsRepository detailsRepository;
    private final RecyclingTransactionRepository transactionRepository;
    private final RecyclableRepository recyclableRepository;
    private final UserRepository userRepository;
    private final RecycledItemRepository recycledItemRepository;
    private final ModelMapper mapper;


    @Override
    public RecycleResponse recycle(RecycleRequest request) {
        List<RecycledItem> recycledItems = new ArrayList<>();
        int totalPoints = 0;
        RecyclableDetails details = RecyclableDetails
                .builder()
                .code(RandomString.make())
                .build();
        for (RecyclableRequest r : request.getRecyclables()) {
            Recyclable recyclable = recyclableRepository.findById(r.getRecyclableId()).orElseThrow(
                    ()-> new RecyclableNotFound("El reciclable con el id" + r.getRecyclableId() + " no existe.")
            );
            totalPoints += recyclable.getPoints()*Math.round((double)r.getGrams()/100);
            recycledItems.add(RecycledItem.builder()
                            .grams(r.getGrams())
                            .recyclable(recyclable)
                            .recyclableDetails(details)
                            .build());
            log.info(recyclable.toString());
        }

        details.setRecycledItems(recycledItems);
        details.setTotalPoints(totalPoints);

        detailsRepository.save(details);

        return mapper.map(details,RecycleResponse.class);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void redeem(RedeemRequest request) {
        RecyclableDetails details = detailsRepository.findByCodeAndRedeemedFalse(request.getCode()).orElseThrow(
                ()-> new InvalidRecycleCode("El código es inválido.")
        );
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                ()-> new IdNotFoundException("El usuario con el id "+ request.getUserId() +" no existe."));
        details.setRedeemed(true);
        detailsRepository.save(details);
        user.setPoints(user.getPoints()+details.getTotalPoints());
        userRepository.save(user);

        transactionRepository.save(RecyclingTransaction
                .builder()
                .recyclableDetails(details)
                .user(user)
                .build());

    }


    @Override
    public Page<TransactionHistory> getUserTransactionHistory(Pageable pageable, Long id){
        Page<RecyclingTransaction> transactions = transactionRepository.findAllByUserId(pageable,id);
        List<TransactionHistory> transactionHistories = new ArrayList<>();
        if(!transactions.isEmpty()){
        for (RecyclingTransaction transaction : transactions) {
        RecyclableDetails details = transaction.getRecyclableDetails();
               transactionHistories.add(TransactionHistory
                       .builder()
                       .user(mapper.map(transaction.getUser(), UserResponse.class))
                       .totalWeight(details.getTotalWeight())
                       .pointsEarned(details.getTotalPoints())
                       .timestamp(transaction.getTimestamp())
                       .build());
        }
        }

        return new PageImpl<>(transactionHistories,pageable,transactions.getTotalElements());
    }
    @Override
    public Page<TransactionHistory> getAllTransactions(Pageable pageable){
        Page<RecyclingTransaction> transactions =  transactionRepository.findAll(pageable);
        List<TransactionHistory> transactionHistories = new ArrayList<>();
            for (RecyclingTransaction transaction : transactions) {
                RecyclableDetails details = transaction.getRecyclableDetails();
                transactionHistories.add(TransactionHistory
                        .builder()
                        .user(mapper.map(transaction.getUser(), UserResponse.class))
                        .totalWeight(details.getTotalWeight())
                        .pointsEarned(details.getTotalPoints())
                        .timestamp(transaction.getTimestamp())
                        .build());
            }


        return new PageImpl<>(transactionHistories,pageable,transactions.getTotalElements());
    }

    @Override
    public DetailsResponseDTO getDetailsByCode(String code) {
        RecyclableDetails details = detailsRepository.findByCodeAndRedeemedFalse(code).orElseThrow(
                ()->new InvalidRecycleCode("El código es inválido o ya ha sido canjeado.")
        );
        List<RecycledItemDTO> recycledItemDTOS = new ArrayList<>();
        List<RecycledItem> recycledItems = recycledItemRepository.findAllByRecyclableDetailsId(details.getId());
        for (RecycledItem ri : recycledItems) {
            recycledItemDTOS.add(RecycledItemDTO
                    .builder()
                            .recyclableType(ri.getRecyclable().getRecyclableType())
                            .pointsEarned(ri.getRecyclable().getPoints()*(ri.getGrams()/100))
                            .totalGrams(ri.getGrams())


                    .build());
        }

        return DetailsResponseDTO.builder()
                .recycledItems(recycledItemDTOS)
                .totalPoints(details.getTotalPoints())
                .build();
    }


}
