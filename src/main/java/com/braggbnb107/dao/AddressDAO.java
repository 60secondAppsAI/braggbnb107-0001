package com.braggbnb107.dao;

import java.util.List;

import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.domain.Address;





public interface AddressDAO extends GenericDAO<Address, Integer> {
  
	List<Address> findAll();
	






}


