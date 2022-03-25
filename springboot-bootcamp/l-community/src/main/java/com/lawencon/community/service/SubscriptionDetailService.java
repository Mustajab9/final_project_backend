package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.attachment.InsertAttachmentDtoReq;
import com.lawencon.community.dto.subscriptiondetail.GetAllSubscriptionDetailDtoRes;
import com.lawencon.community.dto.subscriptiondetail.GetBySubscriptionDetailIdDtoRes;
import com.lawencon.community.dto.subscriptiondetail.InsertSubscriptionDetailDtoRes;
import com.lawencon.community.model.SubscriptionDetail;

public interface SubscriptionDetailService {
	public GetAllSubscriptionDetailDtoRes findAll() throws Exception;
	public GetBySubscriptionDetailIdDtoRes findById(String id) throws Exception;
	public InsertSubscriptionDetailDtoRes insert(InsertAttachmentDtoReq data) throws Exception;
	public List<SubscriptionDetail> findBySubscription(String id) throws Exception;
}
