package com.grupo18.nocountry.greenpoint.reward;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rewards")
public class RewardController {

    private final RewardService rewardService;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Reward> productOptional = rewardService.getById(id);

        if (productOptional.isPresent()){
            Reward reward = productOptional.get();

            RewardDTO rewardDTO = RewardDTO.builder()
                    .id(reward.getId())
                    .name(reward.getName())
                    .price(reward.getPrice())
                    .description(reward.getDescription())
                    .photo(reward.getPhoto())
                    .inventory(reward.getInventory())
                    .inventory(reward.getInventory())
                    .build();

            return ResponseEntity.ok(rewardDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<RewardDTO> rewardDTOS = rewardService.findAll()
                .stream()
                .map(reward -> RewardDTO.builder()
                        .id(reward.getId())
                        .name(reward.getName())
                        .price(reward.getPrice())
                        .description(reward.getDescription())
                        .photo(reward.getPhoto())
                        .inventory(reward.getInventory())
                        .build()
                ).toList();

        return ResponseEntity.ok(rewardDTOS);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getProductsByName(@PathVariable String name) {
        List<RewardDTO> productListDTOs = rewardService.findAllWithName(name)
                .stream()
                .map(reward -> RewardDTO.builder()
                        .id(reward.getId())
                        .name(reward.getName())
                        .price(reward.getPrice())
                        .description(reward.getDescription())
                        .photo(reward.getPhoto())
                        .inventory(reward.getInventory())
                        .build()
                ).toList();

        return ResponseEntity.ok(productListDTOs);
    }

    @PostMapping
    public ResponseEntity<?> save (@RequestBody RewardDTO rewardDTO) throws URISyntaxException {

        rewardService.save(rewardDTO);

        return ResponseEntity.created(new URI("api/v1/product/save")).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct( @PathVariable Long id, @RequestBody RewardDTO rewardDTO) {
        Reward result = rewardService.update(rewardDTO, id);
        if (result != null) {
            return ResponseEntity.ok("Recompensa actualizada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (id != null) {
            rewardService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }

        return ResponseEntity.badRequest().build();
    }
}
