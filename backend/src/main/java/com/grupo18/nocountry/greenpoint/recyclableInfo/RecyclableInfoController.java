package com.grupo18.nocountry.greenpoint.recyclableInfo;

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
public class RecyclableInfoController {

    private final RecyclableInfoService recyclableInfoService;

    @GetMapping("/id")
    public ResponseEntity<?> getInfoById(@PathVariable Long id) {
        Optional<RecyclableInfo> recyclableInfoOptional = recyclableInfoService.getById(id);

        if (recyclableInfoOptional.isPresent()){
            RecyclableInfo recyclableInfo = recyclableInfoOptional.get();

            RecyclableInfoDTO recyclableInfoDTO = RecyclableInfoDTO.builder()
                    .id(recyclableInfo.getId())
                    .title(recyclableInfo.getTitle())
                    .content(recyclableInfo.getContent())
                    .tag(recyclableInfo.getTag())
                    .build();

            return ResponseEntity.ok(recyclableInfoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{tag}")
    public ResponseEntity<?> getInfoByTag(@PathVariable String tag) {
        return ResponseEntity.ok(recyclableInfoService.getByTag(tag));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<RecyclableInfoDTO> recyclableInfoDTOS = recyclableInfoService.findAll()
                .stream()
                .map(recyclableInfo -> RecyclableInfoDTO.builder()
                        .id(recyclableInfo.getId())
                        .title(recyclableInfo.getTitle())
                        .content(recyclableInfo.getContent())
                        .tag(recyclableInfo.getTag())
                        .build()
                ).toList();
        return ResponseEntity.ok(recyclableInfoDTOS);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody RecyclableInfoDTO recyclableInfoDTO) throws URISyntaxException {

        recyclableInfoService.save(RecyclableInfo.builder()
                        .id(recyclableInfoDTO.getId())
                        .title(recyclableInfoDTO.getTitle())
                        .content(recyclableInfoDTO.getContent())
                        .tag(recyclableInfoDTO.getTag())
                .build());

        return ResponseEntity.created(new URI("/api/v1/recyclableInfo/save")).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecyclableInfo(@PathVariable Long id, @RequestBody RecyclableInfoDTO recyclableInfoDTO) {

        Optional<RecyclableInfo> recyclableInfoOptional = recyclableInfoService.getById(id);

        if (recyclableInfoOptional.isPresent()) {
            RecyclableInfo recyclableInfo = recyclableInfoOptional.get();
            recyclableInfo.setTitle(recyclableInfoDTO.getTitle());
            recyclableInfo.setContent(recyclableInfo.getContent());
            recyclableInfo.setTag(recyclableInfoDTO.getTag());
            recyclableInfoService.save(recyclableInfo);
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
