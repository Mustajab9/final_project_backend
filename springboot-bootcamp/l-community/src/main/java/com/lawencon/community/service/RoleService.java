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
	GetAllRoleDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetByRoleIdDtoRes findById(String id) throws Exception;
	InsertRoleDtoRes insert(InsertRoleDtoReq data) throws Exception;
	UpdateRoleDtoRes update(UpdateRoleDtoReq data) throws Exception;
	DeleteByRoleIdDtoRes deleteById(String id) throws Exception;
	GetByRoleCodeDtoRes findIdByCode(String code) throws Exception;	
}
