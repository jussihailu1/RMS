package com.fun.rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fun.rms.model.Table;

@Repository
public interface TableRepository  extends JpaRepository<Table, Integer> {
	Table findByTableNumber(Integer tableNumber);
	
	@Query("FROM Table WHERE session = null")
	List<Table> findAvailableTables();
}
