package com.lawencon.community.service;

import com.lawencon.community.dto.paymenteventdetail.GetAllPaymentEventDetailDtoRes;
import com.lawencon.community.dto.paymenteventdetail.GetByPaymentEventDetailIdDtoRes;
import com.lawencon.community.dto.paymenteventdetail.GetPaymentEventDetailByEventDtoRes;
import com.lawencon.community.dto.paymenteventdetail.InsertPaymentEventDetailDtoReq;
import com.lawencon.community.dto.paymenteventdetail.InsertPaymentEventDetailDtoRes;

public interface PaymentEventDetailService {
	public GetAllPaymentEventDetailDtoRes findAll() throws Exception;
	public GetByPaymentEventDetailIdDtoRes findById(String id) throws Exception;
	public InsertPaymentEventDetailDtoRes insert(InsertPaymentEventDetailDtoReq data) throws Exception;
	public GetPaymentEventDetailByEventDtoRes findByEvent(String id) throws Exception;
}
