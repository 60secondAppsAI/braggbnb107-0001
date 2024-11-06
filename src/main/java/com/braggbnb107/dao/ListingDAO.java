package com.braggbnb107.dao;

import java.util.List;

import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.domain.Listing;





public interface ListingDAO extends GenericDAO<Listing, Integer> {
  
	List<Listing> findAll();
	






}


