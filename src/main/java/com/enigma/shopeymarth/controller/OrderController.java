package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.constant.AppPath;
import com.enigma.shopeymarth.dto.request.OrderRequest;
import com.enigma.shopeymarth.dto.response.CommonResponse;
import com.enigma.shopeymarth.dto.response.OrderResponse;
import com.enigma.shopeymarth.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.ORDER)
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.createNewOrder(orderRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        CommonResponse.<OrderResponse>builder()
                                .statusCode(HttpStatus.CREATED.value())
                                .message("Successfully ordered a new item")
                                .data(orderResponse)
                                .build()
                );
    }
}
