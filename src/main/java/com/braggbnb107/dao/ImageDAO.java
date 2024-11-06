package com.braggbnb107.dao;

import java.util.List;

import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.domain.Image;





public interface ImageDAO extends GenericDAO<Image, Integer> {
  
	List<Image> findAll();
	






}


