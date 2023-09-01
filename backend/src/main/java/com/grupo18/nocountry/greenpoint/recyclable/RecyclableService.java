package com.grupo18.nocountry.greenpoint.recyclable;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecyclableService {

    private final RecyclableRepository recyclableRepository;

    public Optional<Recyclable> getById(Long id) {
        return recyclableRepository.findById(id);
    }
    public Optional<Recyclable> getByType(RecyclableType type) {
        return recyclableRepository.findByRecyclableType(type);
    }

    public List<Recyclable> findAll() {
        return recyclableRepository.findAll();
    }

    public void save (Recyclable recyclable) {
        recyclableRepository.save(recyclable);
    }

    public void deleteById (Long id) {
        recyclableRepository.deleteById(id);
    }
}
