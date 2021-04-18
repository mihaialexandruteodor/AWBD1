package com.awbd.controllers;

import com.awbd.controller.ClientController;
import com.awbd.model.Client;
import com.awbd.service.ClientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.junit.Rule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ClientControllerTest {

    @Mock
    Model model;

    @Mock
    ClientService clientService;

    ClientController clientController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        clientController = new ClientController(clientService);
    }

    @Captor
    ArgumentCaptor<Client> argumentCaptor;


    @Test
    public void showNewClientForm() {
        Long id = 1l;
        Client clientTest = new Client();
        clientTest.setClientid(id);

        when(clientService.getClientById(id)).thenReturn(clientTest);

        String viewName = clientController.showNewClientForm(model);
        Assert.assertEquals("new_client", viewName);
    }
    
}
