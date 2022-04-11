package com.lawencon.community.service;

import com.lawencon.community.dto.subscriptiondetail.GetAllSubscriptionDetailDtoRes;
import com.lawencon.community.dto.subscriptiondetail.GetBySubscriptionDetailIdDtoRes;
import com.lawencon.community.dto.subscriptiondetail.GetSubscriptionDetailBySubscriptionDtoRes;
import com.lawencon.community.dto.subscriptiondetail.InsertSubscriptionDetailDtoReq;
import com.lawencon.community.dto.subscriptiondetail.InsertSubscriptionDetailDtoRes;

public interface SubscriptionDetailService {
	GetAllSubscriptionDetailDtoRes findAll() throws Exception;
	GetBySubscriptionDetailIdDtoRes findById(String id) throws Exception;
	InsertSubscriptionDetailDtoRes insert(InsertSubscriptionDetailDtoReq data) throws Exception;
	GetSubscriptionDetailBySubscriptionDtoRes findBySubscription(String id) throws Exception;
}
