package com.awbd.lab7;

import com.awbd.lab7.domain.Product;
import com.awbd.lab7.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @MockBean
    Model model;


    @Test
    public void showByIdMvc() throws Exception {
        Long id = 1l;
        Product productTest = new Product();
        productTest.setId(id);
        productTest.setName("test");

        when(productService.findById(id)).thenReturn(productTest);


        mockMvc.perform(get("/product/info/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("info"))
                .andExpect(model().attribute("product", productTest));
    }
}
