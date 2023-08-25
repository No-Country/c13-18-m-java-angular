package com.grupo18.nocountry.greenpoint.pointsystem;

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
    private final ModelMapper mapper;


    @Override
    public RecycleResponse recycle(RecycleRequest request) throws Exception{
        List<Recyclable> recyclables = new ArrayList<>();
        int totalPoints = 0;
        for (RecyclableRequest r : request.getRecyclables()) {
            Recyclable recyclable = recyclableRepository.findById(r.getRecyclableId()).orElseThrow(
                    ()-> new Exception("El reciclable no existe")
            );
            totalPoints += recyclable.getPoints()*Math.round((double)r.getGrams()/100);
            recyclables.add(recyclable);
        }
        RecyclableDetails details = RecyclableDetails
                .builder()
                .code(RandomString.make())
                .recyclables(recyclables)
                .totalPoints(totalPoints)
                .build();

        detailsRepository.save(details);

        return mapper.map(details,RecycleResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void redeem(RedeemRequest request) throws Exception{
        RecyclableDetails details = detailsRepository.findByCodeAndRedeemedFalse(request.getCode()).orElseThrow(
                ()-> new Exception("El código es inválido.")
        );
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                ()-> new Exception("El usuario con el id "+ request.getUserId() +" no existe."));
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
        List<RecyclingTransaction> transactions = transactionRepository.findAllByUserId(id);
        List<TransactionHistory> transactionHistories = new ArrayList<>();
        if(!transactions.isEmpty()){
        for (RecyclingTransaction transaction : transactions) {
        RecyclableDetails details = transaction.getRecyclableDetails();
               transactionHistories.add(TransactionHistory
                       .builder()
                       .user(mapper.map(transaction.getUser(), UserResponse.class))
                       .recycledItems(details.getRecyclables().size())
                       .pointsEarned(details.getTotalPoints())
                       .timestamp(transaction.getTimestamp())
                       .build());
        }
        }

        return new PageImpl<>(transactionHistories,pageable,transactionHistories.size());
    }
    @Override
    public Page<TransactionHistory> getAllTransactions(Pageable pageable){
        List<TransactionHistory> transactionHistories = new ArrayList<>();
            for (RecyclingTransaction transaction : transactionRepository.findAll(pageable)) {
                RecyclableDetails details = transaction.getRecyclableDetails();
                transactionHistories.add(TransactionHistory
                        .builder()
                        .user(mapper.map(transaction.getUser(), UserResponse.class))
                        .recycledItems(details.getRecyclables().size())
                        .pointsEarned(details.getTotalPoints())
                        .timestamp(transaction.getTimestamp())
                        .build());
            }


        return new PageImpl<>(transactionHistories,pageable,transactionHistories.size());
    }






}
