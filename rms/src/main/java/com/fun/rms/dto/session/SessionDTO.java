package com.fun.rms.dto.session;

public class SessionDTO {
	private Integer customers;
	private Integer tableNumber;
	private Integer ReservationId;

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

	public Integer getReservationId() {
		return ReservationId;
	}

	public void setReservationId(Integer reservationId) {
		ReservationId = reservationId;
	}
}
