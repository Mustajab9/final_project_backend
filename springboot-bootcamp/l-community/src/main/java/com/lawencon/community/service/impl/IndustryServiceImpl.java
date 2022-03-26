package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.IndustryDao;
import com.lawencon.community.dto.industry.DeleteByIndustryIdDtoRes;
import com.lawencon.community.dto.industry.GetAllIndustryDtoDataRes;
import com.lawencon.community.dto.industry.GetAllIndustryDtoRes;
import com.lawencon.community.dto.industry.GetByIndustryIdDtoDataRes;
import com.lawencon.community.dto.industry.GetByIndustryIdDtoRes;
import com.lawencon.community.dto.industry.InsertIndustryDtoDataRes;
import com.lawencon.community.dto.industry.InsertIndustryDtoReq;
import com.lawencon.community.dto.industry.InsertIndustryDtoRes;
import com.lawencon.community.dto.industry.UpdateIndustryDtoDataRes;
import com.lawencon.community.dto.industry.UpdateIndustryDtoReq;
import com.lawencon.community.dto.industry.UpdateIndustryDtoRes;
import com.lawencon.community.model.Industry;
import com.lawencon.community.service.IndustryService;

@Service
public class IndustryServiceImpl extends BaseService implements IndustryService {
	private IndustryDao industryDao;

	@Autowired
	public IndustryServiceImpl(IndustryDao industryDao) {
		this.industryDao = industryDao;
	}
	
	@Override
	public GetAllIndustryDtoRes findAll(int startPage, int maxPage) throws Exception {
		GetAllIndustryDtoRes getAll = new GetAllIndustryDtoRes();

		List<Industry> industries = industryDao.findAll(startPage, maxPage);
		List<GetAllIndustryDtoDataRes> listIndustry = new ArrayList<>();

		for (int i = 0; i < industries.size(); i++) {
			Industry industry = industries.get(i);
			GetAllIndustryDtoDataRes data = new GetAllIndustryDtoDataRes();

			data.setId(industry.getId());
			data.setIndustryCode(industry.getIndustryCode());
			data.setIndustryName(industry.getIndustryName());
			data.setVersion(industry.getVersion());
			data.setIsActive(industry.getIsActive());

			listIndustry.add(data);
		}

		getAll.setData(listIndustry);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByIndustryIdDtoRes findById(String id) throws Exception {
		GetByIndustryIdDtoRes getById = new GetByIndustryIdDtoRes();

		Industry industry = industryDao.findById(id);
		GetByIndustryIdDtoDataRes data = new GetByIndustryIdDtoDataRes();

		data.setId(industry.getId());
		data.setIndustryCode(industry.getIndustryCode());
		data.setIndustryName(industry.getIndustryName());
		data.setVersion(industry.getVersion());
		data.setIsActive(industry.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertIndustryDtoRes insert(InsertIndustryDtoReq data) throws Exception {
		InsertIndustryDtoRes insert = new InsertIndustryDtoRes();

		try {
			Industry industry = new Industry();
			industry.setIndustryCode(getAlphaNumericString(5));
			industry.setIndustryName(data.getIndustryName());
			industry.setCreatedBy(getId());
			
			begin();
			Industry industryInsert = industryDao.save(industry);
			commit();

			InsertIndustryDtoDataRes dataDto = new InsertIndustryDtoDataRes();
			dataDto.setId(industryInsert.getId());

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
	public UpdateIndustryDtoRes update(UpdateIndustryDtoReq data) throws Exception {
		UpdateIndustryDtoRes update = new UpdateIndustryDtoRes();

		try {
			if (data.getVersion() != null) {
				Industry industry = industryDao.findById(data.getId());
				industry.setIndustryName(data.getIndustryName());
				industry.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					industry.setIsActive(data.getIsActive());
				}

				begin();
				Industry userUpdate = industryDao.save(industry);
				commit();

				UpdateIndustryDtoDataRes dataDto = new UpdateIndustryDtoDataRes();
				dataDto.setVersion(userUpdate.getVersion());

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
	public DeleteByIndustryIdDtoRes deleteById(String id) throws Exception {
		DeleteByIndustryIdDtoRes deleteById = new DeleteByIndustryIdDtoRes();

		try {
			begin();
			boolean isDeleted = industryDao.deleteById(id);
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
