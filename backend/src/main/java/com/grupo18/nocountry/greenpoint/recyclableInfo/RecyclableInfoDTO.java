package com.grupo18.nocountry.greenpoint.recyclableInfo;

import com.grupo18.nocountry.greenpoint.recyclable.RecyclableType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecyclableInfoDTO {

    private Long id;
    private String title;
    private String content;

    private RecyclableType tag;
}
