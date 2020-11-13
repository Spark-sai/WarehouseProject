package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.WhUserTypeNotFoundException;
import com.example.model.WhUserType;
import com.example.repo.WhUserTypeRepository;
import com.example.service.IWhUserTypeService;

@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService {

	@Autowired
	private WhUserTypeRepository repo;
	
	@Override
	public Integer saveWhUserType(WhUserType whUserType) {
		return repo.save(whUserType).getId();
	}

	@Override
	public List<WhUserType> getAllWhUserTypes() {
		return repo.findAll();
	}

	@Override
	public void deleteWhUserType(Integer id) {
		WhUserType whUserType  = getOneWhUserType(id);
		repo.delete(whUserType);
	}
	
	@Override
	public WhUserType getOneWhUserType(Integer id) {
		WhUserType whUserType = repo.findById(id)
				.orElseThrow(()-> new WhUserTypeNotFoundException(
						"Warehouse User Type '"+id+"' Not exist"));
		return whUserType;
	}
	
	@Override
	public void updateWhUserType(WhUserType whUserType) {
		repo.save(whUserType);
	}

	@Override
	public boolean isWhUserMailIdExist(String email) {
		//return repo.getWhUserEmailCount(email)>0?true:false;
		return repo.getWhUserEmailCount(email)>0;
	}
	
}
