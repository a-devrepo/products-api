package br.com.productsapi.controllers;

import br.com.productsapi.models.Order;
import br.com.productsapi.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService service;

    @PostMapping
    public ResponseEntity<Order> add(@RequestBody final Order newOrder) {
        service.save(newOrder);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newOrder.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(newOrder);
    }

}
