package com.grupo18.nocountry.greenpoint.recyclable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecyclableDTO {

    private Long id;
    private RecyclableType recyclableType;
    private int points;
}
