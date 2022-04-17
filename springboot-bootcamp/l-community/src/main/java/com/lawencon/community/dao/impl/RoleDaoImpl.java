package com.lawencon.community.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.model.Role;
import com.lawencon.model.SearchQuery;

@Repository
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {
	
	@Override
	public SearchQuery<Role> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Role> sq = new SearchQuery<>();
		List<Role> data = null;
		
		if(startPage == null || maxPage == null) {
			data = getAll();
			sq.setData(data);
		}else {
			if(query == null) {
				data = getAll(startPage, maxPage);
				int count = countAll().intValue();
				
				sq.setData(data);
				sq.setCount(count);
			}else {
				return super.getAll(query, startPage, maxPage, "roleName", "roleCode");
			}
		}
		
		return sq;
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
	public Long countAll() {
		return super.countAll();
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
			if(obj[3] != null) {				
				data.setCreatedBy(obj[3].toString());
			}
			data.setCreatedAt(((Timestamp)obj[4]).toLocalDateTime());
			data.setVersion(Integer.valueOf(obj[5].toString()));
			data.setIsActive(Boolean.valueOf(obj[6].toString()));
			
		}catch(NoResultException | NonUniqueResultException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	@Override
	public Role validateCode(String code) throws Exception {
		List<Role> types = createQuery("FROM Role WHERE roleCode = ?1", Role.class)
											.setParameter(1, code)
											.getResultList();
		
		return resultCheck(types);	
	}
	
	@Override
	public List<?> validateDelete(String id) throws Exception {
		String sql = "SELECT r.id FORM roles AS r WHERE r.id = ?1";
		
		List<?> listObj = createNativeQuery(sql).setParameter(1, id).setMaxResults(1).getResultList();
		List<String> result = new ArrayList<>();
		
		listObj.forEach(val -> {
			Object obj = (Object) val;
			result.add(obj != null ? obj.toString() : null);
		});
		
		return result;
	}
}
