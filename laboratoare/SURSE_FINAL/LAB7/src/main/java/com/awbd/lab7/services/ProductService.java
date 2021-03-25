package com.awbd.lab7.services;

import com.awbd.lab7.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long l);
    Product save(Product product);
    void deleteById(Long id);

}
