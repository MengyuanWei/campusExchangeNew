package com.campus.exchange.controller;

import com.campus.exchange.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/furnitures", "/furns"})
public class FurnitureController {
    private Logger logger = LoggerFactory.getLogger(getClass());
}
