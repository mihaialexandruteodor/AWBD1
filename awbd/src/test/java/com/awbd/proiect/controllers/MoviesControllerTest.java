package com.awbd.proiect.controllers;

import com.awbd.proiect.domain.Product;
import com.awbd.proiect.services.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductsControllerTest {

    @Mock
    Model model;

    @Mock
    ProductService productService;

    ProductsController productsController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        productsController = new ProductsController(productService);
    }

    @Captor
    ArgumentCaptor<Product> argumentCaptor;


    @Test
    public void showById() {
        Long id = 1l;
        Product productTest = new Product();
        productTest.setId(id);

        when(productService.findById(id)).thenReturn(productTest);

        String viewName = productsController.showById(id.toString(), model);
        Assert.assertEquals("info", viewName);
        verify(productService, times(1)).findById(id);

        //ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(model, times(1))
                .addAttribute(eq("product"), argumentCaptor.capture() );

        Product productArg = argumentCaptor.getValue();
        Assert.assertEquals(productArg.getId(), productTest.getId() );

    }
}


