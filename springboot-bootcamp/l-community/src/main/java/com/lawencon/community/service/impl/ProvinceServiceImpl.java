package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class ProvinceServiceImpl extends BaseService implements ProvinceService {
	private ProvinceDao provinceDao;

	@Autowired
	public ProvinceServiceImpl(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}
	
	@Override
	public GetAllProvinceDtoRes findAll(int startPage, int maxPage) throws Exception {
		GetAllProvinceDtoRes getAll = new GetAllProvinceDtoRes();

		List<Province> provinces = provinceDao.findAll(startPage, maxPage);
		List<GetAllProvinceDtoDataRes> provinceList = new ArrayList<>();

		for (int i = 0; i < provinces.size(); i++) {
			Province province = provinces.get(i);
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
			insert.setMsg("Insert Success");
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
	public DeleteByProvinceIdDtoRes deleteById(String id) throws Exception {
		DeleteByProvinceIdDtoRes deleteById = new DeleteByProvinceIdDtoRes();

		try {
			begin();
			boolean isDeleted = provinceDao.deleteById(id);
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
}
