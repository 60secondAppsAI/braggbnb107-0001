package com.braggbnb107.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.braggbnb107.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.braggbnb107.domain.HostResponse;
import com.braggbnb107.dto.HostResponseDTO;
import com.braggbnb107.dto.HostResponseSearchDTO;
import com.braggbnb107.dto.HostResponsePageDTO;
import com.braggbnb107.service.HostResponseService;
import com.braggbnb107.dto.common.RequestDTO;
import com.braggbnb107.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/hostResponse")
@RestController
public class HostResponseController {

	private final static Logger logger = LoggerFactory.getLogger(HostResponseController.class);

	@Autowired
	HostResponseService hostResponseService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<HostResponse> getAll() {

		List<HostResponse> hostResponses = hostResponseService.findAll();
		
		return hostResponses;	
	}

	@GetMapping(value = "/{hostResponseId}")
	@ResponseBody
	public HostResponseDTO getHostResponse(@PathVariable Integer hostResponseId) {
		
		return (hostResponseService.getHostResponseDTOById(hostResponseId));
	}

 	@RequestMapping(value = "/addHostResponse", method = RequestMethod.POST)
	public ResponseEntity<?> addHostResponse(@RequestBody HostResponseDTO hostResponseDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = hostResponseService.addHostResponse(hostResponseDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/hostResponses")
	public ResponseEntity<HostResponsePageDTO> getHostResponses(HostResponseSearchDTO hostResponseSearchDTO) {
 
		return hostResponseService.getHostResponses(hostResponseSearchDTO);
	}	

	@RequestMapping(value = "/updateHostResponse", method = RequestMethod.POST)
	public ResponseEntity<?> updateHostResponse(@RequestBody HostResponseDTO hostResponseDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = hostResponseService.updateHostResponse(hostResponseDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
