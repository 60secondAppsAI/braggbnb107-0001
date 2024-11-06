package com.braggbnb107.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbnb107.dao.GenericDAO;
import com.braggbnb107.service.GenericService;
import com.braggbnb107.service.impl.GenericServiceImpl;
import com.braggbnb107.dao.HostResponseDAO;
import com.braggbnb107.domain.HostResponse;
import com.braggbnb107.dto.HostResponseDTO;
import com.braggbnb107.dto.HostResponseSearchDTO;
import com.braggbnb107.dto.HostResponsePageDTO;
import com.braggbnb107.dto.HostResponseConvertCriteriaDTO;
import com.braggbnb107.dto.common.RequestDTO;
import com.braggbnb107.dto.common.ResultDTO;
import com.braggbnb107.service.HostResponseService;
import com.braggbnb107.util.ControllerUtils;





@Service
public class HostResponseServiceImpl extends GenericServiceImpl<HostResponse, Integer> implements HostResponseService {

    private final static Logger logger = LoggerFactory.getLogger(HostResponseServiceImpl.class);

	@Autowired
	HostResponseDAO hostResponseDao;

	


	@Override
	public GenericDAO<HostResponse, Integer> getDAO() {
		return (GenericDAO<HostResponse, Integer>) hostResponseDao;
	}
	
	public List<HostResponse> findAll () {
		List<HostResponse> hostResponses = hostResponseDao.findAll();
		
		return hostResponses;	
		
	}

	public ResultDTO addHostResponse(HostResponseDTO hostResponseDTO, RequestDTO requestDTO) {

		HostResponse hostResponse = new HostResponse();

		hostResponse.setHostResponseId(hostResponseDTO.getHostResponseId());


		hostResponse.setResponse(hostResponseDTO.getResponse());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		hostResponse = hostResponseDao.save(hostResponse);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<HostResponse> getAllHostResponses(Pageable pageable) {
		return hostResponseDao.findAll(pageable);
	}

	public Page<HostResponse> getAllHostResponses(Specification<HostResponse> spec, Pageable pageable) {
		return hostResponseDao.findAll(spec, pageable);
	}

	public ResponseEntity<HostResponsePageDTO> getHostResponses(HostResponseSearchDTO hostResponseSearchDTO) {
	
			Integer hostResponseId = hostResponseSearchDTO.getHostResponseId(); 
 			String response = hostResponseSearchDTO.getResponse(); 
 			String sortBy = hostResponseSearchDTO.getSortBy();
			String sortOrder = hostResponseSearchDTO.getSortOrder();
			String searchQuery = hostResponseSearchDTO.getSearchQuery();
			Integer page = hostResponseSearchDTO.getPage();
			Integer size = hostResponseSearchDTO.getSize();

	        Specification<HostResponse> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, hostResponseId, "hostResponseId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, response, "response"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("response")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<HostResponse> hostResponses = this.getAllHostResponses(spec, pageable);
		
		//System.out.println(String.valueOf(hostResponses.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(hostResponses.getTotalPages()));
		
		List<HostResponse> hostResponsesList = hostResponses.getContent();
		
		HostResponseConvertCriteriaDTO convertCriteria = new HostResponseConvertCriteriaDTO();
		List<HostResponseDTO> hostResponseDTOs = this.convertHostResponsesToHostResponseDTOs(hostResponsesList,convertCriteria);
		
		HostResponsePageDTO hostResponsePageDTO = new HostResponsePageDTO();
		hostResponsePageDTO.setHostResponses(hostResponseDTOs);
		hostResponsePageDTO.setTotalElements(hostResponses.getTotalElements());
		return ResponseEntity.ok(hostResponsePageDTO);
	}

	public List<HostResponseDTO> convertHostResponsesToHostResponseDTOs(List<HostResponse> hostResponses, HostResponseConvertCriteriaDTO convertCriteria) {
		
		List<HostResponseDTO> hostResponseDTOs = new ArrayList<HostResponseDTO>();
		
		for (HostResponse hostResponse : hostResponses) {
			hostResponseDTOs.add(convertHostResponseToHostResponseDTO(hostResponse,convertCriteria));
		}
		
		return hostResponseDTOs;

	}
	
	public HostResponseDTO convertHostResponseToHostResponseDTO(HostResponse hostResponse, HostResponseConvertCriteriaDTO convertCriteria) {
		
		HostResponseDTO hostResponseDTO = new HostResponseDTO();
		
		hostResponseDTO.setHostResponseId(hostResponse.getHostResponseId());

	
		hostResponseDTO.setResponse(hostResponse.getResponse());

	

		
		return hostResponseDTO;
	}

	public ResultDTO updateHostResponse(HostResponseDTO hostResponseDTO, RequestDTO requestDTO) {
		
		HostResponse hostResponse = hostResponseDao.getById(hostResponseDTO.getHostResponseId());

		hostResponse.setHostResponseId(ControllerUtils.setValue(hostResponse.getHostResponseId(), hostResponseDTO.getHostResponseId()));

		hostResponse.setResponse(ControllerUtils.setValue(hostResponse.getResponse(), hostResponseDTO.getResponse()));



        hostResponse = hostResponseDao.save(hostResponse);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public HostResponseDTO getHostResponseDTOById(Integer hostResponseId) {
	
		HostResponse hostResponse = hostResponseDao.getById(hostResponseId);
			
		
		HostResponseConvertCriteriaDTO convertCriteria = new HostResponseConvertCriteriaDTO();
		return(this.convertHostResponseToHostResponseDTO(hostResponse,convertCriteria));
	}







}
