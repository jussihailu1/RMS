package com.fun.rms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fun.rms.repository.TableRepository;
import com.fun.rms.model.Table;

@Service
@Transactional
public class TableService {

	@Autowired
	private TableRepository repo;
	
	public List<Table> getAll(){
		return repo.findAll();
	}
	
	public void update(Table table) {
		repo.save(table);
	}
	
	public Table findByTableNumber(Integer tableNumber) {
		return repo.findByTableNumber(tableNumber);
	}
}
