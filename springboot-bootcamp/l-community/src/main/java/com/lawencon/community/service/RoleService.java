package com.lawencon.community.service;

import com.lawencon.community.dto.role.DeleteByRoleIdDtoRes;
import com.lawencon.community.dto.role.GetAllRoleDtoRes;
import com.lawencon.community.dto.role.GetByRoleCodeDtoRes;
import com.lawencon.community.dto.role.GetByRoleIdDtoRes;
import com.lawencon.community.dto.role.InsertRoleDtoReq;
import com.lawencon.community.dto.role.InsertRoleDtoRes;
import com.lawencon.community.dto.role.UpdateRoleDtoReq;
import com.lawencon.community.dto.role.UpdateRoleDtoRes;

public interface RoleService {
	public GetAllRoleDtoRes findAll() throws Exception;
	public GetByRoleIdDtoRes findById(Long id) throws Exception;
	public InsertRoleDtoRes insert(InsertRoleDtoReq data) throws Exception;
	public UpdateRoleDtoRes update(UpdateRoleDtoReq data) throws Exception;
	public DeleteByRoleIdDtoRes deleteById(Long id) throws Exception;
	public GetByRoleCodeDtoRes findIdByCode(String code) throws Exception;	
}
