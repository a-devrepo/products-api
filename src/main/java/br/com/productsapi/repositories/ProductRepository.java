package br.com.productsapi.repositories;

import br.com.productsapi.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {

    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
    }

    @Override
    public List<Product> findALL() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Product add(Product product) {
        if (product.getId() == null) {
            product.setId(products.size() + 1L);
        }
        products.add(product);
        return product;
    }

    @Override
    public void update(Long id, Product product) {
        products.stream()
                .filter(p -> p.getId() == id).forEach(p -> {
                    p.setDescription(product.getDescription());
                    p.setPrice(product.getPrice());
                    p.setSupplier(product.getSupplier());
                    p.setMaxDiscount(product.getMaxDiscount());
                });
    }

    @Override
    public void delete(Long id) {
        products.removeIf(p -> p.getId() == id);
    }
}
