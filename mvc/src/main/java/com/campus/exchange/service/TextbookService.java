package com.campus.exchange.service;

import com.campus.exchange.model.Textbook;
import com.campus.exchange.repository.TextbookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextbookService {

    @Autowired
    private TextbookDao textbookDao;

    public Textbook save(Textbook textbook){
        return textbookDao.save(textbook);
    }

    public boolean update(Textbook textbook){
        return textbookDao.update(textbook);
    }

    public boolean delete(Textbook textbook){
        return textbookDao.delete(textbook);
    }

    public List<Textbook> getTextbooks(){
        return textbookDao.getTextbooks();
    }
}
