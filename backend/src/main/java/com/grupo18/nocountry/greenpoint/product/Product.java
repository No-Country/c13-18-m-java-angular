package com.grupo18.nocountry.greenpoint.product;

import com.grupo18.nocountry.greenpoint.catalogue.Catalogue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price; // no le pongo BigDecimal, xq son precios de puntos pequeños.
    private String description;
    private String photo;
    @ManyToOne
    private Catalogue catalogue;
}
