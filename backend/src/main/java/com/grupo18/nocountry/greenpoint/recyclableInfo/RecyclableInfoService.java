package com.grupo18.nocountry.greenpoint.recyclableInfo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecyclableInfoService {
    private final ReciclableInfoRepository reciclableInfoRepository;

    public List<RecyclableInfo> findAll() {
        return reciclableInfoRepository.findAll();
    }

    public Optional<RecyclableInfo> getById(Long id) {
        return reciclableInfoRepository.findById(id);
    }

    public List<RecyclableInfo> getByTag(String tag) {
        return reciclableInfoRepository.findByTag(tag);
    }
    public void save(RecyclableInfo recyclableInfo) {
        reciclableInfoRepository.save(recyclableInfo);
    }

    public void deleteById(Long id) {
        reciclableInfoRepository.deleteById(id);
    }
}
