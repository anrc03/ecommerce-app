package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.request.AuthRequest;
import com.enigma.shopeymarth.dto.response.AdminResponse;
import com.enigma.shopeymarth.entity.Admin;
import com.enigma.shopeymarth.repository.AdminRepository;
import com.enigma.shopeymarth.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public AdminResponse createNewAdmin(Admin request) {
        Admin admin = adminRepository.saveAndFlush(request);
        return AdminResponse.builder()
                .id(admin.getId())
                .name(admin.getName())
                .phoneNumber(admin.getPhoneNumber())
                .build();
    }
}
