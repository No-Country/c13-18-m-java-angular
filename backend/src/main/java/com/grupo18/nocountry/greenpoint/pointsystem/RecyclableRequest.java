package com.grupo18.nocountry.greenpoint.pointsystem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecyclableRequest {
    @NotNull
    private Long recyclableId;
    @NotNull
    @Min(value = 0,message = "La cantidad de gramos no puede ser negativa")
    private int grams;

}
