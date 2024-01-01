package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.request.CustomerRequest;
import com.enigma.shopeymarth.dto.response.CustomerResponse;
import com.enigma.shopeymarth.entity.Customer;
import com.enigma.shopeymarth.repository.CustomerRepository;
import com.enigma.shopeymarth.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse create(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .mobilePhone(customerRequest.getMobilePhone())
                .email(customerRequest.getEmail())
                .build();
        customerRepository.save(customer);
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .mobilePhone(customer.getMobilePhone())
                .email(customer.getEmail())
                .build();
    }

    @Override
    public CustomerResponse createNewCustomer(Customer request) {
        Customer customer = customerRepository.saveAndFlush(request);
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .mobilePhone(customer.getMobilePhone())
                .build();
    }

    @Override
    public CustomerResponse getById(String id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return CustomerResponse.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .address(customer.getAddress())
                    .mobilePhone(customer.getMobilePhone())
                    .email(customer.getEmail())
                    .build();
        }
        return null;
    }

    @Override
    public List<CustomerResponse> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerResponse(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getMobilePhone(),
                        customer.getEmail()
                ))
                .toList();
    }

    @Override
    public CustomerResponse update(CustomerRequest customerRequest) {
        if (customerRequest.getId() != null) {
            Customer customer = Customer.builder()
                    .id(customerRequest.getId())
                    .name(customerRequest.getName())
                    .address(customerRequest.getAddress())
                    .mobilePhone(customerRequest.getMobilePhone())
                    .email(customerRequest.getEmail())
                    .build();
            customerRepository.save(customer);
            return getById(customer.getId());
        }
        return null;
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }
}
