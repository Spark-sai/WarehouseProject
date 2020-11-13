package com.example.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.model.OrderMethod;
 
public interface IOrderMethodService {

	Integer saveOrderMethod(OrderMethod om);
	void updateOrderMethod(OrderMethod om);
	
	void deleteOrderMethod(Integer id);
	Optional<OrderMethod> getOneOrderMethod(Integer id);
	
	List<OrderMethod> getAllOrderMethods();
	
	Map<Integer,String> getOrderMethodIdAndCodeByMode(String mode);
}
