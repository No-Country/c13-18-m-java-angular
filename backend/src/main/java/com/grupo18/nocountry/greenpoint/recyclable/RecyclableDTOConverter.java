package com.grupo18.nocountry.greenpoint.recyclable;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecyclableDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public RecyclableDTO convertRecyclableToRecyclableDTO(Recyclable recyclable) {

        RecyclableDTO recyclableDTO = modelMapper.map(recyclable, RecyclableDTO.class);
        return recyclableDTO;
    }

    public Recyclable convertRecyclableDTOToRecyclable(RecyclableDTO recyclableDTO) {

        Recyclable recyclable = modelMapper.map(recyclableDTO, Recyclable.class);
        return recyclable;
    }
}
