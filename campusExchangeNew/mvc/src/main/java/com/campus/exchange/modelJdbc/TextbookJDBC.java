package com.campus.exchange.modelJdbc;

import java.math.BigDecimal;

public class TextbookJDBC {
    public TextbookJDBC(){}

    private long id;
    private String name;
    private String category;
    private String isbn;
    private String description;
    private long customerId;
    private BigDecimal price;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public long getcustomerId() {
        return customerId;
    }
    public void setcustomerId(long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
