package com.lawencon.community.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.model.Role;

@Repository
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {
	
	@Override
	public List<Role> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public Role findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public Role save(Role entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public Role findByCode(String code) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT id, role_name, role_code, created_by, created_at, version, is_active");
		builder.append(" FROM roles");
		builder.append(" WHERE role_code = :code");
		
		Role data = null;
		try {
			Object result = createNativeQuery(builder.toString())
								.setParameter("code", code)
								.getSingleResult();
			
			Object[] obj = (Object[]) result;
			data = new Role();
			
			data.setId(obj[0].toString());
			data.setRoleName(obj[1].toString());
			data.setRoleCode(obj[2].toString());
			data.setCreatedBy(obj[3].toString());
			data.setCreatedAt(((Timestamp)obj[4]).toLocalDateTime());
			data.setVersion(Integer.valueOf(obj[5].toString()));
			data.setIsActive(Boolean.valueOf(obj[6].toString()));
			
		}catch(NoResultException | NonUniqueResultException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
