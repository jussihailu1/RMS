package com.fun.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fun.rms.model.Table;

public interface TableRepository  extends JpaRepository<Table, Integer> {
	Table findByTableNumber(Integer tableNumber);
}
