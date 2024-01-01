package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.dto.request.CustomerRequest;
import com.enigma.shopeymarth.dto.response.CustomerResponse;
import com.enigma.shopeymarth.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerRequest customerRequest);
    CustomerResponse createNewCustomer(Customer request);
    CustomerResponse getById(String id);
    List<CustomerResponse> getAll();
    CustomerResponse update(CustomerRequest customerRequest);
    void delete(String id);


}
