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
import com.braggbnb107.dao.IdentityVerificationDAO;
import com.braggbnb107.domain.IdentityVerification;
import com.braggbnb107.dto.IdentityVerificationDTO;
import com.braggbnb107.dto.IdentityVerificationSearchDTO;
import com.braggbnb107.dto.IdentityVerificationPageDTO;
import com.braggbnb107.dto.IdentityVerificationConvertCriteriaDTO;
import com.braggbnb107.dto.common.RequestDTO;
import com.braggbnb107.dto.common.ResultDTO;
import com.braggbnb107.service.IdentityVerificationService;
import com.braggbnb107.util.ControllerUtils;





@Service
public class IdentityVerificationServiceImpl extends GenericServiceImpl<IdentityVerification, Integer> implements IdentityVerificationService {

    private final static Logger logger = LoggerFactory.getLogger(IdentityVerificationServiceImpl.class);

	@Autowired
	IdentityVerificationDAO identityVerificationDao;

	


	@Override
	public GenericDAO<IdentityVerification, Integer> getDAO() {
		return (GenericDAO<IdentityVerification, Integer>) identityVerificationDao;
	}
	
	public List<IdentityVerification> findAll () {
		List<IdentityVerification> identityVerifications = identityVerificationDao.findAll();
		
		return identityVerifications;	
		
	}

	public ResultDTO addIdentityVerification(IdentityVerificationDTO identityVerificationDTO, RequestDTO requestDTO) {

		IdentityVerification identityVerification = new IdentityVerification();

		identityVerification.setIdentityVerificationId(identityVerificationDTO.getIdentityVerificationId());


		identityVerification.setDocumentType(identityVerificationDTO.getDocumentType());


		identityVerification.setDocumentNumber(identityVerificationDTO.getDocumentNumber());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		identityVerification = identityVerificationDao.save(identityVerification);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<IdentityVerification> getAllIdentityVerifications(Pageable pageable) {
		return identityVerificationDao.findAll(pageable);
	}

	public Page<IdentityVerification> getAllIdentityVerifications(Specification<IdentityVerification> spec, Pageable pageable) {
		return identityVerificationDao.findAll(spec, pageable);
	}

	public ResponseEntity<IdentityVerificationPageDTO> getIdentityVerifications(IdentityVerificationSearchDTO identityVerificationSearchDTO) {
	
			Integer identityVerificationId = identityVerificationSearchDTO.getIdentityVerificationId(); 
 			String documentType = identityVerificationSearchDTO.getDocumentType(); 
 			String documentNumber = identityVerificationSearchDTO.getDocumentNumber(); 
 			String sortBy = identityVerificationSearchDTO.getSortBy();
			String sortOrder = identityVerificationSearchDTO.getSortOrder();
			String searchQuery = identityVerificationSearchDTO.getSearchQuery();
			Integer page = identityVerificationSearchDTO.getPage();
			Integer size = identityVerificationSearchDTO.getSize();

	        Specification<IdentityVerification> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, identityVerificationId, "identityVerificationId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, documentType, "documentType"); 
			
			spec = ControllerUtils.andIfNecessary(spec, documentNumber, "documentNumber"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("documentType")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("documentNumber")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<IdentityVerification> identityVerifications = this.getAllIdentityVerifications(spec, pageable);
		
		//System.out.println(String.valueOf(identityVerifications.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(identityVerifications.getTotalPages()));
		
		List<IdentityVerification> identityVerificationsList = identityVerifications.getContent();
		
		IdentityVerificationConvertCriteriaDTO convertCriteria = new IdentityVerificationConvertCriteriaDTO();
		List<IdentityVerificationDTO> identityVerificationDTOs = this.convertIdentityVerificationsToIdentityVerificationDTOs(identityVerificationsList,convertCriteria);
		
		IdentityVerificationPageDTO identityVerificationPageDTO = new IdentityVerificationPageDTO();
		identityVerificationPageDTO.setIdentityVerifications(identityVerificationDTOs);
		identityVerificationPageDTO.setTotalElements(identityVerifications.getTotalElements());
		return ResponseEntity.ok(identityVerificationPageDTO);
	}

	public List<IdentityVerificationDTO> convertIdentityVerificationsToIdentityVerificationDTOs(List<IdentityVerification> identityVerifications, IdentityVerificationConvertCriteriaDTO convertCriteria) {
		
		List<IdentityVerificationDTO> identityVerificationDTOs = new ArrayList<IdentityVerificationDTO>();
		
		for (IdentityVerification identityVerification : identityVerifications) {
			identityVerificationDTOs.add(convertIdentityVerificationToIdentityVerificationDTO(identityVerification,convertCriteria));
		}
		
		return identityVerificationDTOs;

	}
	
	public IdentityVerificationDTO convertIdentityVerificationToIdentityVerificationDTO(IdentityVerification identityVerification, IdentityVerificationConvertCriteriaDTO convertCriteria) {
		
		IdentityVerificationDTO identityVerificationDTO = new IdentityVerificationDTO();
		
		identityVerificationDTO.setIdentityVerificationId(identityVerification.getIdentityVerificationId());

	
		identityVerificationDTO.setDocumentType(identityVerification.getDocumentType());

	
		identityVerificationDTO.setDocumentNumber(identityVerification.getDocumentNumber());

	

		
		return identityVerificationDTO;
	}

	public ResultDTO updateIdentityVerification(IdentityVerificationDTO identityVerificationDTO, RequestDTO requestDTO) {
		
		IdentityVerification identityVerification = identityVerificationDao.getById(identityVerificationDTO.getIdentityVerificationId());

		identityVerification.setIdentityVerificationId(ControllerUtils.setValue(identityVerification.getIdentityVerificationId(), identityVerificationDTO.getIdentityVerificationId()));

		identityVerification.setDocumentType(ControllerUtils.setValue(identityVerification.getDocumentType(), identityVerificationDTO.getDocumentType()));

		identityVerification.setDocumentNumber(ControllerUtils.setValue(identityVerification.getDocumentNumber(), identityVerificationDTO.getDocumentNumber()));



        identityVerification = identityVerificationDao.save(identityVerification);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public IdentityVerificationDTO getIdentityVerificationDTOById(Integer identityVerificationId) {
	
		IdentityVerification identityVerification = identityVerificationDao.getById(identityVerificationId);
			
		
		IdentityVerificationConvertCriteriaDTO convertCriteria = new IdentityVerificationConvertCriteriaDTO();
		return(this.convertIdentityVerificationToIdentityVerificationDTO(identityVerification,convertCriteria));
	}







}
