package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.ProfilesDao;
import com.lawencon.community.dao.SubscriptionDao;
import com.lawencon.community.dto.subscription.DeleteBySubscriptionIdDtoRes;
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
import com.lawencon.model.SearchQuery;

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
	public GetAllSubscriptionDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllSubscriptionDtoRes getAll = new GetAllSubscriptionDtoRes();

		SearchQuery<Subscription> subscriptions = subscriptionDao.findAll(query, startPage, maxPage);
		List<GetAllSubscriptionDtoDataRes> listSubscription = new ArrayList<>();

		for (int i = 0; i < subscriptions.getData().size(); i++) {
			Subscription subscription = subscriptions.getData().get(i);
			GetAllSubscriptionDtoDataRes data = new GetAllSubscriptionDtoDataRes();

			data.setId(subscription.getId());
			data.setSubscriptionCode(subscription.getSubscriptionCode());
			data.setsubscriptionDuration(subscription.getSubscriptionDuration());
			data.setIsApprove(subscription.getIsApprove());
			data.setProfileId(subscription.getProfileId().getId());
			data.setProfileCode(subscription.getProfileId().getProfileCode());
			data.setProfileName(subscription.getProfileId().getProfileName());
			data.setProfileCompany(subscription.getProfileId().getProfileCompany());
			data.setProfilePortalCode(subscription.getProfileId().getProfilePostalCode());
			data.setUserId(subscription.getProfileId().getUserId().getId());
			data.setEmail(subscription.getProfileId().getUserId().getEmail());
			data.setVersion(subscription.getVersion());
			data.setIsActive(subscription.getIsActive());

			listSubscription.add(data);
		}

		getAll.setData(listSubscription);
		getAll.setMsg(null);
		getAll.setTotal(subscriptions.getCount());

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
		data.setProfilePortalCode(subscription.getProfileId().getProfilePostalCode());
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
			Date now = new Date();
			java.sql.Date date = new java.sql.Date(now.getTime());
			
			subscription.setSubscriptionCode(code);
			subscription.setSubscriptionDuration(date);		
			
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
			insert.setMsg("You " + CommonConstant.SUCCESS.getDetail() + " Upgrade to Membership");
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
				
				InsertSubscriptionDetailDtoReq detailReq = new InsertSubscriptionDetailDtoReq();
				detailReq.setSubscriptionId(subscription.getId());
				detailReq.setPriceId(data.getPriceId());
				
				subscriptionDetailService.insert(detailReq);

				subscription.setVersion(data.getVersion());
				subscription.setUpdatedBy(getId());
				subscription.setIsActive(data.getIsActive());

				UpdateSubscriptionDtoDataRes dataDto = new UpdateSubscriptionDtoDataRes();
				dataDto.setVersion(subscription.getVersion() + 1);

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Subscription " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteBySubscriptionIdDtoRes deleteById(String id) throws Exception {
		DeleteBySubscriptionIdDtoRes deleteById = new DeleteBySubscriptionIdDtoRes();

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
