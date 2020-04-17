package com.fun.rms.persistence.model;

import java.time.Duration;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="sessions")
public class SessionDAO {
	private Integer id;
	private Integer customers;
	private LocalTime entry_time;
	private Duration duration;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomers() {
		return customers;
	}

	public void setCustomers(Integer customers) {
		this.customers = customers;
	}

	public LocalTime getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(LocalTime entry_time) {
		this.entry_time = entry_time;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}
