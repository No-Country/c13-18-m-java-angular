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
    private final RecyclableDTOConverter recyclableDTOConverter;

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecyclableById(@PathVariable Long id) {
        Optional<Recyclable> recyclableOptional = recyclableService.getById(id);

        if (recyclableOptional.isPresent()){
            Recyclable recyclable = recyclableOptional.get();

            RecyclableDTO recyclableDTO = recyclableDTOConverter.convertRecyclableToRecyclableDTO(recyclable);

            return ResponseEntity.ok(recyclableDTO);
        }else{
            throw new RecyclableNotFound("EL reciclable con el id "+id+" no existe.");
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<RecyclableDTO> recyclableDTOS = recyclableService.findAll()
                .stream()
                .map(recyclable -> recyclableDTOConverter.convertRecyclableToRecyclableDTO(recyclable)
                ).toList();

        return ResponseEntity.ok(recyclableDTOS);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody RecyclableDTO recyclableDTO) throws URISyntaxException {

        if (recyclableService.getByType(recyclableDTO.getRecyclableType()).isPresent()){
            throw new RecyclableAlreadyExist("Este reciclable ya se encuentra registrado.");
        }


        recyclableService.save(recyclableDTOConverter.convertRecyclableDTOToRecyclable(recyclableDTO));

        return ResponseEntity.created(new URI("/api/v1/recyclable/save")).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecyclable(@PathVariable Long id, @RequestBody RecyclableDTO recyclableDTO) {

        Recyclable updatedRecyclable = recyclableDTOConverter.convertRecyclableDTOToRecyclable(recyclableDTO);

        Recyclable result = recyclableService.update(updatedRecyclable, id);

        if (result != null) {
            return ResponseEntity.ok("Reciclable actualizado");
        } else {
            throw new RecyclableNotFound("El reciclable con el ID " + id + " no existe.");
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
