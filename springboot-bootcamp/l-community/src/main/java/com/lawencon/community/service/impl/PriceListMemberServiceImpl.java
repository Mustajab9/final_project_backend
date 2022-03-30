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

@Service
public class PriceListMemberServiceImpl extends BaseService implements PriceListMemberService {
	private PriceListMemberDao priceListMemberDao;

	@Autowired
	public PriceListMemberServiceImpl(PriceListMemberDao priceListMemberDao) {
		this.priceListMemberDao = priceListMemberDao;
	}
	
	@Override
	public GetAllPriceListMemberDtoRes findAll(int startPage, int maxPage) throws Exception {
		GetAllPriceListMemberDtoRes getAll = new GetAllPriceListMemberDtoRes();

		List<PriceListMember> priceListMembers = priceListMemberDao.findAll(startPage, maxPage);
		List<GetAllPriceListMemberDtoDataRes> priceListMemberList = new ArrayList<>();

		for (int i = 0; i < priceListMembers.size(); i++) {
			PriceListMember priceListMember = priceListMembers.get(i);
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
}
