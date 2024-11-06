package com.braggbnb107.dao;

import java.util.List;

import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


