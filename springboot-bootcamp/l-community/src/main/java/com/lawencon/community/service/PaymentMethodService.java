package com.lawencon.community.service;

import com.lawencon.community.dto.paymentmethod.DeleteByPaymentMethodIdDtoRes;
import com.lawencon.community.dto.paymentmethod.GetAllPaymentMethodDtoRes;
import com.lawencon.community.dto.paymentmethod.GetByPaymentMethodIdDtoRes;
import com.lawencon.community.dto.paymentmethod.InsertPaymentMethodDtoReq;
import com.lawencon.community.dto.paymentmethod.InsertPaymentMethodDtoRes;
import com.lawencon.community.dto.paymentmethod.UpdatePaymentMethodDtoReq;
import com.lawencon.community.dto.paymentmethod.UpdatePaymentMethodDtoRes;

public interface PaymentMethodService {
	GetAllPaymentMethodDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetByPaymentMethodIdDtoRes findById(String id) throws Exception;
	InsertPaymentMethodDtoRes insert(InsertPaymentMethodDtoReq data) throws Exception;
	UpdatePaymentMethodDtoRes update(UpdatePaymentMethodDtoReq data) throws Exception;
	DeleteByPaymentMethodIdDtoRes deleteById(String id) throws Exception;
}
