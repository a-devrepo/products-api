package br.com.productsapi.services;

import br.com.productsapi.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product findById(Long id);

    Product add(Product product);

    void update(Long id, Product product);

    void delete(Long id);
}
