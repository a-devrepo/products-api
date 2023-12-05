package br.com.productsapi.services;

import br.com.productsapi.exceptions.ProdutoNaoEncontradoException;
import br.com.productsapi.models.Order;
import br.com.productsapi.models.Product;
import br.com.productsapi.repositories.IOrderRepository;
import br.com.productsapi.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository repository;

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
    public Order add(Order order) {
        return repository.add(order);
    }

    @Override
    public void update(Long id, Order order) {
        repository.update(id, order);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    public double calculateOrderPrice(Order newOrder){
        double orderPrice = 0.0;
        if(newOrder.getDiscount() == null){
            orderPrice =  newOrder.getOrderPrice();
        } else {
            double discount = newOrder.getDiscount();
            double priceWithDiscount = newOrder.getProduct().getPriceWithDiscount(discount);
            orderPrice = priceWithDiscount * newOrder.getQuantity();
        }
        return orderPrice;
    }
}
