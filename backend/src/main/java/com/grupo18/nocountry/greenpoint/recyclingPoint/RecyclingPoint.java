package com.grupo18.nocountry.greenpoint.recyclingPoint;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalTime;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
@ToString

@Table(name = "RecyclingPoint")
public class RecyclingPoint {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id ;

    @Column(name = "comuna")
    @NotNull
    private int comuna;

    @Column(length = 255)
    @NotNull
    private String address;

    @Column(name = "opening_time")
    @NotNull
    private LocalTime openingTime;

    @Column(name = "closing_time")
    @NotNull
    private LocalTime closingTime;

    @ElementCollection(targetClass = DAYS_OF_THE_WEEK.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "RecyclingPoint_Days", joinColumns = @JoinColumn(name = "recycling_point_id"))
    @Column(name = "day")
    private Set<DAYS_OF_THE_WEEK> openDays;

}
