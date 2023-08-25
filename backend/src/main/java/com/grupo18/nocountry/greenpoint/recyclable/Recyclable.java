package com.grupo18.nocountry.greenpoint.recyclable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Scanner;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recyclable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RecyclableType recyclableType;
    private int points;

}
