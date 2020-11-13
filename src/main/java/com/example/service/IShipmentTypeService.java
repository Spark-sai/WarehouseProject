package com.example.service;

import java.util.List;

import com.example.model.ShipmentType;

public interface IShipmentTypeService {

	Integer saveShipmentType(ShipmentType st);
	void updateShipmentType(ShipmentType st);
	void deleteShipmentType(Integer id);
	ShipmentType getOneShipmentType(Integer id);
	List<ShipmentType> getAllShipmentTypes();
	
	boolean isShipmentTypeCodeExist(String code);
	List<Object[]> getShipmentModeAndCount();
}
