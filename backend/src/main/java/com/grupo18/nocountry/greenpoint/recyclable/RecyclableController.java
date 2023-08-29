package com.grupo18.nocountry.greenpoint.recyclable;

import com.grupo18.nocountry.greenpoint.exceptions.RecyclableAlreadyExist;
import com.grupo18.nocountry.greenpoint.exceptions.RecyclableNotFound;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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
        }else{
            throw new RecyclableNotFound("EL reciclable con el id "+id+" no existe.");
        }
    }

    @GetMapping
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

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody RecyclableDTO recyclableDTO) throws URISyntaxException {

        if (recyclableService.getByType(recyclableDTO.getRecyclableType()).isPresent()){
            throw new RecyclableAlreadyExist("Este reciclable ya se encuentra registrado.");
        }


        recyclableService.save(Recyclable.builder()
                .recyclableType(recyclableDTO.getRecyclableType())
                .points(recyclableDTO.getPoints())
                .build());

        return ResponseEntity.created(new URI("/api/v1/recyclable/save")).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecyclable(@PathVariable Long id, @RequestBody RecyclableDTO recyclableDTO) {

        Optional<Recyclable> recyclableOptional = recyclableService.getById(id);

        if (recyclableOptional.isPresent()){
            Recyclable recyclable = recyclableOptional.get();
            recyclable.setRecyclableType(recyclableDTO.getRecyclableType());
            recyclable.setPoints(recyclableDTO.getPoints());
            recyclableService.save(recyclable);
            return ResponseEntity.ok("Registro Actualizado");
        }else{
            throw new RecyclableNotFound("EL reciclable con el id "+id+" no existe.");
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        if (id != null) {
            recyclableService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }

        return ResponseEntity.badRequest().build();
    }
}
