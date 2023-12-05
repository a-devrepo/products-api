package br.com.productsapi.models;

public class Product {
    private Long id;
    private String description;
    private String supplier;
    private Double price;
    private Double maxDiscount;

    public Product() {
    }

    public Product(Long id, String description, String supplier, Double price, Double maxDiscount) {
        this.id = id;
        this.description = description;
        this.supplier = supplier;
        this.price = price;
        this.maxDiscount = maxDiscount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(Double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public double getPriceWithDiscount(double discount) {
        if (discount > maxDiscount)
            return price - (price * maxDiscount);
        else
            return price - (price * discount);
    }
}