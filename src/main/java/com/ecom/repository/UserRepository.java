package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls, Integer> {

	UserDtls findByEmail(String email);

	List<UserDtls> findByRole(String role);

	UserDtls findByResetToken(String token);

	Boolean existsByEmail(String email);
}
