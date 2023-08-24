package com.grupo18.nocountry.greenpoint.recyclable;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Entity
@Data
@Builder
public class Recyclable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RecyclableType recyclableType;
    private int points;
}
