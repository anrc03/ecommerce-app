package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
}
