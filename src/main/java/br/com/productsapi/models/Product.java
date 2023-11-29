package br.com.productsapi.models;

public class Product {
    private Long id;
    private String description;
    private String supplier;
    private Double price;
    private Double maxDiscount;

    public Product() {
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