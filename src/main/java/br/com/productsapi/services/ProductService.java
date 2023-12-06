package br.com.productsapi.services;

import br.com.productsapi.exceptions.ProdutoNaoEncontradoException;
import br.com.productsapi.models.Product;
import br.com.productsapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {


    private ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Product> findAll() {
        return repository.findALL();
    }

    @Override
    public Product findById(Long id) {
        Product product = repository.findById(id);
        if (product == null) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado");
        }
        return product;
    }

    @Override
    public Product add(Product product) {
        return repository.add(product);
    }

    @Override
    public void update(Long id, Product product) {
        repository.update(id, product);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
