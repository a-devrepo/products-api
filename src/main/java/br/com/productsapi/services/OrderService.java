package br.com.productsapi.services;

import br.com.productsapi.exceptions.ProdutoForaDeEstoqueException;
import br.com.productsapi.exceptions.ProdutoNaoEncontradoException;
import br.com.productsapi.models.Order;
import br.com.productsapi.models.Product;
import br.com.productsapi.repositories.IOrderRepository;
import br.com.productsapi.repositories.IProductRepository;
import br.com.productsapi.repositories.OrderRepository;
import br.com.productsapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    private IOrderRepository repository;
    private IProductRepository productRepository;

    public OrderService(IProductRepository productRepository, IOrderRepository repository) {
        this.productRepository = productRepository;
        this.repository = repository;
    }

    @Override
    public List<Order> findAll() {
        return repository.findALL();
    }

    @Override
    public Order findById(Long id) {
        Order order = repository.findById(id);
        if (order == null) {
            throw new ProdutoNaoEncontradoException("Pedido não encontrado");
        }
        return order;
    }

    @Override
    public Order save(Order order) {
        verifyQuantity(order);
        calculateOrderPrice(order);
        return repository.save(order);
    }

    @Override
    public void update(Long id, Order order) {
        repository.update(id, order);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    public double calculateOrderPrice(Order newOrder) {
        double orderPrice = 0.0;

        if (newOrder.getDiscount() == null) {
            orderPrice = newOrder.getOrderPrice();
        } else {
            double discount = newOrder.getDiscount();
            double priceWithDiscount = newOrder.getProduct().getPriceWithDiscount(discount);
            orderPrice = priceWithDiscount * newOrder.getQuantity();
        }
        return orderPrice;
    }

    public void verifyQuantity(Order newOrder) {
        Product productInStock = productRepository.findById(newOrder.getProduct().getId());
        if (productInStock.getStockQuantity() == 0) {
            throw new ProdutoForaDeEstoqueException("Produto fora de estoque");
        }
        if (newOrder.getQuantity() > productInStock.getStockQuantity()) {
            newOrder.setQuantity(productInStock.getStockQuantity());
        }
        productInStock.setStockQuantity(productInStock.getStockQuantity() - newOrder.getQuantity());
    }
}
