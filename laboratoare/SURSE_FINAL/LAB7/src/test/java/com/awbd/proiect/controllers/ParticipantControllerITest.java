package com.awbd.proiect.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParticipantControllerITest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void showByIdMvc() throws Exception {


        mockMvc.perform(get("/participant/info/{id}","17"))
                .andExpect(status().isNotFound());
               // .andExpect(view().name("notfound"));
    }
}
