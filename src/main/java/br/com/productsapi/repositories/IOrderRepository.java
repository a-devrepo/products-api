package br.com.productsapi.repositories;

import br.com.productsapi.models.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository {

    List<Order> findALL();

    Order findById(Long id);

    Order save(Order product);

    void update(Long id, Order order);

    void delete(Long id);
}
