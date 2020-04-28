package com.fun.rms.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sessions")
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer customers;

	@Column(name = "tablenumber")
	private Integer tableNumber;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reservationid")
	private Reservation reservation;
	
	private LocalTime start;
	
	@Column(name = "durationinminutes")
	private Long durationInMinutes;
	
	private LocalDate date;

	public Session() {
	}

	public Session(Integer customers, Integer tableNumber, LocalTime start, LocalDate date) {
		this.customers = customers;
		this.tableNumber = tableNumber;
		this.start = start;
		this.date = date;
	}

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

	public Integer getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(Integer tableNumber) {
		this.tableNumber = tableNumber;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public Long getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDuration(Long durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setDurationInMinutes(Long durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

}
