package com.campus.exchange.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "furnitures")
public class Furniture {
    public Furniture(){ }

    @ManyToOne(fetch = FetchType.LAZY)  // 数据库里
    @JoinColumn(name = "customers_id")  // 数据库里
    private Customer customer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    public Furniture(long id, String name, String category,
                         String description, long customerId, BigDecimal price){
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public Furniture(String name, String category,
                         String description, long customerId, BigDecimal price){
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
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

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal setPrice(double v) {
        this.price = price;
        return price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name, category, description, price);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Furniture furniture = (Furniture) o;
        return id == furniture.id &&
                name.equals(furniture.name) &&
                Objects.equals(category, furniture.category) &&
                Objects.equals(description, furniture.description) &&
                Objects.equals(price, furniture.price);
    }

}
