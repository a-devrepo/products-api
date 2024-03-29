package br.com.productsapi.repositories;

import br.com.productsapi.models.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class OrderRepository {

    private List<Order> orders;

    public OrderRepository() {
        orders = new ArrayList<>();
    }


    public List<Order> findALL() {
        return orders;
    }


    public Order findById(Long id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst().orElse(null);
    }


    public Order save(Order product) {
        if (product.getId() == null) {
            product.setId(orders.size() + 1L);
        }
        orders.add(product);
        return product;
    }


    public void update(Long id, Order order) {
        orders.stream()
                .filter(o -> o.getId() == id).forEach(o -> {
                    o.setProduct(order.getProduct());
                    o.setDiscount(order.getDiscount());
                    o.setQuantity(order.getQuantity());
                });
    }


    public void delete(Long id) {
        orders.removeIf(o -> o.getId() == id);
    }
}
