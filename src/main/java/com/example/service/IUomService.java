package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.model.Uom;

public interface IUomService {

	public Integer saveUom(Uom uom);
	public List<Uom> getAllUoms();
	
	public void updateUom(Uom uom);
	public void deleteUom(Integer id);
	public Uom getOneUom(Integer id);
	
	public boolean isUomModelExist(String uomModel);
	public List<Object[]> getUomTypeAndCount();
	
	public Map<Integer,String> getUomIdAndModel();
}
