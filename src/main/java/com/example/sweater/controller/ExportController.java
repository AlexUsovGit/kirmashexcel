package com.example.sweater.controller;


import com.example.sweater.domain.NewLabel;
import com.example.sweater.domain.Product;
import com.example.sweater.domain.basedictionary.Composition;
import com.example.sweater.domain.basedictionary.ProductName;
import com.example.sweater.export.Exports;
import com.example.sweater.repos.InfoClassRepo;
import com.example.sweater.repos.ProductRepo;
import com.example.sweater.repos.basedictionaryrepos.CompositionRepo;
import com.example.sweater.repos.basedictionaryrepos.ProductNameRepo;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Controller
public class ExportController {
    @Autowired
    private ProductRepo productRepo;


    @PostMapping("/exportAllProducts")
    public String exportAllProducts(Map<String, Object> model) {
        Iterable<Product> products = productRepo.findAllByOrderByIdDesc();

        model.put("products", products);
        Exports exports = new Exports();
        exports.createXlsx(products);

        return "producttable";
    }




}
