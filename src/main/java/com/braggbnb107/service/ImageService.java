package com.braggbnb107.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb107.domain.Image;
import com.braggbnb107.dto.ImageDTO;
import com.braggbnb107.dto.ImageSearchDTO;
import com.braggbnb107.dto.ImagePageDTO;
import com.braggbnb107.dto.ImageConvertCriteriaDTO;
import com.braggbnb107.service.GenericService;
import com.braggbnb107.dto.common.RequestDTO;
import com.braggbnb107.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ImageService extends GenericService<Image, Integer> {

	List<Image> findAll();

	ResultDTO addImage(ImageDTO imageDTO, RequestDTO requestDTO);

	ResultDTO updateImage(ImageDTO imageDTO, RequestDTO requestDTO);

    Page<Image> getAllImages(Pageable pageable);

    Page<Image> getAllImages(Specification<Image> spec, Pageable pageable);

	ResponseEntity<ImagePageDTO> getImages(ImageSearchDTO imageSearchDTO);
	
	List<ImageDTO> convertImagesToImageDTOs(List<Image> images, ImageConvertCriteriaDTO convertCriteria);

	ImageDTO getImageDTOById(Integer imageId);







}





