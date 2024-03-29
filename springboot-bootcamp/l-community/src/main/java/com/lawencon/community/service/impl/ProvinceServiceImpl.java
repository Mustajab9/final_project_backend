package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.ProvinceDao;
import com.lawencon.community.dto.province.DeleteByProvinceIdDtoRes;
import com.lawencon.community.dto.province.GetAllProvinceDtoDataRes;
import com.lawencon.community.dto.province.GetAllProvinceDtoRes;
import com.lawencon.community.dto.province.GetByProvinceIdDtoDataRes;
import com.lawencon.community.dto.province.GetByProvinceIdDtoRes;
import com.lawencon.community.dto.province.InsertProvinceDtoDataRes;
import com.lawencon.community.dto.province.InsertProvinceDtoReq;
import com.lawencon.community.dto.province.InsertProvinceDtoRes;
import com.lawencon.community.dto.province.UpdateProvinceDtoDataRes;
import com.lawencon.community.dto.province.UpdateProvinceDtoReq;
import com.lawencon.community.dto.province.UpdateProvinceDtoRes;
import com.lawencon.community.model.Province;
import com.lawencon.community.service.ProvinceService;
import com.lawencon.model.SearchQuery;

@Service
public class ProvinceServiceImpl extends BaseService implements ProvinceService {
	private ProvinceDao provinceDao;

	@Autowired
	public ProvinceServiceImpl(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}
	
	@Override
	public GetAllProvinceDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllProvinceDtoRes getAll = new GetAllProvinceDtoRes();

		SearchQuery<Province> provinces = provinceDao.findAll(query, startPage, maxPage);
		List<GetAllProvinceDtoDataRes> provinceList = new ArrayList<>();

		for (int i = 0; i < provinces.getData().size(); i++) {
			Province province = provinces.getData().get(i);
			GetAllProvinceDtoDataRes data = new GetAllProvinceDtoDataRes();

			data.setId(province.getId());
			data.setProvinceName(province.getProvinceName());
			data.setProvinceCode(province.getProvinceCode());
			data.setVersion(province.getVersion());
			data.setIsActive(province.getIsActive());

			provinceList.add(data);
		}

		getAll.setData(provinceList);
		getAll.setMsg(null);
		getAll.setTotal(provinces.getCount());

		return getAll;
	}
	
	@Override
	public GetByProvinceIdDtoRes findById(String id) throws Exception {
		GetByProvinceIdDtoRes getById = new GetByProvinceIdDtoRes();

		Province province = provinceDao.findById(id);
		GetByProvinceIdDtoDataRes data = new GetByProvinceIdDtoDataRes();

		data.setId(province.getId());
		data.setProvinceName(province.getProvinceName());
		data.setProvinceCode(province.getProvinceCode());
		data.setVersion(province.getVersion());
		data.setIsActive(province.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertProvinceDtoRes insert(InsertProvinceDtoReq data) throws Exception {
		InsertProvinceDtoRes insert = new InsertProvinceDtoRes();

		try {
			validateInsert(data);
			
			Province province = new Province();
			province.setProvinceName(data.getProvinceName());
			province.setProvinceCode(data.getProvinceCode());
			province.setCreatedBy(getId());

			begin();
			Province provinceInsert = provinceDao.save(province);
			commit();

			InsertProvinceDtoDataRes dataDto = new InsertProvinceDtoDataRes();
			dataDto.setId(provinceInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(CommonConstant.ACTION_ADD.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Province " + CommonConstant.HAS_BEEN_ADDED.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdateProvinceDtoRes update(UpdateProvinceDtoReq data) throws Exception {
		UpdateProvinceDtoRes update = new UpdateProvinceDtoRes();

		try {
			validateUpdate(data);
			
			if (data.getVersion() != null) {
				Province province = provinceDao.findById(data.getId());

				province.setProvinceName(data.getProvinceName());
				province.setVersion(data.getVersion());
				province.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					province.setIsActive(data.getIsActive());
				}

				begin();
				Province provinceUpdate = provinceDao.save(province);
				commit();

				UpdateProvinceDtoDataRes dataDto = new UpdateProvinceDtoDataRes();
				dataDto.setVersion(provinceUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Province " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByProvinceIdDtoRes deleteById(String id) throws Exception {
		DeleteByProvinceIdDtoRes deleteById = new DeleteByProvinceIdDtoRes();

		try {
			validateDelete(id);
			
			begin();
			boolean isDeleted = provinceDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Province " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	private void validateInsert(InsertProvinceDtoReq data) throws Exception {
		if (data.getProvinceCode() == null || data.getProvinceCode().trim().equals("")) {
			throw new Exception("Province Code Cant Null");
		} else {
			Province province = provinceDao.findByCode(data.getProvinceCode());
			if (province != null) {
				throw new Exception("Province Code Already Exsist");
			}
			if (data.getProvinceName() == null || data.getProvinceName().trim().equals("")) {
				throw new Exception("Province Name Cant Null");
			}
		}
	}

	private void validateUpdate(UpdateProvinceDtoReq data) throws Exception {
		if (data.getId() == null || data.getId().trim().equals("")) {
			throw new Exception("Province Id Cant Null");
		} else {
			Province province = provinceDao.findById(data.getId());
			if (data.getProvinceName() == null || data.getProvinceName().trim().equals("")) {
				throw new Exception("Province Name Cant Null");
			}
			if (province.getVersion() != data.getVersion()) {
				throw new Exception("Province You Update Already Update By Someone");
			}
		}
	}

	private void validateDelete(String id) throws Exception {
		Province province = provinceDao.findById(id);
		
		if(province == null) {
			throw new Exception("Province Id Not Exsist");
		}
	}
}
