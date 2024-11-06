package com.braggbnb107.dao;

import java.util.List;

import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.domain.IdentityVerification;





public interface IdentityVerificationDAO extends GenericDAO<IdentityVerification, Integer> {
  
	List<IdentityVerification> findAll();
	






}


