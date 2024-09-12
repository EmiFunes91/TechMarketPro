package com.ecom.service;

import java.util.List;

import com.ecom.model.Cart;

public interface CartService {

	/**
	 * Guarda el carrito para un producto y un usuario dados.
	 *
	 * @param productId El ID del producto.
	 * @param userId El ID del usuario.
	 * @return El objeto Cart guardado.
	 */
	Cart saveCart(Integer productId, Integer userId);

	/**
	 * Recupera todos los carritos asociados a un usuario.
	 *
	 * @param userId El ID del usuario.
	 * @return Lista de carritos asociados al usuario.
	 */
	List<Cart> getCartsByUser(Integer userId);

	/**
	 * Obtiene el conteo de carritos asociados a un usuario.
	 *
	 * @param userId El ID del usuario.
	 * @return El número total de carritos asociados al usuario.
	 */
	Integer getCountCart(Integer userId);

	/**
	 * Actualiza la cantidad de productos en un carrito.
	 *
	 * @param sy Indica si se incrementa o disminuye la cantidad ("in" para incrementar, "de" para disminuir).
	 * @param cid El ID del carrito a actualizar.
	 */
	void updateQuantity(String sy, Integer cid);

}

