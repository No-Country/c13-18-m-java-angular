package com.grupo18.nocountry.greenpoint.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grupo18.nocountry.greenpoint.reward.Reward;
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
@JsonIgnoreProperties("reward")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "inventory")
    private Reward reward;
    private int stock;
}
