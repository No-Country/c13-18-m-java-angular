package com.grupo18.nocountry.greenpoint.recyclingPoint;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;


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
    @Column(length = 255)
    @NotNull
private String address;
}
