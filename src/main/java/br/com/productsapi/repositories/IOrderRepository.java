package  br.com.productsapi.repositories;

import br.com.productsapi.models.Order;

import java.util.List;

public interface IOrderRepository {

    List<Order> findALL();

    Order findById(Long id);

    Order add(Order product);

    void update(Long id, Order order);

    void delete(Long id);
}
