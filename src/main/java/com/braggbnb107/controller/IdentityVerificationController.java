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

import com.braggbnb107.domain.IdentityVerification;
import com.braggbnb107.dto.IdentityVerificationDTO;
import com.braggbnb107.dto.IdentityVerificationSearchDTO;
import com.braggbnb107.dto.IdentityVerificationPageDTO;
import com.braggbnb107.service.IdentityVerificationService;
import com.braggbnb107.dto.common.RequestDTO;
import com.braggbnb107.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/identityVerification")
@RestController
public class IdentityVerificationController {

	private final static Logger logger = LoggerFactory.getLogger(IdentityVerificationController.class);

	@Autowired
	IdentityVerificationService identityVerificationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<IdentityVerification> getAll() {

		List<IdentityVerification> identityVerifications = identityVerificationService.findAll();
		
		return identityVerifications;	
	}

	@GetMapping(value = "/{identityVerificationId}")
	@ResponseBody
	public IdentityVerificationDTO getIdentityVerification(@PathVariable Integer identityVerificationId) {
		
		return (identityVerificationService.getIdentityVerificationDTOById(identityVerificationId));
	}

 	@RequestMapping(value = "/addIdentityVerification", method = RequestMethod.POST)
	public ResponseEntity<?> addIdentityVerification(@RequestBody IdentityVerificationDTO identityVerificationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = identityVerificationService.addIdentityVerification(identityVerificationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/identityVerifications")
	public ResponseEntity<IdentityVerificationPageDTO> getIdentityVerifications(IdentityVerificationSearchDTO identityVerificationSearchDTO) {
 
		return identityVerificationService.getIdentityVerifications(identityVerificationSearchDTO);
	}	

	@RequestMapping(value = "/updateIdentityVerification", method = RequestMethod.POST)
	public ResponseEntity<?> updateIdentityVerification(@RequestBody IdentityVerificationDTO identityVerificationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = identityVerificationService.updateIdentityVerification(identityVerificationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
