package com.braggbnb107.dao;

import java.util.List;

import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.domain.Property;





public interface PropertyDAO extends GenericDAO<Property, Integer> {
  
	List<Property> findAll();
	






}


