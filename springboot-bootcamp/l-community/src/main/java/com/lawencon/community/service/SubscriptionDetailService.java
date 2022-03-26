package com.lawencon.community.service;

import com.lawencon.community.dto.subscriptiondetail.GetAllSubscriptionDetailDtoRes;
import com.lawencon.community.dto.subscriptiondetail.GetBySubscriptionDetailIdDtoRes;
import com.lawencon.community.dto.subscriptiondetail.GetSubscriptionDetailBySubscriptionDtoRes;
import com.lawencon.community.dto.subscriptiondetail.InsertSubscriptionDetailDtoReq;
import com.lawencon.community.dto.subscriptiondetail.InsertSubscriptionDetailDtoRes;

public interface SubscriptionDetailService {
	public GetAllSubscriptionDetailDtoRes findAll() throws Exception;
	public GetBySubscriptionDetailIdDtoRes findById(String id) throws Exception;
	public InsertSubscriptionDetailDtoRes insert(InsertSubscriptionDetailDtoReq data) throws Exception;
	public GetSubscriptionDetailBySubscriptionDtoRes findBySubscription(String id) throws Exception;
}
