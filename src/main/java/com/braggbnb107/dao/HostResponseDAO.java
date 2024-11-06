package com.braggbnb107.dao;

import java.util.List;

import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.domain.HostResponse;





public interface HostResponseDAO extends GenericDAO<HostResponse, Integer> {
  
	List<HostResponse> findAll();
	






}


