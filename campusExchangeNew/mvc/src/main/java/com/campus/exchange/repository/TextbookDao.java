package com.campus.exchange.repository;

import com.campus.exchange.model.Furniture;
import com.campus.exchange.model.Textbook;

import java.util.List;

public interface TextbookDao {
    Textbook save(Textbook textbook);

    boolean update(Textbook textbook);

    boolean delete(Textbook textbook);

    List<Textbook> getTextbooks();


}
