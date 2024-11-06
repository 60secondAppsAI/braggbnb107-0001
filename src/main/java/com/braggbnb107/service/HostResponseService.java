package com.braggbnb107.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb107.domain.HostResponse;
import com.braggbnb107.dto.HostResponseDTO;
import com.braggbnb107.dto.HostResponseSearchDTO;
import com.braggbnb107.dto.HostResponsePageDTO;
import com.braggbnb107.dto.HostResponseConvertCriteriaDTO;
import com.braggbnb107.service.GenericService;
import com.braggbnb107.dto.common.RequestDTO;
import com.braggbnb107.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface HostResponseService extends GenericService<HostResponse, Integer> {

	List<HostResponse> findAll();

	ResultDTO addHostResponse(HostResponseDTO hostResponseDTO, RequestDTO requestDTO);

	ResultDTO updateHostResponse(HostResponseDTO hostResponseDTO, RequestDTO requestDTO);

    Page<HostResponse> getAllHostResponses(Pageable pageable);

    Page<HostResponse> getAllHostResponses(Specification<HostResponse> spec, Pageable pageable);

	ResponseEntity<HostResponsePageDTO> getHostResponses(HostResponseSearchDTO hostResponseSearchDTO);
	
	List<HostResponseDTO> convertHostResponsesToHostResponseDTOs(List<HostResponse> hostResponses, HostResponseConvertCriteriaDTO convertCriteria);

	HostResponseDTO getHostResponseDTOById(Integer hostResponseId);







}





