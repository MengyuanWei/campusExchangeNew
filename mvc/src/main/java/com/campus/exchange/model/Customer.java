package com.campus.exchange.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    public Customer(){}

    // 1 customer could have many furnitures.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Furniture> furnitures;

    // 1 customer could have many textbooks.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Textbook> textbooks;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    public Long getId() { return id; }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Set<Furniture> getFurnitures() { return furnitures; }

    public void setFurnitures(Set<Furniture> furnitures) { this.furnitures = furnitures; }

    public Set<Textbook> getTextbooks() { return textbooks; }

    public void setTextbooks(Set<Textbook> textbooks) { this.textbooks = textbooks; }

    @Override
    public String toString(){
        return String.format(id + ", " + name + ", " + password + ", " + email + ", " + phoneNumber);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name, password, email, phoneNumber);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                name.equals(customer.name) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(phoneNumber, customer.phoneNumber);
    }

}

