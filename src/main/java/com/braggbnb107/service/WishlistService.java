package com.braggbnb107.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb107.domain.Wishlist;
import com.braggbnb107.dto.WishlistDTO;
import com.braggbnb107.dto.WishlistSearchDTO;
import com.braggbnb107.dto.WishlistPageDTO;
import com.braggbnb107.dto.WishlistConvertCriteriaDTO;
import com.braggbnb107.service.GenericService;
import com.braggbnb107.dto.common.RequestDTO;
import com.braggbnb107.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WishlistService extends GenericService<Wishlist, Integer> {

	List<Wishlist> findAll();

	ResultDTO addWishlist(WishlistDTO wishlistDTO, RequestDTO requestDTO);

	ResultDTO updateWishlist(WishlistDTO wishlistDTO, RequestDTO requestDTO);

    Page<Wishlist> getAllWishlists(Pageable pageable);

    Page<Wishlist> getAllWishlists(Specification<Wishlist> spec, Pageable pageable);

	ResponseEntity<WishlistPageDTO> getWishlists(WishlistSearchDTO wishlistSearchDTO);
	
	List<WishlistDTO> convertWishlistsToWishlistDTOs(List<Wishlist> wishlists, WishlistConvertCriteriaDTO convertCriteria);

	WishlistDTO getWishlistDTOById(Integer wishlistId);







}





