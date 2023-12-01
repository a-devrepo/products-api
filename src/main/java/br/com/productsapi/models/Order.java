package br.com.productsapi.models;

public class Order {
    private Long id;
    private Product product;
    private Double discount;
    private Integer quantity;

    public Order() {
    }

    public Order(Long id, Product product, Double discount, Integer quantity) {
        this.id = id;
        this.product = product;
        this.discount = discount;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getOrderPrice() {
        return product.getPrice() * getQuantity();
    }
}
