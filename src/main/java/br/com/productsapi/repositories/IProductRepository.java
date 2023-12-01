package br.com.productsapi.repositories;

import br.com.productsapi.models.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository {

    List<Product> findALL();

    Product findById(Long id);

    Product add(Product product);

    void update(Long id, Product product);

    void delete(Long id);
}
