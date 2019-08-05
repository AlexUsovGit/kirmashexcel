package com.example.sweater.repos;


import com.example.sweater.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Integer> {

    List<Product> findAllByOrderByIdDesc();
    List<Product> findByBarcode(String barcode);
    List<Product> findAllByOrderByProductNameAsc();
    List<Product> findAllByOrderByProductNameDesc();
    List<Product> findAllByOrderByGenderAsc();

    List<Product> findByBarcodeOrderByIdAsc(String barcode);

    List<Product> findByIdOrderByIdAsc(Long id);
    List<Product> findById(Long id);
    List<Product> findByisDistrib(Integer id);



}

