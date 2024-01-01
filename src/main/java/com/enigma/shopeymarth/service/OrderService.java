package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.dto.request.OrderRequest;
import com.enigma.shopeymarth.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createNewOrder(OrderRequest orderRequest);
    OrderResponse getOrderById(String id);
    List<OrderResponse> getAllOrder();

}
