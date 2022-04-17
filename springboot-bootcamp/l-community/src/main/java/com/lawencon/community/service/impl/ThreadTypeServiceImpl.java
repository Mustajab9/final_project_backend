package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
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
import com.lawencon.model.SearchQuery;

@Service
public class ThreadTypeServiceImpl extends BaseService implements ThreadTypeService {
	private ThreadTypeDao typeDao;

	@Autowired
	public ThreadTypeServiceImpl(ThreadTypeDao typeDao) {
		this.typeDao = typeDao;
	}
	
	@Override
	public GetAllThreadTypeDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllThreadTypeDtoRes getAll = new GetAllThreadTypeDtoRes();

		SearchQuery<ThreadType> types = typeDao.findAll(query, startPage, maxPage);
		List<GetAllThreadTypeDtoDataRes> listUser = new ArrayList<>();

		for (int i = 0; i < types.getData().size(); i++) {
			ThreadType threadType = types.getData().get(i);
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
		getAll.setTotal(types.getCount());

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
			validateInsert(data);
			
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
			insert.setMsg(CommonConstant.ACTION_ADD + " " + CommonConstant.SUCCESS + ", Thread Type " + CommonConstant.HAS_BEEN_ADDED);
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
			validateUpdate(data);
			
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
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Thread Type " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
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
			validateDelete(id);
			
			begin();
			boolean isDeleted = typeDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Thread Type " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	private void validateInsert(InsertThreadTypeDtoReq data) throws Exception {
		if (data.getTypeCode() == null || data.getTypeCode().trim().equals("")) {
			throw new Exception("Type Code Cant Null");
		} else {
			ThreadType type = typeDao.findByCode(data.getTypeCode());
			if (type != null) {
				throw new Exception("Type Code Already Exsist");
			}
			if (data.getTypeName() == null || data.getTypeName().trim().equals("")) {
				throw new Exception("Type Name Cant Null");
			}
		}
	}

	private void validateUpdate(UpdateThreadTypeDtoReq data) throws Exception {
		if (data.getId() == null || data.getId().trim().equals("")) {
			throw new Exception("Type Id Cant Null");
		} else {
			ThreadType type = typeDao.findById(data.getId());
			if (data.getTypeName() == null || data.getTypeName().trim().equals("")) {
				throw new Exception("Type Name Cant Null");
			}
			if (type.getVersion() != data.getVersion()) {
				throw new Exception("Type You Update Already Update By Someone");
			}
		}
	}

	private void validateDelete(String id) throws Exception {
		ThreadType type = typeDao.findById(id);
		
		if(type == null) {
			throw new Exception("Type Id Not Exsist");
		}
	}
}
