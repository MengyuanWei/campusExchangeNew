package com.campus.exchange.repository;

import com.campus.exchange.model.Customer;
import com.campus.exchange.model.Furniture;

import java.util.List;

public interface FurnitureDao {

    Furniture save(Furniture furniture);

    boolean update(Furniture furniture);

    boolean delete(Furniture furn);

    List<Furniture> getFurnitures();

    Furniture getFurnitureByName(String name);


}
