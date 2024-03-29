package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.constant.RoleConstant;
import com.lawencon.community.dao.ProfilesDao;
import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.dao.SubscriptionDao;
import com.lawencon.community.dao.UserDao;
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
import com.lawencon.community.model.Role;
import com.lawencon.community.model.Subscription;
import com.lawencon.community.model.User;
import com.lawencon.community.service.SubscriptionDetailService;
import com.lawencon.community.service.SubscriptionService;
import com.lawencon.model.SearchQuery;

@Service
public class SubscriptionServiceImpl extends BaseService implements SubscriptionService {
	private SubscriptionDao subscriptionDao;
	private ProfilesDao profileDao;
	private UserDao userDao;
	private RoleDao roleDao;
	private SubscriptionDetailService subscriptionDetailService;

	Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
	
	@Autowired
	public SubscriptionServiceImpl(SubscriptionDao subscriptionDao, ProfilesDao profileDao, UserDao userDao, RoleDao roleDao) {
		this.subscriptionDao = subscriptionDao;
		this.profileDao = profileDao;
		this.userDao = userDao;
		this.roleDao = roleDao;
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
			validateInsert(data);
			
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
				
				Profiles profile = profileDao.findById(data.getProfileId());
				
				User user = userDao.findById(profile.getUserId().getId());
				Role role = roleDao.findByCode(RoleConstant.MEMBER.getCode());
				user.setRoleId(role);
				
				begin();
				userDao.save(user);
				commit();
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
				
				Profiles profile = profileDao.findById(subscription.getProfileId().getId());
				
				User user = userDao.findById(profile.getUserId().getId());
				Role role = roleDao.findByCode(RoleConstant.MEMBER.getCode());
				user.setRoleId(role);
				
				begin();
				userDao.save(user);
				commit();

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

		if(subscription != null) {			
			data.setId(subscription.getId());
			data.setSubscriptionCode(subscription.getSubscriptionCode());
			data.setsubscriptionDuration(subscription.getCreatedBy());
			data.setIsApprove(subscription.getIsApprove());
			data.setProfileId(subscription.getProfileId().getId());
			data.setProfileName(subscription.getProfileId().getProfileName());
			data.setVersion(subscription.getVersion());
			data.setIsActive(subscription.getIsActive());
		}

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public void expiredSubscription() throws Exception {
		SearchQuery<Subscription> subscriptions = subscriptionDao.findAll(null, null, null);
		
		List<Subscription> listSubscription = subscriptions.getData();
		Role role = roleDao.findByCode(RoleConstant.USER.getCode());
		
		int data = listSubscription.size();
		for(int i = 0; i < data; i++) {
			Subscription subscription = listSubscription.get(i);
			
			if(subscription.getSubscriptionDuration().before(new Date())) {
				
				User user = userDao.findById(subscription.getProfileId().getUserId().getId());
				user.setRoleId(role);
				
				begin();
				userDao.save(user);
				commit();
			}
		}
	}
	
	
	private void validateInsert(InsertSubscriptionDtoReq data) throws Exception {
		if (data.getProfileId() == null || data.getProfileId().trim().equals("")) {
			throw new Exception("Profile Id Cant Null");
		} else {
			if (data.getPriceId() == null || data.getPriceId().trim().equals("")) {
				throw new Exception("Price Id Cant Null");
			}
		}
	}
}
