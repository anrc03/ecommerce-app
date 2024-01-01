package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.dto.response.AdminResponse;
import com.enigma.shopeymarth.entity.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    AdminResponse createNewAdmin(Admin request);
}
