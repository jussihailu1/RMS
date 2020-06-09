package com.fun.rms;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fun.rms.factory.ModelFactory;
import com.fun.rms.model.Reservation;
import com.fun.rms.repository.ReservationRepository;
import com.fun.rms.service.ReservationService;

@SpringBootTest
public class ReservationServiceTests {

	@Mock
	private ReservationRepository reservationRepository;

	@Mock
	private ModelFactory modelFactory;

	@InjectMocks
	private ReservationService reservationService;

	@Test
	public void findByNameTest() {
		String nameToFind = "Thomas";
		List<Reservation> mockReservations = new ArrayList<Reservation>();

		Reservation mr1 = new Reservation(1, LocalDate.now().plusWeeks(1), LocalTime.now().plusHours(1), "Emma");
		mockReservations.add(mr1);
		Reservation mr2 = new Reservation(2, LocalDate.now().plusWeeks(2), LocalTime.now().plusHours(2), nameToFind);
		mockReservations.add(mr2);
		Reservation mr3 = new Reservation(3, LocalDate.now().plusWeeks(3), LocalTime.now().plusHours(3), nameToFind);
		mockReservations.add(mr3);
		Reservation mr4 = new Reservation(4, LocalDate.now().plusWeeks(4), LocalTime.now().plusHours(4), "Maya");
		mockReservations.add(mr4);
		Reservation mr5 = new Reservation(5, LocalDate.now().plusWeeks(5), LocalTime.now().plusHours(5), "Nigel");
		mockReservations.add(mr5);

		List<Reservation> expectedReservations = new ArrayList<>();
		Reservation er1 = new Reservation(2, LocalDate.now().plusWeeks(2), LocalTime.now().plusHours(2), nameToFind);
		expectedReservations.add(er1);
		Reservation er2 = new Reservation(3, LocalDate.now().plusWeeks(3), LocalTime.now().plusHours(3), nameToFind);
		expectedReservations.add(er2);

		when(reservationRepository.findByName(nameToFind)).thenReturn(expectedReservations);

		List<Reservation> foundReservations = reservationService.findByName(nameToFind);

		assertEquals(2, foundReservations.size());
		assertEquals(mockReservations.get(1).getCustomers(), foundReservations.get(0).getCustomers());
		assertEquals(mockReservations.get(2).getDate(), foundReservations.get(1).getDate());
		verify(reservationRepository, times(1)).findByName(nameToFind);
	}
}
