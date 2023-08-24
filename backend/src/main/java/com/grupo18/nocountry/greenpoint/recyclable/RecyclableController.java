package com.grupo18.nocountry.greenpoint.recyclable;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/recyclable")
@RequiredArgsConstructor
public class RecyclableController {

    private final RecyclableService recyclableService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecyclableById(@PathVariable Long id) {
        Optional<Recyclable> recyclableOptional = recyclableService.getById(id);

        if (recyclableOptional.isPresent()){
            Recyclable recyclable = recyclableOptional.get();

            // Todo usar libreria de mapeo dto, en vez de hacerlo manual
            RecyclableDTO recyclableDTO = RecyclableDTO.builder()
                    .id(recyclable.getId())
                    .recyclableType(recyclable.getRecyclableType())
                    .points(recyclable.getPoints())
                    .build();

            return ResponseEntity.ok(recyclableDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {

        List<RecyclableDTO> recyclableDTOS = recyclableService.findAll()
                .stream()
                .map(recyclable -> RecyclableDTO.builder()
                        .id(recyclable.getId())
                        .recyclableType(recyclable.getRecyclableType())
                        .points(recyclable.getPoints())
                        .build()
                ).toList();

        return ResponseEntity.ok(recyclableDTOS);
    }
}
