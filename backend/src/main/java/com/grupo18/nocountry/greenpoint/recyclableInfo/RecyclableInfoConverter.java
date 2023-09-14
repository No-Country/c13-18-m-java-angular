package com.grupo18.nocountry.greenpoint.recyclableInfo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecyclableInfoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public RecyclableInfoDTO convertInfoToInfoDTO(RecyclableInfo recyclableInfo) {

        RecyclableInfoDTO recyclableInfoDTO = modelMapper.map(recyclableInfo, RecyclableInfoDTO.class);
        return recyclableInfoDTO;
    }

    public RecyclableInfo convertInfoDTOToInfo(RecyclableInfoDTO recyclableInfoDTO) {

        RecyclableInfo recyclableInfo = modelMapper.map(recyclableInfoDTO, RecyclableInfo.class);
        return recyclableInfo;
    }
}
