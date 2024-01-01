package com.enigma.shopeymarth.repository;

import com.enigma.shopeymarth.constant.ERole;
import com.enigma.shopeymarth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
