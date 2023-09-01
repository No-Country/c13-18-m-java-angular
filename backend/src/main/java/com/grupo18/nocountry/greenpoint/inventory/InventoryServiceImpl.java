package com.grupo18.nocountry.greenpoint.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    @Override
    public void updateStock(Long rewardId,StockUpdateDTO dto) {
        Inventory inventory = inventoryRepository.findById(rewardId).orElseThrow(
                ()->new RuntimeException("El premio con id "+rewardId+" no existe.")
        );
        if(dto.getNewStock()<0){
            throw new RuntimeException("Ingrese una cantidad mayor a cero.");
        }
        inventory.setStock(dto.getNewStock());
        inventoryRepository.save(inventory);

    }
}
