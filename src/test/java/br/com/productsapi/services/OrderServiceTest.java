package br.com.productsapi.services;

import br.com.productsapi.exceptions.ProdutoForaDeEstoqueException;
import br.com.productsapi.models.Order;
import br.com.productsapi.models.Product;
import br.com.productsapi.repositories.IOrderRepository;
import br.com.productsapi.repositories.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    IOrderRepository repository;
    @Mock
    IProductRepository productRepository;
    OrderService service;
    Order order;
    Product product;

    @BeforeEach
    public void setup() {
        service = new OrderService(productRepository,repository);
        product = new Product(1L, "Notebook", "DELL", 6000.0, 0.1, 5);
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

    @Test
    void givenOrderObject_WhenQuantityIsEqualToStock_ThenSellOrderQuantity() {
        //Given
        given(productRepository.findById(anyLong())).willReturn(product);
        given(repository.save(order)).willReturn(order);

        //When
        Order newOrder = service.save(order);

        //Then
        assertNotNull(newOrder);
        assertEquals(5,newOrder.getQuantity());
    }

    @Test
    void givenOrderObject_WhenQuantityIsLowerToStock_ThenSellOrderQuantity() {
        //Given
        order.setQuantity(2);
        given(productRepository.findById(anyLong())).willReturn(product);
        given(repository.save(order)).willReturn(order);

        //When
        Order newOrder = service.save(order);

        //Then
        assertNotNull(newOrder);
        assertEquals(2, newOrder.getQuantity());
    }

    @Test
    void givenOrderObject_WhenQuantityIsHigherThanStock_ThenAvailableStock() {
        //Given
        order.setQuantity(10);
        given(productRepository.findById(anyLong())).willReturn(product);
        given(repository.save(order)).willReturn(order);

        //When
        Order newOrder = service.save(order);

        //Then
        assertNotNull(newOrder);
        assertEquals(5, newOrder.getQuantity());
    }

    @Test
    void givenOrderObject_WhenProductIsOutOfStock_ThenThrowAnException() {
        //Given
        product.setStockQuantity(0);
        given(productRepository.findById(anyLong())).willReturn(product);

        //When
        //Then
        assertThrows(ProdutoForaDeEstoqueException.class, () ->{
            Order newOrder = service.save(order);
        });
    }
}