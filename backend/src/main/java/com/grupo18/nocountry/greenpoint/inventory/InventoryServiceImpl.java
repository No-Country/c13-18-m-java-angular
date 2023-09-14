package com.grupo18.nocountry.greenpoint.inventory;

import com.grupo18.nocountry.greenpoint.exceptions.RewardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    @Override
    public void updateStock(Long rewardId,StockUpdateDTO dto) {
        Inventory inventory = inventoryRepository.findById(rewardId).orElseThrow(
                ()->new RewardNotFoundException("El premio con id "+rewardId+" no existe.")
        );
        if(dto.getNewStock()<0){
            throw new IllegalArgumentException();
        }
        inventory.setStock(dto.getNewStock());
        inventoryRepository.save(inventory);

    }
}
