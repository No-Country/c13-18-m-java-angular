package com.grupo18.nocountry.greenpoint.inventory;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockUpdateDTO {
    @Min(value = 1,message = "El stock debe ser mayor o igual a 1")
    private int newStock;
}
