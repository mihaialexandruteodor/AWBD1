package com.awbd.proiect.services;

import com.awbd.proiect.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long l);
    Product save(Product product);
    void deleteById(Long id);

}
