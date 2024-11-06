package com.braggbnb107.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb107.domain.User;
import com.braggbnb107.dto.UserDTO;
import com.braggbnb107.dto.UserSearchDTO;
import com.braggbnb107.dto.UserPageDTO;
import com.braggbnb107.dto.UserConvertCriteriaDTO;
import com.braggbnb107.service.GenericService;
import com.braggbnb107.dto.common.RequestDTO;
import com.braggbnb107.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface UserService extends GenericService<User, Integer> {

	List<User> findAll();

	ResultDTO addUser(UserDTO userDTO, RequestDTO requestDTO);

	ResultDTO updateUser(UserDTO userDTO, RequestDTO requestDTO);

    Page<User> getAllUsers(Pageable pageable);

    Page<User> getAllUsers(Specification<User> spec, Pageable pageable);

	ResponseEntity<UserPageDTO> getUsers(UserSearchDTO userSearchDTO);
	
	List<UserDTO> convertUsersToUserDTOs(List<User> users, UserConvertCriteriaDTO convertCriteria);

	UserDTO getUserDTOById(Integer userId);







}





