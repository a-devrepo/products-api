package br.com.productsapi.repositories;

import br.com.productsapi.models.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
    }


    public List<Product> findALL() {
        return products;
    }


    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }


    public Product add(Product product) {
        if (product.getId() == null) {
            product.setId(products.size() + 1L);
        }
        products.add(product);
        return product;
    }


    public void update(Long id, Product product) {
        products.stream()
                .filter(p -> p.getId() == id).forEach(p -> {
                    p.setDescription(product.getDescription());
                    p.setPrice(product.getPrice());
                    p.setSupplier(product.getSupplier());
                    p.setMaxDiscount(product.getMaxDiscount());
                });
    }


    public void delete(Long id) {
        products.removeIf(p -> p.getId() == id);
    }
}
