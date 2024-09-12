package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	// Método para encontrar un carrito por producto y usuario
	Cart findByProductIdAndUserId(Integer productId, Integer userId);

	// Método para contar los carritos por usuario
	Integer countByUserId(Integer userId);

	// Método para encontrar todos los carritos de un usuario
	List<Cart> findByUserId(Integer userId);

}
