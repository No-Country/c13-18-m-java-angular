package com.grupo18.nocountry.greenpoint.pointsystem;

import com.grupo18.nocountry.greenpoint.recyclable.Recyclable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
public class RecyclableDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "recyclableDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecycledItem> recycledItems;

    private String code;
    private Integer totalPoints;
    private boolean redeemed;

    public int getTotalWeight(){
        if(!recycledItems.isEmpty()) {
            return recycledItems.stream()
                    .mapToInt(RecycledItem::getGrams)
                    .sum();
        }
        return 0;
    }

}
