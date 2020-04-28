package com.fun.rms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fun.rms.service.TableService;
import com.fun.rms.model.Session;
import com.fun.rms.model.Table;

@RestController
@RequestMapping("tables")
@CrossOrigin(origins = "http://localhost:4200")
public class TableController {

	@Autowired
	private TableService service;

	@GetMapping
	public List<Table> getAll() {
		return service.getAll();
	}
}
