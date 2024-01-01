package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.constant.AppPath;
import com.enigma.shopeymarth.dto.request.AuthRequest;
import com.enigma.shopeymarth.dto.response.CommonResponse;
import com.enigma.shopeymarth.dto.response.LoginResponse;
import com.enigma.shopeymarth.dto.response.RegisterResponse;
import com.enigma.shopeymarth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppPath.AUTHENTICATION)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody AuthRequest authRequest) {
        RegisterResponse response = authService.registerCustomer(authRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        CommonResponse.<RegisterResponse>builder()
                                .statusCode(HttpStatus.CREATED.value())
                                .message("Successfully registered")
                                .data(response)
                                .build()
                );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        LoginResponse loginResponse = authService.login(authRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.<LoginResponse>builder()
                                .statusCode(HttpStatus.OK.value())
                                .message("Successfully Login")
                                .data(loginResponse)
                                .build()
                );
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AuthRequest authRequest) {
        RegisterResponse response = authService.registerAdmin(authRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        CommonResponse.<RegisterResponse>builder()
                                .statusCode(HttpStatus.CREATED.value())
                                .message("Successfully Registered")
                                .data(response)
                                .build()
                );
    }
}
