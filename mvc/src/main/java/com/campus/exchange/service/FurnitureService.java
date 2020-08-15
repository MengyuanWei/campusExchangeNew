package com.campus.exchange.service;

import com.campus.exchange.model.Furniture;
import com.campus.exchange.model.Furniture;
import com.campus.exchange.repository.FurnitureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureService {
    
    @Autowired
    private FurnitureDao furnitureDao;

    public Furniture save(Furniture furniture){
        return furnitureDao.save(furniture);
    }

    public boolean update(Furniture furniture){
        return furnitureDao.update(furniture);
    }

    public boolean delete(Furniture furniture){
        return furnitureDao.delete(furniture);
    }

    public List<Furniture> getFurnitures(){
        return furnitureDao.getFurnitures();
    }

    public Furniture getFurnitureByName(String name){
        return furnitureDao.getFurnitureByName(name);
    }
}
