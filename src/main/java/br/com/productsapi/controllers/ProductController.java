package br.com.productsapi.controllers;

import br.com.productsapi.models.Product;
import br.com.productsapi.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") final Long id) {
        Product entity = service.findById(id);
        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody final Product product) {
        service.add(product);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(product.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(
            @PathVariable("id") final Long id, @RequestBody final Product product) {
        service.update(id, product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") final Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
