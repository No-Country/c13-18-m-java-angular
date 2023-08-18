package com.grupo18.nocountry.greenpoint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "temporal_sample")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemporalSample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long temporalSample_id;
    private String descripcion;
    private int puntaje;

}
