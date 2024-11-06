package com.braggbnb107.dao;

import java.util.List;

import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.domain.Event;





public interface EventDAO extends GenericDAO<Event, Integer> {
  
	List<Event> findAll();
	






}


