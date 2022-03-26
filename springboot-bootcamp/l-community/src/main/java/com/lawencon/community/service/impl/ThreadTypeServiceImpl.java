package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ThreadTypeDao;
import com.lawencon.community.dto.threadtype.DeleteByThreadTypeIdDtoRes;
import com.lawencon.community.dto.threadtype.GetAllThreadTypeDtoDataRes;
import com.lawencon.community.dto.threadtype.GetAllThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.GetByThreadTypeIdDtoDataRes;
import com.lawencon.community.dto.threadtype.GetByThreadTypeIdDtoRes;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoDataRes;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoReq;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoDataRes;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoReq;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoRes;
import com.lawencon.community.model.ThreadType;
import com.lawencon.community.service.ThreadTypeService;

@Service
public class ThreadTypeServiceImpl extends BaseService implements ThreadTypeService {
	private ThreadTypeDao typeDao;

	@Autowired
	public ThreadTypeServiceImpl(ThreadTypeDao typeDao) {
		this.typeDao = typeDao;
	}
	
	@Override
	public GetAllThreadTypeDtoRes findAll(int startPage, int maxPage) throws Exception {
		GetAllThreadTypeDtoRes getAll = new GetAllThreadTypeDtoRes();

		List<ThreadType> types = typeDao.findAll(startPage, maxPage);
		List<GetAllThreadTypeDtoDataRes> listUser = new ArrayList<>();

		for (int i = 0; i < types.size(); i++) {
			ThreadType threadType = types.get(i);
			GetAllThreadTypeDtoDataRes data = new GetAllThreadTypeDtoDataRes();

			data.setId(threadType.getId());
			data.setTypeCode(threadType.getTypeCode());
			data.setTypeName(threadType.getTypeName());
			data.setVersion(threadType.getVersion());
			data.setIsActive(threadType.getIsActive());

			listUser.add(data);
		}

		getAll.setData(listUser);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByThreadTypeIdDtoRes findById(String id) throws Exception {
		GetByThreadTypeIdDtoRes getById = new GetByThreadTypeIdDtoRes();

		ThreadType threadType = typeDao.findById(id);
		GetByThreadTypeIdDtoDataRes data = new GetByThreadTypeIdDtoDataRes();

		data.setId(threadType.getId());
		data.setTypeCode(threadType.getTypeCode());
		data.setTypeName(threadType.getTypeName());
		data.setVersion(threadType.getVersion());
		data.setIsActive(threadType.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertThreadTypeDtoRes insert(InsertThreadTypeDtoReq data) throws Exception {
		InsertThreadTypeDtoRes insert = new InsertThreadTypeDtoRes();

		try {
			ThreadType threadType = new ThreadType();

			threadType.setTypeCode(data.getTypeCode());
			threadType.setTypeName(data.getTypeName());
			threadType.setCreatedBy(getId());

			begin();
			ThreadType insertUser = typeDao.save(threadType);
			commit();

			InsertThreadTypeDtoDataRes dataDto = new InsertThreadTypeDtoDataRes();
			dataDto.setId(insertUser.getId());

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
	public UpdateThreadTypeDtoRes update(UpdateThreadTypeDtoReq data) throws Exception {
		UpdateThreadTypeDtoRes update = new UpdateThreadTypeDtoRes();

		try {
			if (data.getVersion() != null) {
				ThreadType threadType = typeDao.findById(data.getId());

				threadType.setTypeName(data.getTypeName());
				threadType.setVersion(data.getVersion());
				threadType.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					threadType.setIsActive(data.getIsActive());
				}

				begin();
				ThreadType threadTypeUpdate = typeDao.save(threadType);
				commit();

				UpdateThreadTypeDtoDataRes dataDto = new UpdateThreadTypeDtoDataRes();
				dataDto.setVersion(threadTypeUpdate.getVersion());

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
	public DeleteByThreadTypeIdDtoRes deleteById(String id) throws Exception {
		DeleteByThreadTypeIdDtoRes deleteById = new DeleteByThreadTypeIdDtoRes();

		try {
			begin();
			boolean isDeleted = typeDao.deleteById(id);
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
