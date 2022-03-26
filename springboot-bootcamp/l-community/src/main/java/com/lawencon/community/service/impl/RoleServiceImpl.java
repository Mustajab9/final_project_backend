package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class RoleServiceImpl extends BaseService implements RoleService {
	private RoleDao roleDao;

	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	@Override
	public GetAllRoleDtoRes findAll(int startPage, int maxPage) throws Exception {
		GetAllRoleDtoRes getAll = new GetAllRoleDtoRes();

		List<Role> roles = roleDao.findAll(startPage, maxPage);
		List<GetAllRoleDtoDataRes> roleList = new ArrayList<>();

		for (int i = 0; i < roles.size(); i++) {
			Role role = roles.get(i);
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
			Role role = new Role();
			role.setRoleName(data.getRoleName());
			role.setRoleCode(data.getRoleCode());
			
			begin();
			Role roleInsert = roleDao.save(role);
			commit();

			InsertRoleDtoDataRes dataDto = new InsertRoleDtoDataRes();
			dataDto.setId(roleInsert.getId());

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
	public UpdateRoleDtoRes update(UpdateRoleDtoReq data) throws Exception {
		UpdateRoleDtoRes update = new UpdateRoleDtoRes();

		try {
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
	public DeleteByRoleIdDtoRes deleteById(String id) throws Exception {
		DeleteByRoleIdDtoRes deleteById = new DeleteByRoleIdDtoRes();

		try {
			begin();
			boolean isDeleted = roleDao.deleteById(id);
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
}
