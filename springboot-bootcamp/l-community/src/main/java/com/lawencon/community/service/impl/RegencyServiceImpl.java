package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.ProvinceDao;
import com.lawencon.community.dao.RegencyDao;
import com.lawencon.community.dto.regency.DeleteByRegencyIdDtoRes;
import com.lawencon.community.dto.regency.GetAllRegencyDtoDataRes;
import com.lawencon.community.dto.regency.GetAllRegencyDtoRes;
import com.lawencon.community.dto.regency.GetByProvinceCodeDtoDataRes;
import com.lawencon.community.dto.regency.GetByProvinceCodeDtoRes;
import com.lawencon.community.dto.regency.GetByRegencyIdDtoDataRes;
import com.lawencon.community.dto.regency.GetByRegencyIdDtoRes;
import com.lawencon.community.dto.regency.InsertRegencyDtoDataRes;
import com.lawencon.community.dto.regency.InsertRegencyDtoReq;
import com.lawencon.community.dto.regency.InsertRegencyDtoRes;
import com.lawencon.community.dto.regency.UpdateRegencyDtoDataRes;
import com.lawencon.community.dto.regency.UpdateRegencyDtoReq;
import com.lawencon.community.dto.regency.UpdateRegencyDtoRes;
import com.lawencon.community.model.Province;
import com.lawencon.community.model.Regency;
import com.lawencon.community.service.RegencyService;
import com.lawencon.model.SearchQuery;

@Service
public class RegencyServiceImpl extends BaseService implements RegencyService {
	private RegencyDao regencyDao;
	private ProvinceDao provinceDao;
	@Autowired
	public RegencyServiceImpl(RegencyDao regencyDao, ProvinceDao provinceDao) {
		this.regencyDao = regencyDao;
		this.provinceDao = provinceDao;
	}
	
	@Override
	public GetAllRegencyDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllRegencyDtoRes getAll = new GetAllRegencyDtoRes();

		SearchQuery<Regency> regencies = regencyDao.findAll(query, startPage, maxPage);
		List<GetAllRegencyDtoDataRes> regencyList = new ArrayList<>();

		for (int i = 0; i < regencies.getData().size(); i++) {
			Regency regency = regencies.getData().get(i);
			GetAllRegencyDtoDataRes data = new GetAllRegencyDtoDataRes();

			data.setId(regency.getId());
			data.setRegancyName(regency.getRegencyName());
			data.setRegancyCode(regency.getRegencyCode());
			data.setProvinceId(regency.getProvinceId().getId());
			data.setProvinceName(regency.getProvinceId().getProvinceName());
			data.setProvinceCode(regency.getProvinceId().getProvinceCode());
			data.setVersion(regency.getVersion());
			data.setIsActive(regency.getIsActive());

			regencyList.add(data);
		}
		getAll.setData(regencyList);
		getAll.setMsg(null);
		getAll.setTotal(regencies.getCount());

		return getAll;
	}
	
	@Override
	public GetByRegencyIdDtoRes findById(String id) throws Exception {
		GetByRegencyIdDtoRes getById = new GetByRegencyIdDtoRes();

		Regency regency = regencyDao.findById(id);
		GetByRegencyIdDtoDataRes data = new GetByRegencyIdDtoDataRes();

		data.setId(regency.getId());
		data.setRegancyName(regency.getRegencyName());
		data.setRegancyCode(regency.getRegencyCode());
		data.setProvinceId(regency.getProvinceId().getId());
		data.setProvinceName(regency.getProvinceId().getProvinceName());
		data.setProvinceCode(regency.getProvinceId().getProvinceCode());
		data.setVersion(regency.getVersion());
		data.setIsActive(regency.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertRegencyDtoRes insert(InsertRegencyDtoReq data) throws Exception {
		InsertRegencyDtoRes insert = new InsertRegencyDtoRes();

		try {
			Regency regency = new Regency();
			regency.setRegencyName(data.getRegencyName());
			regency.setRegencyCode(data.getRegencyCode());
			
			Province province = provinceDao.findById(data.getProvinceId());
			regency.setProvinceId(province);
			regency.setCreatedBy(getId());
			
			begin();
			Regency regencyInsert = regencyDao.save(regency);
			commit();

			InsertRegencyDtoDataRes dataDto = new InsertRegencyDtoDataRes();
			dataDto.setId(regencyInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(CommonConstant.ACTION_ADD.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Regency " + CommonConstant.HAS_BEEN_ADDED.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdateRegencyDtoRes update(UpdateRegencyDtoReq data) throws Exception {
		UpdateRegencyDtoRes update = new UpdateRegencyDtoRes();

		try {
			if (data.getVersion() != null) {
				Regency regency = regencyDao.findById(data.getId());
				
				regency.setRegencyName(data.getRegencyName());
				regency.setVersion(data.getVersion());
				regency.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					regency.setIsActive(data.getIsActive());
				}

				begin();
				Regency regencyUpdate = regencyDao.save(regency);
				commit();

				UpdateRegencyDtoDataRes dataDto = new UpdateRegencyDtoDataRes();
				dataDto.setVersion(regencyUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Regency " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByRegencyIdDtoRes deleteById(String id) throws Exception {
		DeleteByRegencyIdDtoRes deleteById = new DeleteByRegencyIdDtoRes();

		try {
			begin();
			boolean isDeleted = regencyDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Regency " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetByProvinceCodeDtoRes findByProvince(String id) throws Exception {
		GetByProvinceCodeDtoRes getByProvinceCode = new GetByProvinceCodeDtoRes();

		List<Regency> regencies = regencyDao.findByProvince(id);
		List<GetByProvinceCodeDtoDataRes> regencyList = new ArrayList<>();

		for (int i = 0; i < regencies.size(); i++) {
			Regency regency = regencies.get(i);
			GetByProvinceCodeDtoDataRes data = new GetByProvinceCodeDtoDataRes();

			data.setId(regency.getId());
			data.setRegancyName(regency.getRegencyName());
			data.setRegancyCode(regency.getRegencyCode());
			data.setProvinceId(regency.getProvinceId().getId());
			data.setProvinceName(regency.getProvinceId().getProvinceName());
			data.setProvinceCode(regency.getProvinceId().getProvinceCode());
			data.setVersion(regency.getVersion());
			data.setIsActive(regency.getIsActive());

			regencyList.add(data);
		}
		getByProvinceCode.setData(regencyList);
		getByProvinceCode.setMsg(null);

		return getByProvinceCode;
	}
}
