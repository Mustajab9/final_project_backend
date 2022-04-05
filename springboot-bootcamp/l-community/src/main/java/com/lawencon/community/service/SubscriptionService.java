package com.lawencon.community.service;

import com.lawencon.community.dto.subscription.DeleteBySubscriptionIdDtoRes;
import com.lawencon.community.dto.subscription.GetAllSubscriptionDtoRes;
import com.lawencon.community.dto.subscription.GetBySubscriptionIdDtoRes;
import com.lawencon.community.dto.subscription.GetSubscriptionByUserDtoRes;
import com.lawencon.community.dto.subscription.InsertSubscriptionDtoReq;
import com.lawencon.community.dto.subscription.InsertSubscriptionDtoRes;
import com.lawencon.community.dto.subscription.UpdateSubscriptionDtoReq;
import com.lawencon.community.dto.subscription.UpdateSubscriptionDtoRes;

public interface SubscriptionService {
	public GetAllSubscriptionDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public GetBySubscriptionIdDtoRes findById(String id) throws Exception;
	public InsertSubscriptionDtoRes insert(InsertSubscriptionDtoReq data) throws Exception;
	public UpdateSubscriptionDtoRes update(UpdateSubscriptionDtoReq data) throws Exception;
	public DeleteBySubscriptionIdDtoRes deleteById(String id) throws Exception;
	public GetSubscriptionByUserDtoRes findByUser(String id) throws Exception;
}
