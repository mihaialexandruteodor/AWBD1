package com.awbd.proiect.services;

import com.awbd.proiect.domain.Product;
import com.awbd.proiect.repositories.MovieRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {

    MovieService productService;

    @Mock
    MovieRepository productRepository;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        productService = new MovieServiceImpl(productRepository);
    }

    @Test
    public void findProducts() {
        List<Product> productsRet = new ArrayList<Product>();
        Product product = new Product();
        product.setCategories(null);
        product.setId(4L);
        product.setCode("TEST");
        productsRet.add(product);


        when(productRepository.findAll()).thenReturn(productsRet);
        List<Product> products = productService.findAll();
        assertEquals(products.size(), 1);
        verify(productRepository, times(1)).findAll();
    }
}
