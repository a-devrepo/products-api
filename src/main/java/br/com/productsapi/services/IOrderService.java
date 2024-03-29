package br.com.productsapi.services;

import br.com.productsapi.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IOrderService {
    List<Order> findAll();

    Order findById(Long id);

    Order save(Order order);

    void update(Long id, Order product);

    void delete(Long id);

    public double calculateOrderPrice(Order newOrder);
}
