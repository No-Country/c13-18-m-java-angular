package com.grupo18.nocountry.greenpoint.pointsystem;

import com.grupo18.nocountry.greenpoint.recyclable.Recyclable;
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
public class RecycledItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int grams;

    @ManyToOne
    @JoinColumn(name = "recyclable_id")
    private Recyclable recyclable;

    @ManyToOne
    @JoinColumn(name = "recyclable_details_id")
    private RecyclableDetails recyclableDetails;

}