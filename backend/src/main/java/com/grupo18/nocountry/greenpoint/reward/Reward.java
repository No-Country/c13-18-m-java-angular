package com.grupo18.nocountry.greenpoint.reward;

import com.grupo18.nocountry.greenpoint.inventory.Inventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price; // no le pongo BigDecimal, xq son precios de puntos pequeños.
    private String description;
    private String photo;
    @OneToOne(orphanRemoval = true)
    private Inventory inventory;
}
