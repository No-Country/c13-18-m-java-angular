package com.grupo18.nocountry.greenpoint.recyclable;

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
public class Recyclable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RecyclableType recyclableType;
    private int points;

    public Recyclable(RecyclableType recyclableType) {
        this.recyclableType = recyclableType;
        this.points = calculatePoints(recyclableType);
    }

    private int calculatePoints(RecyclableType recyclableType) {
        switch (recyclableType) {
            case METAL:
                return 5;
            case MADERA:
                return 3;
            case ORGANICO:
                return 2;
            case VIDRIO:
                return 1;
            case PLASTICO:
                return 4;
            default:
                return 0; // tipo no reconocido
        }
    }
}
