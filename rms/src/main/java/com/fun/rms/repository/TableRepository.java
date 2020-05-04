package com.fun.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fun.rms.model.Table;

@Repository
public interface TableRepository  extends JpaRepository<Table, Integer> {
	Table findByTableNumber(Integer tableNumber);
}
