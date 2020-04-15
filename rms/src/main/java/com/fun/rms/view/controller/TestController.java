package com.fun.rms.view.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {
	
	ArrayList<TestDish> testDishes = new ArrayList<TestDish>();	
	
	@GetMapping()
	public ArrayList<TestDish> getAll() {
		testDishes.clear();
		testDishes.add(new TestDish(1, "Dish one", 123, "test"));
		testDishes.add(new TestDish(2, "Dish two", 123, "test"));
		testDishes.add(new TestDish(3, "Dish three", 123, "test"));
		testDishes.add(new TestDish(4, "Dish four", 123, "test"));
		testDishes.add(new TestDish(5, "Dish five", 123, "test"));
		
		return testDishes;
	}
	
}

class TestDish {
	private Integer id;
	private String name;
	private Integer price;
	private String picture;
	
	public TestDish(Integer id, String name, Integer price, String picture) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.picture = picture;
	}

}