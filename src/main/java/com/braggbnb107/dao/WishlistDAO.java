package com.braggbnb107.dao;

import java.util.List;

import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.domain.Wishlist;





public interface WishlistDAO extends GenericDAO<Wishlist, Integer> {
  
	List<Wishlist> findAll();
	






}


