package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.PriceListMemberDao;
import com.lawencon.community.dao.SubscriptionDao;
import com.lawencon.community.dao.SubscriptionDetailDao;
import com.lawencon.community.dto.subscriptiondetail.GetAllSubscriptionDetailDtoDataRes;
import com.lawencon.community.dto.subscriptiondetail.GetAllSubscriptionDetailDtoRes;
import com.lawencon.community.dto.subscriptiondetail.GetBySubscriptionDetailIdDtoDataRes;
import com.lawencon.community.dto.subscriptiondetail.GetBySubscriptionDetailIdDtoRes;
import com.lawencon.community.dto.subscriptiondetail.GetSubscriptionDetailBySubscriptionDtoDataRes;
import com.lawencon.community.dto.subscriptiondetail.GetSubscriptionDetailBySubscriptionDtoRes;
import com.lawencon.community.dto.subscriptiondetail.InsertSubscriptionDetailDtoDataRes;
import com.lawencon.community.dto.subscriptiondetail.InsertSubscriptionDetailDtoReq;
import com.lawencon.community.dto.subscriptiondetail.InsertSubscriptionDetailDtoRes;
import com.lawencon.community.model.PriceListMember;
import com.lawencon.community.model.Subscription;
import com.lawencon.community.model.SubscriptionDetail;
import com.lawencon.community.service.SubscriptionDetailService;

@Service
public class SubscriptionDetailServiceImpl extends BaseService implements SubscriptionDetailService {
	private SubscriptionDetailDao subscriptionDetailDao;
	private SubscriptionDao subscriptionDao;
	private PriceListMemberDao priceListMemberDao;

	@Autowired
	public SubscriptionDetailServiceImpl(SubscriptionDetailDao subscriptionDetailDao, SubscriptionDao subscriptionDao,
									PriceListMemberDao priceListMemberDao) {
		this.subscriptionDetailDao = subscriptionDetailDao;
		this.subscriptionDao = subscriptionDao;
		this.priceListMemberDao = priceListMemberDao;
	}

	@Override
	public GetAllSubscriptionDetailDtoRes findAll() throws Exception {
		GetAllSubscriptionDetailDtoRes getAll = new GetAllSubscriptionDetailDtoRes();

		List<SubscriptionDetail> details = subscriptionDetailDao.findAll();
		List<GetAllSubscriptionDetailDtoDataRes> listDetail = new ArrayList<>();

		for (int i = 0; i < details.size(); i++) {
			SubscriptionDetail detail = details.get(i);
			GetAllSubscriptionDetailDtoDataRes data = new GetAllSubscriptionDetailDtoDataRes();

			data.setId(detail.getId());
			data.setPriceId(detail.getPriceId().getId());
			data.setPriceCode(detail.getPriceId().getPriceCode());
			data.setPriceNominal(detail.getPriceId().getPriceNominal());
			data.setDuration(detail.getPriceId().getDuration());
			data.setSubscriptionId(detail.getSubscriptionId().getId());
			data.setSubscriptionCode(detail.getSubscriptionId().getSubscriptionCode());
			data.setSubscriptionDuration(detail.getSubscriptionId().getSubscriptionDuration());
			data.setIsApprove(detail.getSubscriptionId().getIsApprove());
			data.setProfileId(detail.getSubscriptionId().getProfileId().getId());
			data.setProfileCode(detail.getSubscriptionId().getProfileId().getProfileCode());
			data.setProfileName(detail.getSubscriptionId().getProfileId().getProfileName());
			data.setProfileCompany(detail.getSubscriptionId().getProfileId().getProfileCompany());
			data.setProfilePortalCode(detail.getSubscriptionId().getProfileId().getProfilePortalCode());
			data.setUserId(detail.getSubscriptionId().getProfileId().getUserId().getId());
			data.setEmail(detail.getSubscriptionId().getProfileId().getUserId().getEmail());
			data.setVersion(detail.getVersion());
			data.setIsActive(detail.getIsActive());

			listDetail.add(data);
		}

		getAll.setData(listDetail);
		getAll.setMsg(null);

		return getAll;
	}

	@Override
	public GetBySubscriptionDetailIdDtoRes findById(String id) throws Exception {
		GetBySubscriptionDetailIdDtoRes getById = new GetBySubscriptionDetailIdDtoRes();

		SubscriptionDetail detail = subscriptionDetailDao.findById(id);
		GetBySubscriptionDetailIdDtoDataRes data = new GetBySubscriptionDetailIdDtoDataRes();

		data.setId(detail.getId());
		data.setPriceId(detail.getPriceId().getId());
		data.setPriceCode(detail.getPriceId().getPriceCode());
		data.setPriceNominal(detail.getPriceId().getPriceNominal());
		data.setDuration(detail.getPriceId().getDuration());
		data.setSubscriptionId(detail.getSubscriptionId().getId());
		data.setSubscriptionCode(detail.getSubscriptionId().getSubscriptionCode());
		data.setSubscriptionDuration(detail.getSubscriptionId().getSubscriptionDuration());
		data.setIsApprove(detail.getSubscriptionId().getIsApprove());
		data.setProfileId(detail.getSubscriptionId().getProfileId().getId());
		data.setProfileCode(detail.getSubscriptionId().getProfileId().getProfileCode());
		data.setProfileName(detail.getSubscriptionId().getProfileId().getProfileName());
		data.setProfileCompany(detail.getSubscriptionId().getProfileId().getProfileCompany());
		data.setProfilePortalCode(detail.getSubscriptionId().getProfileId().getProfilePortalCode());
		data.setUserId(detail.getSubscriptionId().getProfileId().getUserId().getId());
		data.setEmail(detail.getSubscriptionId().getProfileId().getUserId().getEmail());
		data.setVersion(detail.getVersion());
		data.setIsActive(detail.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}

	@Override
	public InsertSubscriptionDetailDtoRes insert(InsertSubscriptionDetailDtoReq data) throws Exception {
		InsertSubscriptionDetailDtoRes insert = new InsertSubscriptionDetailDtoRes();

		try {
			SubscriptionDetail detail = new SubscriptionDetail();

			Subscription subscription = subscriptionDao.findById(data.getSubscriptionId());
			detail.setSubscriptionId(subscription);
			
			PriceListMember priceListMember = priceListMemberDao.findById(data.getPriceId());
			detail.setPriceId(priceListMember);
			detail.setCreatedBy(getId());

			begin();
			SubscriptionDetail detailInsert = subscriptionDetailDao.save(detail);
			commit();

			InsertSubscriptionDetailDtoDataRes dataDto = new InsertSubscriptionDetailDtoDataRes();
			dataDto.setId(detailInsert.getId());
			
			if(detailInsert != null) {
				Integer LenghtDay = priceListMember.getDuration();
				if(subscription.getSubscriptionDuration().compareTo(new Date()) <= 0){
					java.sql.Date date = subscription.getSubscriptionDuration();
					
					begin();
					subscriptionDao.update(date, LenghtDay, subscription.getId());
					commit();
					
				}else {
					begin();
					subscriptionDao.update(new Date(), LenghtDay, subscription.getId());
					commit();
				}
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
	public GetSubscriptionDetailBySubscriptionDtoRes findBySubscription(String id) throws Exception {
		GetSubscriptionDetailBySubscriptionDtoRes getBySubscription = new GetSubscriptionDetailBySubscriptionDtoRes();

		List<SubscriptionDetail> details = subscriptionDetailDao.findBySubscription(id);
		List<GetSubscriptionDetailBySubscriptionDtoDataRes> listDetail = new ArrayList<>();

		for (int i = 0; i < details.size(); i++) {
			SubscriptionDetail detail = details.get(i);
			GetSubscriptionDetailBySubscriptionDtoDataRes data = new GetSubscriptionDetailBySubscriptionDtoDataRes();

			data.setId(detail.getId());
			data.setPriceId(detail.getPriceId().getId());
			data.setPriceCode(detail.getPriceId().getPriceCode());
			data.setPriceNominal(detail.getPriceId().getPriceNominal());
			data.setDuration(detail.getPriceId().getDuration());
			data.setSubscriptionId(detail.getSubscriptionId().getId());
			data.setSubscriptionCode(detail.getSubscriptionId().getSubscriptionCode());
			data.setSubscriptionDuration(detail.getSubscriptionId().getSubscriptionDuration());
			data.setIsApprove(detail.getSubscriptionId().getIsApprove());
			data.setProfileId(detail.getSubscriptionId().getProfileId().getId());
			data.setProfileCode(detail.getSubscriptionId().getProfileId().getProfileCode());
			data.setProfileName(detail.getSubscriptionId().getProfileId().getProfileName());
			data.setProfileCompany(detail.getSubscriptionId().getProfileId().getProfileCompany());
			data.setProfilePortalCode(detail.getSubscriptionId().getProfileId().getProfilePortalCode());
			
			if(detail.getSubscriptionId().getProfileId().getProfileImage() != null) {
				data.setProfileImage(detail.getSubscriptionId().getProfileId().getProfileImage().getId());
				data.setAttachmentExtension(detail.getSubscriptionId().getProfileId().getProfileImage().getAttachmentExtension());
			}
			
			data.setUserId(detail.getSubscriptionId().getProfileId().getUserId().getId());
			data.setEmail(detail.getSubscriptionId().getProfileId().getUserId().getEmail());
			data.setVersion(detail.getVersion());
			data.setIsActive(detail.getIsActive());

			listDetail.add(data);
		}

		getBySubscription.setData(listDetail);
		getBySubscription.setMsg(null);

		return getBySubscription;
	}
}
