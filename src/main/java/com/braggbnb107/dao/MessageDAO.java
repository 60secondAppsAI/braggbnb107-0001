package com.braggbnb107.dao;

import java.util.List;

import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.domain.Message;





public interface MessageDAO extends GenericDAO<Message, Integer> {
  
	List<Message> findAll();
	






}


