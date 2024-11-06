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
import com.braggbnb107.dao.CancellationPolicyDAO;
import com.braggbnb107.domain.CancellationPolicy;
import com.braggbnb107.dto.CancellationPolicyDTO;
import com.braggbnb107.dto.CancellationPolicySearchDTO;
import com.braggbnb107.dto.CancellationPolicyPageDTO;
import com.braggbnb107.dto.CancellationPolicyConvertCriteriaDTO;
import com.braggbnb107.dto.common.RequestDTO;
import com.braggbnb107.dto.common.ResultDTO;
import com.braggbnb107.service.CancellationPolicyService;
import com.braggbnb107.util.ControllerUtils;





@Service
public class CancellationPolicyServiceImpl extends GenericServiceImpl<CancellationPolicy, Integer> implements CancellationPolicyService {

    private final static Logger logger = LoggerFactory.getLogger(CancellationPolicyServiceImpl.class);

	@Autowired
	CancellationPolicyDAO cancellationPolicyDao;

	


	@Override
	public GenericDAO<CancellationPolicy, Integer> getDAO() {
		return (GenericDAO<CancellationPolicy, Integer>) cancellationPolicyDao;
	}
	
	public List<CancellationPolicy> findAll () {
		List<CancellationPolicy> cancellationPolicys = cancellationPolicyDao.findAll();
		
		return cancellationPolicys;	
		
	}

	public ResultDTO addCancellationPolicy(CancellationPolicyDTO cancellationPolicyDTO, RequestDTO requestDTO) {

		CancellationPolicy cancellationPolicy = new CancellationPolicy();

		cancellationPolicy.setCancellationPolicyId(cancellationPolicyDTO.getCancellationPolicyId());


		cancellationPolicy.setName(cancellationPolicyDTO.getName());


		cancellationPolicy.setDescription(cancellationPolicyDTO.getDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		cancellationPolicy = cancellationPolicyDao.save(cancellationPolicy);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CancellationPolicy> getAllCancellationPolicys(Pageable pageable) {
		return cancellationPolicyDao.findAll(pageable);
	}

	public Page<CancellationPolicy> getAllCancellationPolicys(Specification<CancellationPolicy> spec, Pageable pageable) {
		return cancellationPolicyDao.findAll(spec, pageable);
	}

	public ResponseEntity<CancellationPolicyPageDTO> getCancellationPolicys(CancellationPolicySearchDTO cancellationPolicySearchDTO) {
	
			Integer cancellationPolicyId = cancellationPolicySearchDTO.getCancellationPolicyId(); 
 			String name = cancellationPolicySearchDTO.getName(); 
 			String description = cancellationPolicySearchDTO.getDescription(); 
 			String sortBy = cancellationPolicySearchDTO.getSortBy();
			String sortOrder = cancellationPolicySearchDTO.getSortOrder();
			String searchQuery = cancellationPolicySearchDTO.getSearchQuery();
			Integer page = cancellationPolicySearchDTO.getPage();
			Integer size = cancellationPolicySearchDTO.getSize();

	        Specification<CancellationPolicy> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, cancellationPolicyId, "cancellationPolicyId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<CancellationPolicy> cancellationPolicys = this.getAllCancellationPolicys(spec, pageable);
		
		//System.out.println(String.valueOf(cancellationPolicys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(cancellationPolicys.getTotalPages()));
		
		List<CancellationPolicy> cancellationPolicysList = cancellationPolicys.getContent();
		
		CancellationPolicyConvertCriteriaDTO convertCriteria = new CancellationPolicyConvertCriteriaDTO();
		List<CancellationPolicyDTO> cancellationPolicyDTOs = this.convertCancellationPolicysToCancellationPolicyDTOs(cancellationPolicysList,convertCriteria);
		
		CancellationPolicyPageDTO cancellationPolicyPageDTO = new CancellationPolicyPageDTO();
		cancellationPolicyPageDTO.setCancellationPolicys(cancellationPolicyDTOs);
		cancellationPolicyPageDTO.setTotalElements(cancellationPolicys.getTotalElements());
		return ResponseEntity.ok(cancellationPolicyPageDTO);
	}

	public List<CancellationPolicyDTO> convertCancellationPolicysToCancellationPolicyDTOs(List<CancellationPolicy> cancellationPolicys, CancellationPolicyConvertCriteriaDTO convertCriteria) {
		
		List<CancellationPolicyDTO> cancellationPolicyDTOs = new ArrayList<CancellationPolicyDTO>();
		
		for (CancellationPolicy cancellationPolicy : cancellationPolicys) {
			cancellationPolicyDTOs.add(convertCancellationPolicyToCancellationPolicyDTO(cancellationPolicy,convertCriteria));
		}
		
		return cancellationPolicyDTOs;

	}
	
	public CancellationPolicyDTO convertCancellationPolicyToCancellationPolicyDTO(CancellationPolicy cancellationPolicy, CancellationPolicyConvertCriteriaDTO convertCriteria) {
		
		CancellationPolicyDTO cancellationPolicyDTO = new CancellationPolicyDTO();
		
		cancellationPolicyDTO.setCancellationPolicyId(cancellationPolicy.getCancellationPolicyId());

	
		cancellationPolicyDTO.setName(cancellationPolicy.getName());

	
		cancellationPolicyDTO.setDescription(cancellationPolicy.getDescription());

	

		
		return cancellationPolicyDTO;
	}

	public ResultDTO updateCancellationPolicy(CancellationPolicyDTO cancellationPolicyDTO, RequestDTO requestDTO) {
		
		CancellationPolicy cancellationPolicy = cancellationPolicyDao.getById(cancellationPolicyDTO.getCancellationPolicyId());

		cancellationPolicy.setCancellationPolicyId(ControllerUtils.setValue(cancellationPolicy.getCancellationPolicyId(), cancellationPolicyDTO.getCancellationPolicyId()));

		cancellationPolicy.setName(ControllerUtils.setValue(cancellationPolicy.getName(), cancellationPolicyDTO.getName()));

		cancellationPolicy.setDescription(ControllerUtils.setValue(cancellationPolicy.getDescription(), cancellationPolicyDTO.getDescription()));



        cancellationPolicy = cancellationPolicyDao.save(cancellationPolicy);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CancellationPolicyDTO getCancellationPolicyDTOById(Integer cancellationPolicyId) {
	
		CancellationPolicy cancellationPolicy = cancellationPolicyDao.getById(cancellationPolicyId);
			
		
		CancellationPolicyConvertCriteriaDTO convertCriteria = new CancellationPolicyConvertCriteriaDTO();
		return(this.convertCancellationPolicyToCancellationPolicyDTO(cancellationPolicy,convertCriteria));
	}







}
