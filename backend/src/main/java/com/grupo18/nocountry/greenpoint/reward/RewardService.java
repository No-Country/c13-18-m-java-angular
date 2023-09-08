package com.grupo18.nocountry.greenpoint.reward;

import com.grupo18.nocountry.greenpoint.inventory.Inventory;
import com.grupo18.nocountry.greenpoint.inventory.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final RewardRepository rewardRepository;
    private final InventoryRepository inventoryRepository;

    public Optional<Reward> getById(Long id) {
        return rewardRepository.findById(id);
    }

    public List<Reward> findAll() {
        return rewardRepository.findAll();
    }

    public List<Reward> findAllWithName(String productName) {
        return rewardRepository.findByName(productName);
    }

    public void save (RewardDTO rewardDTO) {

        Inventory inventory = Inventory.builder().build();
        inventoryRepository.save(inventory);
        rewardRepository.save(Reward.builder()
                .name(rewardDTO.getName())
                .price(rewardDTO.getPrice())
                .description(rewardDTO.getDescription())
                .photo(rewardDTO.getPhoto())
                .inventory(inventory)
                .build());
    }

    public Reward update (RewardDTO rewardDTO, Long id){
        Optional<Reward> productOptional = rewardRepository.findById(id);

        if (productOptional.isPresent()) {
            Reward reward = productOptional.get();
            reward.setName(rewardDTO.getName());
            reward.setPrice(rewardDTO.getPrice());
            reward.setDescription(rewardDTO.getDescription());
            reward.setPhoto(rewardDTO.getPhoto());

            return rewardRepository.save(reward);
        }
        return null;
    }

    public void deleteById(Long id) {
        rewardRepository.deleteById(id);
    }
}
