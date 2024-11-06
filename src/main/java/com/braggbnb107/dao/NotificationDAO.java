package com.braggbnb107.dao;

import java.util.List;

import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.domain.Notification;





public interface NotificationDAO extends GenericDAO<Notification, Integer> {
  
	List<Notification> findAll();
	






}


