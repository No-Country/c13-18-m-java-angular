package com.grupo18.nocountry.greenpoint.pointsystem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailsResponseDTO {

    private List<RecycledItemDTO> recycledItems;
    private int totalPoints;
}
