package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.PaymentMethodDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.PaymentMethodService;

@Service
public class PaymentMethodServiceImpl extends BaseService implements PaymentMethodService {
	private PaymentMethodDao paymentMethodDao;

	@Autowired
	public PaymentMethodServiceImpl(PaymentMethodDao paymentMethodDao) {
		this.paymentMethodDao = paymentMethodDao;
	}
}
