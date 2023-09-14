package com.grupo18.nocountry.greenpoint.reward;

import com.grupo18.nocountry.greenpoint.inventory.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RewardDTO {

    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String photo;
    private Inventory inventory;
}
