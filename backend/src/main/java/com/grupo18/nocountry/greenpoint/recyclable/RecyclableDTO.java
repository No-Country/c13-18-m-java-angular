package com.grupo18.nocountry.greenpoint.recyclable;

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
public class RecyclableDTO {

    private Long id;
    @NotNull
    private RecyclableType recyclableType;
    @NotNull
    @Min(value = 1)
    private Integer points;
}
