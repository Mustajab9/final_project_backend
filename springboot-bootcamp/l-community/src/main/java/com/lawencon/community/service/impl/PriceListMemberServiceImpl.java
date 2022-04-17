package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.PriceListMemberDao;
import com.lawencon.community.dto.pricelistmember.DeleteByPriceListMemberIdDtoRes;
import com.lawencon.community.dto.pricelistmember.GetAllPriceListMemberDtoDataRes;
import com.lawencon.community.dto.pricelistmember.GetAllPriceListMemberDtoRes;
import com.lawencon.community.dto.pricelistmember.GetByPriceListMemberIdDtoDataRes;
import com.lawencon.community.dto.pricelistmember.GetByPriceListMemberIdDtoRes;
import com.lawencon.community.dto.pricelistmember.InsertPriceListMemberDtoDataRes;
import com.lawencon.community.dto.pricelistmember.InsertPriceListMemberDtoReq;
import com.lawencon.community.dto.pricelistmember.InsertPriceListMemberDtoRes;
import com.lawencon.community.dto.pricelistmember.UpdatePriceListMemberDtoDataRes;
import com.lawencon.community.dto.pricelistmember.UpdatePriceListMemberDtoReq;
import com.lawencon.community.dto.pricelistmember.UpdatePriceListMemberDtoRes;
import com.lawencon.community.model.PriceListMember;
import com.lawencon.community.service.PriceListMemberService;
import com.lawencon.model.SearchQuery;

@Service
public class PriceListMemberServiceImpl extends BaseService implements PriceListMemberService {
	private PriceListMemberDao priceListMemberDao;

	@Autowired
	public PriceListMemberServiceImpl(PriceListMemberDao priceListMemberDao) {
		this.priceListMemberDao = priceListMemberDao;
	}
	
	@Override
	public GetAllPriceListMemberDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllPriceListMemberDtoRes getAll = new GetAllPriceListMemberDtoRes();

		SearchQuery<PriceListMember> priceListMembers = priceListMemberDao.findAll(query, startPage, maxPage);
		List<GetAllPriceListMemberDtoDataRes> priceListMemberList = new ArrayList<>();

		for (int i = 0; i < priceListMembers.getData().size(); i++) {
			PriceListMember priceListMember = priceListMembers.getData().get(i);
			GetAllPriceListMemberDtoDataRes data = new GetAllPriceListMemberDtoDataRes();

			data.setId(priceListMember.getId());
			data.setPriceNominal(priceListMember.getPriceNominal());
			data.setPriceCode(priceListMember.getPriceCode());
			data.setDuration(priceListMember.getDuration());
			data.setVersion(priceListMember.getVersion());
			data.setIsActive(priceListMember.getIsActive());

			priceListMemberList.add(data);
		}

		getAll.setData(priceListMemberList);
		getAll.setMsg(null);
		getAll.setTotal(priceListMembers.getCount());

		return getAll;
	}
	
	@Override
	public GetByPriceListMemberIdDtoRes findById(String id) throws Exception {
		GetByPriceListMemberIdDtoRes getById = new GetByPriceListMemberIdDtoRes();

		PriceListMember priceListMember = priceListMemberDao.findById(id);
		GetByPriceListMemberIdDtoDataRes data = new GetByPriceListMemberIdDtoDataRes();

		data.setId(priceListMember.getId());
		data.setPriceNominal(priceListMember.getPriceNominal());
		data.setPriceCode(priceListMember.getPriceCode());
		data.setDuration(priceListMember.getDuration());
		data.setVersion(priceListMember.getVersion());
		data.setIsActive(priceListMember.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertPriceListMemberDtoRes insert(InsertPriceListMemberDtoReq data) throws Exception {
		InsertPriceListMemberDtoRes insert = new InsertPriceListMemberDtoRes();

		try {
			validateInsert(data);
			
			PriceListMember priceListMember = new PriceListMember();
			priceListMember.setPriceNominal(data.getPriceNominal());
			priceListMember.setPriceCode(data.getPriceCode());
			priceListMember.setDuration(data.getDuration());
			priceListMember.setCreatedBy(getId());

			begin();
			PriceListMember priceListMemberInsert = priceListMemberDao.save(priceListMember);
			commit();

			InsertPriceListMemberDtoDataRes dataDto = new InsertPriceListMemberDtoDataRes();
			dataDto.setId(priceListMemberInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(CommonConstant.ACTION_ADD.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Price List Member " + CommonConstant.HAS_BEEN_ADDED.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdatePriceListMemberDtoRes update(UpdatePriceListMemberDtoReq data) throws Exception {
		UpdatePriceListMemberDtoRes update = new UpdatePriceListMemberDtoRes();

		try {
			if (data.getVersion() != null) {
				validateUpdate(data);
				
				PriceListMember priceListMember = priceListMemberDao.findById(data.getId());

				priceListMember.setPriceNominal(data.getPriceNominal());
				priceListMember.setDuration(data.getDuration());
				priceListMember.setVersion(data.getVersion());
				priceListMember.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					priceListMember.setIsActive(data.getIsActive());
				}

				begin();
				PriceListMember userUpdate = priceListMemberDao.save(priceListMember);
				commit();

				UpdatePriceListMemberDtoDataRes dataDto = new UpdatePriceListMemberDtoDataRes();
				dataDto.setVersion(userUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Price List Member " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByPriceListMemberIdDtoRes deleteById(String id) throws Exception {
		DeleteByPriceListMemberIdDtoRes deleteById = new DeleteByPriceListMemberIdDtoRes();

		try {
			validateDelete(id);
			
			begin();
			boolean isDeleted = priceListMemberDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Price List Member " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	private void validateInsert(InsertPriceListMemberDtoReq data) throws Exception {
		if (data.getPriceCode() == null || data.getPriceCode().trim().equals("")) {
			throw new Exception("Price List Code Can't Be Null");
		} else {
			PriceListMember priceListMember = priceListMemberDao.findByCode(data.getPriceCode());
			if (priceListMember != null) {
				throw new Exception("Price List Code Already Existed");
			}
			if (data.getPriceNominal() == null || data.getPriceNominal().equals("")) {
				throw new Exception("Price List Nominal Can't Be Null");
			}
		}
	}
	
	private void validateUpdate(UpdatePriceListMemberDtoReq data) throws Exception {
		if (data.getId() == null || data.getId().trim().equals("")) {
			throw new Exception("Price List Id Can't Be Null");
		} else {
			PriceListMember priceListMember = priceListMemberDao.findById(data.getId());
			if (data.getPriceNominal() == null || data.getPriceNominal().equals("")) {
				throw new Exception("Price List Nominal Can't Be Null");
			}
			if (priceListMember.getVersion() != data.getVersion()) {
				throw new Exception("Price List That You Update Already Updated By Someone");
			}
		}
	}

	private void validateDelete(String id) throws Exception {
		PriceListMember priceListMember = priceListMemberDao.findById(id);
		
		if(priceListMember == null) {
			throw new Exception("Price List Id Is Not Exist");
		}
	}
}
