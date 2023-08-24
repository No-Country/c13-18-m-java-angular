package com.grupo18.nocountry.greenpoint.recyclingPoint;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicUpdate
@ToString

@Table(name = "RecyclingPoint")
public class RecyclingPoint {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
private long id ;
    @Column(length = 255)
private String address;
private int totalPoints;
    @Column(unique = true)
private String code;
private LocalDate timeStamp;
}
