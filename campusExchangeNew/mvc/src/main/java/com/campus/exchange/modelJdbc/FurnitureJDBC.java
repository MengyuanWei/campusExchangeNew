package com.campus.exchange.modelJdbc;

import java.math.BigDecimal;

public class FurnitureJDBC {
    public FurnitureJDBC(){ }

    private long id;
    private String name;
    private String category;
    private String description;
    private long customerId;
    private BigDecimal price;

    public FurnitureJDBC(long id, String name, String category,
                         String description, long customerId, BigDecimal price){
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.customerId = customerId;
        this.price = price;
    }

    public FurnitureJDBC(String name, String category,
                         String description, long customerId, BigDecimal price){
        this.name = name;
        this.category = category;
        this.description = description;
        this.customerId = customerId;
        this.price = price;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) { this.price = price; }

    @Override
    public String toString(){
        return String.format(id + "," + name + "," + category + "," + description
                + "," + customerId + "," + price);
    }

    public BigDecimal setPrice() {
        this.price = price;
        return price;
    }

    public long setCustomerId() {
        this.customerId = customerId;
        return customerId;
    }

    public String setName() {
        this.name = name;
        return name;
    }
}
