package com.braggbnb107.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb107.domain.Message;
import com.braggbnb107.dto.MessageDTO;
import com.braggbnb107.dto.MessageSearchDTO;
import com.braggbnb107.dto.MessagePageDTO;
import com.braggbnb107.dto.MessageConvertCriteriaDTO;
import com.braggbnb107.service.GenericService;
import com.braggbnb107.dto.common.RequestDTO;
import com.braggbnb107.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MessageService extends GenericService<Message, Integer> {

	List<Message> findAll();

	ResultDTO addMessage(MessageDTO messageDTO, RequestDTO requestDTO);

	ResultDTO updateMessage(MessageDTO messageDTO, RequestDTO requestDTO);

    Page<Message> getAllMessages(Pageable pageable);

    Page<Message> getAllMessages(Specification<Message> spec, Pageable pageable);

	ResponseEntity<MessagePageDTO> getMessages(MessageSearchDTO messageSearchDTO);
	
	List<MessageDTO> convertMessagesToMessageDTOs(List<Message> messages, MessageConvertCriteriaDTO convertCriteria);

	MessageDTO getMessageDTOById(Integer messageId);







}





