package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ProfilesDao;
import com.lawencon.community.dao.SubscriptionDao;
import com.lawencon.community.dto.socialmedia.DeleteBySocialMediaIdDtoRes;
import com.lawencon.community.dto.subscription.GetAllSubscriptionDtoDataRes;
import com.lawencon.community.dto.subscription.GetAllSubscriptionDtoRes;
import com.lawencon.community.dto.subscription.GetBySubscriptionIdDtoDataRes;
import com.lawencon.community.dto.subscription.GetBySubscriptionIdDtoRes;
import com.lawencon.community.dto.subscription.GetSubscriptionByUserDtoDataRes;
import com.lawencon.community.dto.subscription.GetSubscriptionByUserDtoRes;
import com.lawencon.community.dto.subscription.InsertSubscriptionDtoDataRes;
import com.lawencon.community.dto.subscription.InsertSubscriptionDtoReq;
import com.lawencon.community.dto.subscription.InsertSubscriptionDtoRes;
import com.lawencon.community.dto.subscription.UpdateSubscriptionDtoDataRes;
import com.lawencon.community.dto.subscription.UpdateSubscriptionDtoReq;
import com.lawencon.community.dto.subscription.UpdateSubscriptionDtoRes;
import com.lawencon.community.dto.subscriptiondetail.InsertSubscriptionDetailDtoReq;
import com.lawencon.community.model.Profiles;
import com.lawencon.community.model.Subscription;
import com.lawencon.community.service.SubscriptionDetailService;
import com.lawencon.community.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl extends BaseService implements SubscriptionService {
	private SubscriptionDao subscriptionDao;
	private ProfilesDao profileDao;
	private SubscriptionDetailService subscriptionDetailService;

	@Autowired
	public SubscriptionServiceImpl(SubscriptionDao subscriptionDao, ProfilesDao profileDao) {
		this.subscriptionDao = subscriptionDao;
		this.profileDao = profileDao;
	}
	
	@Autowired
	public void setSubscriptionDetailService(SubscriptionDetailService subscriptionDetailService) {
		this.subscriptionDetailService = subscriptionDetailService;
	}
	
	@Override
	public GetAllSubscriptionDtoRes findAll() throws Exception {
		GetAllSubscriptionDtoRes getAll = new GetAllSubscriptionDtoRes();

		List<Subscription> subscriptions = subscriptionDao.findAll();
		List<GetAllSubscriptionDtoDataRes> listSubscription = new ArrayList<>();

		for (int i = 0; i < subscriptions.size(); i++) {
			Subscription subscription = subscriptions.get(i);
			GetAllSubscriptionDtoDataRes data = new GetAllSubscriptionDtoDataRes();

			data.setId(subscription.getId());
			data.setSubscriptionCode(subscription.getSubscriptionCode());
			data.setsubscriptionDuration(subscription.getSubscriptionDuration());
			data.setIsApprove(subscription.getIsApprove());
			data.setProfileId(subscription.getProfileId().getId());
			data.setProfileCode(subscription.getProfileId().getProfileCode());
			data.setProfileName(subscription.getProfileId().getProfileName());
			data.setProfileCompany(subscription.getProfileId().getProfileCompany());
			data.setProfilePortalCode(subscription.getProfileId().getProfilePortalCode());
			data.setUserId(subscription.getProfileId().getUserId().getId());
			data.setEmail(subscription.getProfileId().getUserId().getEmail());
			data.setVersion(subscription.getVersion());
			data.setIsActive(subscription.getIsActive());

			listSubscription.add(data);
		}

		getAll.setData(listSubscription);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetBySubscriptionIdDtoRes findById(String id) throws Exception {
		GetBySubscriptionIdDtoRes getById = new GetBySubscriptionIdDtoRes();

		Subscription subscription = subscriptionDao.findById(id);
		GetBySubscriptionIdDtoDataRes data = new GetBySubscriptionIdDtoDataRes();

		data.setId(subscription.getId());
		data.setSubscriptionCode(subscription.getSubscriptionCode());
		data.setsubscriptionDuration(subscription.getSubscriptionDuration());
		data.setIsApprove(subscription.getIsApprove());
		data.setProfileId(subscription.getProfileId().getId());
		data.setProfileCode(subscription.getProfileId().getProfileCode());
		data.setProfileName(subscription.getProfileId().getProfileName());
		data.setProfileCompany(subscription.getProfileId().getProfileCompany());
		data.setProfilePortalCode(subscription.getProfileId().getProfilePortalCode());
		data.setUserId(subscription.getProfileId().getUserId().getId());
		data.setEmail(subscription.getProfileId().getUserId().getEmail());
		data.setVersion(subscription.getVersion());
		data.setIsActive(subscription.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertSubscriptionDtoRes insert(InsertSubscriptionDtoReq data) throws Exception {
		InsertSubscriptionDtoRes insert = new InsertSubscriptionDtoRes();

		try {
			Subscription subscription = new Subscription();
			String code = getAlphaNumericString(5);
			Date date = new Date();
			
			subscription.setSubscriptionCode(code);
			subscription.setSubscriptionDuration((java.sql.Date) date);		
			
			Profiles profiles = profileDao.findById(data.getProfileId());
			subscription.setProfileId(profiles);
			subscription.setCreatedBy(getId());
			
			begin();
			Subscription subscriptionInsert = subscriptionDao.save(subscription);
			commit();

			InsertSubscriptionDtoDataRes dataDto = new InsertSubscriptionDtoDataRes();
			dataDto.setId(subscriptionInsert.getId());
			
			if(subscriptionInsert != null) {
				InsertSubscriptionDetailDtoReq detailReq = new InsertSubscriptionDetailDtoReq();
				detailReq.setSubscriptionId(subscriptionInsert.getId());
				detailReq.setPriceId(data.getPriceId());
				
				subscriptionDetailService.insert(detailReq);
			}

			insert.setData(dataDto);
			insert.setMsg("Insert Success");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdateSubscriptionDtoRes update(UpdateSubscriptionDtoReq data) throws Exception {
		UpdateSubscriptionDtoRes update = new UpdateSubscriptionDtoRes();

		try {
			if (data.getVersion() != null) {
				Subscription subscription = subscriptionDao.findById(data.getId());

				subscription.setVersion(data.getVersion());
				subscription.setUpdatedBy(getId());
				subscription.setIsActive(data.getIsActive());

				begin();
				Subscription subscriptionUpdate = subscriptionDao.save(subscription);
				commit();

				UpdateSubscriptionDtoDataRes dataDto = new UpdateSubscriptionDtoDataRes();
				dataDto.setVersion(subscriptionUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg("Update Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteBySocialMediaIdDtoRes deleteById(String id) throws Exception {
		DeleteBySocialMediaIdDtoRes deleteById = new DeleteBySocialMediaIdDtoRes();

		try {
			begin();
			boolean isDeleted = subscriptionDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg("Delete Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetSubscriptionByUserDtoRes findByUser(String id) throws Exception {
		GetSubscriptionByUserDtoRes getById = new GetSubscriptionByUserDtoRes();

		Subscription subscription = subscriptionDao.findByUser(id);
		GetSubscriptionByUserDtoDataRes data = new GetSubscriptionByUserDtoDataRes();

		data.setId(subscription.getId());
		data.setSubscriptionCode(subscription.getSubscriptionCode());
		data.setsubscriptionDuration(subscription.getSubscriptionDuration());
		data.setIsApprove(subscription.getIsApprove());
		data.setProfileId(subscription.getProfileId().getId());
		data.setProfileName(subscription.getProfileId().getProfileName());
		data.setVersion(subscription.getVersion());
		data.setIsActive(subscription.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
}
