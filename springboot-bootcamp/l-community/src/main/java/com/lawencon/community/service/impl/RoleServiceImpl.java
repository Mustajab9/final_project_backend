package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.dto.role.DeleteByRoleIdDtoRes;
import com.lawencon.community.dto.role.GetAllRoleDtoDataRes;
import com.lawencon.community.dto.role.GetAllRoleDtoRes;
import com.lawencon.community.dto.role.GetByRoleCodeDtoDataRes;
import com.lawencon.community.dto.role.GetByRoleCodeDtoRes;
import com.lawencon.community.dto.role.GetByRoleIdDtoDataRes;
import com.lawencon.community.dto.role.GetByRoleIdDtoRes;
import com.lawencon.community.dto.role.InsertRoleDtoDataRes;
import com.lawencon.community.dto.role.InsertRoleDtoReq;
import com.lawencon.community.dto.role.InsertRoleDtoRes;
import com.lawencon.community.dto.role.UpdateRoleDtoDataRes;
import com.lawencon.community.dto.role.UpdateRoleDtoReq;
import com.lawencon.community.dto.role.UpdateRoleDtoRes;
import com.lawencon.community.model.Role;
import com.lawencon.community.service.RoleService;
import com.lawencon.model.SearchQuery;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {
	private RoleDao roleDao;

	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	@Override
	public GetAllRoleDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllRoleDtoRes getAll = new GetAllRoleDtoRes();

		SearchQuery<Role> roles = roleDao.findAll(query, startPage, maxPage);
		List<GetAllRoleDtoDataRes> roleList = new ArrayList<>();

		for (int i = 0; i < roles.getData().size(); i++) {
			Role role = roles.getData().get(i);
			GetAllRoleDtoDataRes data = new GetAllRoleDtoDataRes();

			data.setId(role.getId());
			data.setRoleName(role.getRoleName());
			data.setRoleCode(role.getRoleCode());
			data.setVersion(role.getVersion());
			data.setIsActive(role.getIsActive());

			roleList.add(data);
		}

		getAll.setData(roleList);
		getAll.setMsg(null);
		getAll.setTotal(roles.getCount());

		return getAll;
	}
	
	@Override
	public GetByRoleIdDtoRes findById(String id) throws Exception {
		GetByRoleIdDtoRes getById = new GetByRoleIdDtoRes();

		Role role = roleDao.findById(id);
		GetByRoleIdDtoDataRes data = new GetByRoleIdDtoDataRes();

		data.setId(role.getId());
		data.setRoleName(role.getRoleName());
		data.setRoleCode(role.getRoleCode());
		data.setVersion(role.getVersion());
		data.setIsActive(role.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertRoleDtoRes insert(InsertRoleDtoReq data) throws Exception {
		InsertRoleDtoRes insert = new InsertRoleDtoRes();

		try {
			validateInsert(data);
			
			Role role = new Role();
			role.setRoleName(data.getRoleName());
			role.setRoleCode(data.getRoleCode());
			
			begin();
			Role roleInsert = roleDao.save(role);
			commit();

			InsertRoleDtoDataRes dataDto = new InsertRoleDtoDataRes();
			dataDto.setId(roleInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(CommonConstant.ACTION_ADD.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Role " + CommonConstant.HAS_BEEN_ADDED.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdateRoleDtoRes update(UpdateRoleDtoReq data) throws Exception {
		UpdateRoleDtoRes update = new UpdateRoleDtoRes();

		try {
			validateUpdate(data);
			
			if (data.getVersion() != null) {
				Role role = roleDao.findById(data.getId());

				role.setRoleName(data.getRoleName());
				role.setVersion(data.getVersion());

				role.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					role.setIsActive(data.getIsActive());
				}

				begin();
				Role roleUpdate = roleDao.save(role);
				commit();

				UpdateRoleDtoDataRes dataDto = new UpdateRoleDtoDataRes();
				dataDto.setVersion(roleUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Role " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByRoleIdDtoRes deleteById(String id) throws Exception {
		DeleteByRoleIdDtoRes deleteById = new DeleteByRoleIdDtoRes();

		try {
			validateDelete(id);
			
			begin();
			boolean isDeleted = roleDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Role " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetByRoleCodeDtoRes findIdByCode(String code) throws Exception {
		GetByRoleCodeDtoRes getById = new GetByRoleCodeDtoRes();

		Role role = roleDao.findByCode(code);
		GetByRoleCodeDtoDataRes data = new GetByRoleCodeDtoDataRes();

		data.setId(role.getId());
		data.setRoleName(role.getRoleName());
		data.setRoleCode(role.getRoleCode());
		data.setVersion(role.getVersion());
		data.setIsActive(role.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	private void validateInsert(InsertRoleDtoReq data) throws Exception {
		if (data.getRoleCode() == null || data.getRoleCode().trim().equals("")) {
			throw new Exception("Role Code Cant Null");
		} else {
			Role role = roleDao.findByCode(data.getRoleCode());
			if (role != null) {
				throw new Exception("Role Code Already Exsist");
			}
			if (data.getRoleName() == null || data.getRoleName().trim().equals("")) {
				throw new Exception("Role Name Cant Null");
			}
		}
	}

	private void validateUpdate(UpdateRoleDtoReq data) throws Exception {
		if (data.getId() == null || data.getId().trim().equals("")) {
			throw new Exception("Role Id Cant Null");
		} else {
			Role role = roleDao.findById(data.getId());
			if (data.getRoleName() == null || data.getRoleName().trim().equals("")) {
				throw new Exception("Role Name Cant Null");
			}
			if (role.getVersion() != data.getVersion()) {
				throw new Exception("Role You Update Already Update By Someone");
			}
		}
	}

	private void validateDelete(String id) throws Exception {
		Role role = roleDao.findById(id);
		
		if(role == null) {
			throw new Exception("Role Id Not Exsist");
		}
	}
}
