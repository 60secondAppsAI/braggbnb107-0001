package com.braggbnb107.dao;

import java.util.List;

import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.domain.Report;





public interface ReportDAO extends GenericDAO<Report, Integer> {
  
	List<Report> findAll();
	






}


