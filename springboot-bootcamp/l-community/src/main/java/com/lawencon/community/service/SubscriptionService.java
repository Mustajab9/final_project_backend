package com.lawencon.community.service;

import com.lawencon.community.dto.socialmedia.DeleteBySocialMediaIdDtoRes;
import com.lawencon.community.dto.subscription.GetAllSubscriptionDtoRes;
import com.lawencon.community.dto.subscription.GetBySubscriptionIdDtoRes;
import com.lawencon.community.dto.subscription.InsertSubscriptionDtoReq;
import com.lawencon.community.dto.subscription.InsertSubscriptionDtoRes;
import com.lawencon.community.dto.subscription.UpdateSubscriptionDtoReq;
import com.lawencon.community.dto.subscription.UpdateSubscriptionDtoRes;
import com.lawencon.community.model.Subscription;

public interface SubscriptionService {
	public GetAllSubscriptionDtoRes findAll() throws Exception;
	public GetBySubscriptionIdDtoRes findById(String id) throws Exception;
	public InsertSubscriptionDtoRes insert(InsertSubscriptionDtoReq data) throws Exception;
	public UpdateSubscriptionDtoRes update(UpdateSubscriptionDtoReq data) throws Exception;
	public DeleteBySocialMediaIdDtoRes deleteById(String id) throws Exception;
	public Subscription findByUser(String id) throws Exception;
}
