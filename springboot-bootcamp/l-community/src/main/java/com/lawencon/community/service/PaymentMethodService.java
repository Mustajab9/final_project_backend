package com.lawencon.community.service;

import com.lawencon.community.dto.paymentmethod.DeleteByPaymentMethodIdDtoRes;
import com.lawencon.community.dto.paymentmethod.GetAllPaymentMethodDtoRes;
import com.lawencon.community.dto.paymentmethod.GetByPaymentMethodIdDtoRes;
import com.lawencon.community.dto.paymentmethod.InsertPaymentMethodDtoReq;
import com.lawencon.community.dto.paymentmethod.InsertPaymentMethodDtoRes;
import com.lawencon.community.dto.paymentmethod.UpdatePaymentMethodDtoReq;
import com.lawencon.community.dto.paymentmethod.UpdatePaymentMethodDtoRes;

public interface PaymentMethodService {
	public GetAllPaymentMethodDtoRes findAll() throws Exception;
	public GetByPaymentMethodIdDtoRes findById(String id) throws Exception;
	public InsertPaymentMethodDtoRes insert(InsertPaymentMethodDtoReq data) throws Exception;
	public UpdatePaymentMethodDtoRes update(UpdatePaymentMethodDtoReq data) throws Exception;
	public DeleteByPaymentMethodIdDtoRes deleteById(String id) throws Exception;
}
