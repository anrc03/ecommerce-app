package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.dto.request.CustomerRequest;
import com.enigma.shopeymarth.dto.response.CustomerResponse;
import com.enigma.shopeymarth.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createCustomer() {
        CustomerRequest customerRequest = new CustomerRequest();
        CustomerResponse customerResponse = new CustomerResponse();

        when(customerService.create(customerRequest)).thenReturn(customerResponse);

        CustomerResponse actualResponse = customerController.createCustomer(customerRequest);

        assertEquals(customerResponse, actualResponse);
    }

    @Test
    void getCustomerById() {
        String customerId = "1";
        CustomerResponse customerResponse = new CustomerResponse();
        when(customerService.getById(customerId)).thenReturn(customerResponse);
        CustomerResponse actualResponse = customerController.getCustomerById(customerId);
        assertEquals(customerResponse, actualResponse);
    }

    @Test
    void getAll() {
        List<CustomerResponse> expectedResponse = new ArrayList<>();
        when(customerService.getAll()).thenReturn(expectedResponse);
        List<CustomerResponse> actualResponse = customerController.getAll();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void update() {
        CustomerRequest customerRequest = new CustomerRequest();
        CustomerResponse expectedResponse = new CustomerResponse();

        when(customerService.update(customerRequest)).thenReturn(expectedResponse);

        CustomerResponse actualResponse = customerController.update(customerRequest);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void delete() {
        String customerId = "1";
        customerController.delete(customerId);
        verify(customerService, times(1)).delete(customerId);
    }
}