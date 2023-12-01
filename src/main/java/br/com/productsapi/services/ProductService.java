package br.com.productsapi.services;

import br.com.productsapi.models.Product;
import br.com.productsapi.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository repository;

    @Override
    public List<Product> findAll() {
        return repository.findALL();
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Product add(Product product) {
        return repository.add(product);
    }

    @Override
    public void update(Long id, Product product) {
        repository.update(id,product);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
