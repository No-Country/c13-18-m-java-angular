package com.grupo18.nocountry.greenpoint.recyclableInfo;

import com.grupo18.nocountry.greenpoint.recyclable.RecyclableType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/info")
public class RecyclableInfoController {

    private final RecyclableInfoService recyclableInfoService;
    private final RecyclableInfoConverter recyclableInfoConverter;

    @GetMapping("/{id}")
    public ResponseEntity<?> getInfoById(@PathVariable Long id) {
        Optional<RecyclableInfo> recyclableInfoOptional = recyclableInfoService.getById(id);

        if (recyclableInfoOptional.isPresent()){
            RecyclableInfo recyclableInfo = recyclableInfoOptional.get();

            RecyclableInfoDTO recyclableInfoDTO = recyclableInfoConverter.convertInfoToInfoDTO(recyclableInfo);

            return ResponseEntity.ok(recyclableInfoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("getByTag/{tag}")
    public ResponseEntity<?> getInfoByTag(@PathVariable String tag) {
        return ResponseEntity.ok(recyclableInfoService.getByTag(RecyclableType.valueOf(tag)));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<RecyclableInfoDTO> recyclableInfoDTOS = recyclableInfoService.findAll()
                .stream()
                .map(recyclableInfo -> recyclableInfoConverter.convertInfoToInfoDTO(recyclableInfo)
                ).toList();
        return ResponseEntity.ok(recyclableInfoDTOS);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody RecyclableInfoDTO recyclableInfoDTO) throws URISyntaxException {

        recyclableInfoService.save(recyclableInfoConverter.convertInfoDTOToInfo(recyclableInfoDTO));

        return ResponseEntity.created(new URI("/api/v1/recyclableInfo/save")).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecyclableInfo(@PathVariable Long id, @RequestBody RecyclableInfoDTO recyclableInfoDTO) {

        Optional<RecyclableInfo> recyclableInfoOptional = recyclableInfoService.getById(id);

        if (recyclableInfoOptional.isPresent()) {
            recyclableInfoService.save(recyclableInfoConverter.convertInfoDTOToInfo(recyclableInfoDTO));
            return ResponseEntity.ok("Post Actualizado");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (id != null) {
            recyclableInfoService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }

        return ResponseEntity.badRequest().build();
    }
}
