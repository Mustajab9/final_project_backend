package com.lawencon.community.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.paymentevent.DeleteByPaymentEventIdDtoRes;
import com.lawencon.community.dto.paymentevent.GetAllPaymentEventDtoRes;
import com.lawencon.community.dto.paymentevent.GetByPaymentEventIdDtoRes;
import com.lawencon.community.dto.paymentevent.GetPaymentEventByUserDtoRes;
import com.lawencon.community.dto.paymentevent.InsertPaymentEventDtoRes;
import com.lawencon.community.dto.paymentevent.UpdatePaymentEventDtoReq;
import com.lawencon.community.dto.paymentevent.UpdatePaymentEventDtoRes;

public interface PaymentEventService {
	GetAllPaymentEventDtoRes findAll() throws Exception;
	GetByPaymentEventIdDtoRes findById(String id) throws Exception;
	InsertPaymentEventDtoRes insert(String data, MultipartFile file) throws Exception;
	UpdatePaymentEventDtoRes update(UpdatePaymentEventDtoReq data) throws Exception;
	DeleteByPaymentEventIdDtoRes deleteById(String id) throws Exception;
	GetPaymentEventByUserDtoRes findByUser(String id) throws Exception;
}
