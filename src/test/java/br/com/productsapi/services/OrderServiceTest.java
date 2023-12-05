package br.com.productsapi.services;

import br.com.productsapi.models.Order;
import br.com.productsapi.models.Product;
import br.com.productsapi.repositories.IOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    IOrderRepository repository;
    IOrderService service;
    Order order;
    Product product;

    @BeforeEach
    public void setup() {
        service = new OrderService();
        product = new Product(1L, "Notebook", "DELL", 6000.0, 0.1);
        order = new Order(1L, product, 0.1, 5);
    }

    @Test
    void givenOrderObject_WhenWithoutDiscount_ThenCalculatePriceWithoutDiscount() {
        //Given
        order.setDiscount(null);

        //When
        double orderPrice = service.calculateOrderPrice(order);

        //Then
        assertEquals(30000.0, orderPrice);
    }

    @Test
    void givenOrderObject_WhenDiscountEqualToMax_ThenCalculatePriceWithDiscount() {
        //Given

        //When
        double orderPrice = service.calculateOrderPrice(order);

        //Then
        assertEquals(27000.0, orderPrice);
    }

    @Test
    void givenOrderObject_WhenDiscountLowerThanMax_ThenCalculatePriceWithDiscount() {
        //Given
        order.setDiscount(0.05);

        //When
        double orderPrice = service.calculateOrderPrice(order);

        //Then
        assertEquals(28500.0, orderPrice);
    }

    @Test
    void givenOrderObject_WhenDiscountHigherThanMax_ThenCalculatePriceWithMax() {
        //Given
        order.setDiscount(0.4);

        //When
        double orderPrice = service.calculateOrderPrice(order);

        //Then
        assertEquals(27000.0, orderPrice);
    }
}