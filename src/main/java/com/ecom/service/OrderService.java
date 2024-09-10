package com.ecom.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ecom.model.OrderRequest;
import com.ecom.model.ProductOrder;

public interface OrderService {

	void saveOrder(Integer userid, OrderRequest orderRequest) throws Exception;

	List<ProductOrder> getOrdersByUser(Integer userId);

	ProductOrder updateOrderStatus(Integer id, String status);

	List<ProductOrder> getAllOrders();

	ProductOrder getOrdersByOrderId(String orderId);
	
	Page<ProductOrder> getAllOrdersPagination(Integer pageNo, Integer pageSize);
}
