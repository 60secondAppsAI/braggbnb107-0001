package com.braggbnb107.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb107.domain.IdentityVerification;
import com.braggbnb107.dto.IdentityVerificationDTO;
import com.braggbnb107.dto.IdentityVerificationSearchDTO;
import com.braggbnb107.dto.IdentityVerificationPageDTO;
import com.braggbnb107.dto.IdentityVerificationConvertCriteriaDTO;
import com.braggbnb107.service.GenericService;
import com.braggbnb107.dto.common.RequestDTO;
import com.braggbnb107.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface IdentityVerificationService extends GenericService<IdentityVerification, Integer> {

	List<IdentityVerification> findAll();

	ResultDTO addIdentityVerification(IdentityVerificationDTO identityVerificationDTO, RequestDTO requestDTO);

	ResultDTO updateIdentityVerification(IdentityVerificationDTO identityVerificationDTO, RequestDTO requestDTO);

    Page<IdentityVerification> getAllIdentityVerifications(Pageable pageable);

    Page<IdentityVerification> getAllIdentityVerifications(Specification<IdentityVerification> spec, Pageable pageable);

	ResponseEntity<IdentityVerificationPageDTO> getIdentityVerifications(IdentityVerificationSearchDTO identityVerificationSearchDTO);
	
	List<IdentityVerificationDTO> convertIdentityVerificationsToIdentityVerificationDTOs(List<IdentityVerification> identityVerifications, IdentityVerificationConvertCriteriaDTO convertCriteria);

	IdentityVerificationDTO getIdentityVerificationDTOById(Integer identityVerificationId);







}





