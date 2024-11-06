package com.braggbnb107.dao;

import java.util.List;

import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.domain.Booking;





public interface BookingDAO extends GenericDAO<Booking, Integer> {
  
	List<Booking> findAll();
	






}


