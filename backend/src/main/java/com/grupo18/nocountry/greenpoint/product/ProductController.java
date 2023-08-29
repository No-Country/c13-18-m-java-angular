package com.grupo18.nocountry.greenpoint.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.getById(id);

        if (productOptional.isPresent()){
            Product product = productOptional.get();

            ProductDTO productDTO = ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .photo(product.getPhoto())
                    .build();

            return ResponseEntity.ok(productDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<ProductDTO> productDTOS = productService.findAll()
                .stream()
                .map(product -> ProductDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .photo(product.getPhoto())
                        .build()
                ).toList();

        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getProductsByName(@PathVariable String name) {
        List<ProductDTO> productListDTOs = productService.findAllWithName(name)
                .stream()
                .map(product -> ProductDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .photo(product.getPhoto())
                        .build()
                ).toList();

        return ResponseEntity.ok(productListDTOs);
    }

    @PostMapping
    public ResponseEntity<?> save (@RequestBody ProductDTO productDTO ) throws URISyntaxException {

        productService.save(Product.builder()
                        .id(productDTO.getId())
                        .name(productDTO.getName())
                        .price(productDTO.getPrice())
                        .description(productDTO.getDescription())
                        .photo(productDTO.getPhoto())
//                        .catalogue(Catalogue.getInstance()) // patron singleton en el constructor de catalogue, xq solo hay un catalogo
                .build());

        return ResponseEntity.created(new URI("api/v1/product/save")).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct( @PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Optional<Product> productOptional = productService.getById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setId(productDTO.getId());
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setPhoto(productDTO.getPhoto());
            productService.save(product);
            return ResponseEntity.ok("Registro Actualizado");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (id != null) {
            productService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }

        return ResponseEntity.badRequest().build();
    }
}
