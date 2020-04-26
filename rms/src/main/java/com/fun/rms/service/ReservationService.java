package com.fun.rms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fun.rms.repository.ReservationRepository;

@Service
@Transactional
public class ReservationService {

	@Autowired
	private ReservationRepository repo;
}
