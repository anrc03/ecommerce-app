package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.dto.request.AuthRequest;
import com.enigma.shopeymarth.dto.response.LoginResponse;
import com.enigma.shopeymarth.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(AuthRequest request);
    LoginResponse login(AuthRequest authRequest);
    RegisterResponse registerAdmin(AuthRequest authRequest);
}
