package com.lawencon.community.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {
	
	@Override
	public SearchQuery<User> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<User> sq = new SearchQuery<>();
		List<User> data = null;
		
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
				return super.getAll(query, startPage, maxPage, "email");
			}
		}
		
		return sq;
	}
	
	@Override
	public User findById(String id) throws Exception {
		return super.getById(id);

	}
	
	@Override
	public User save(User entity) throws Exception {
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
	public User findByUser(String email) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT u.id, u.email, u.password, u.verification_code, r.id AS role_id, r.role_code, r.role_name, u.version, u.is_active");
		builder.append(" FROM users u");
		builder.append(" INNER JOIN roles r ON r.id = u.role_id");
		builder.append(" WHERE u.email = :email");

		User user = null;
		try {
			Object result = createNativeQuery(builder.toString())
								.setParameter("email", email)
								.getSingleResult();

			Object[] obj = (Object[]) result;
			user = new User();
			user.setId(obj[0].toString());
			user.setEmail(obj[1].toString());
			user.setPassword(obj[2].toString());
			user.setVerificationCode(obj[3].toString());

			Role role = new Role();
			role.setId(obj[4].toString());
			role.setRoleCode(obj[5].toString());
			role.setRoleName(obj[6].toString());
			user.setRoleId(role);

			user.setVersion(Integer.valueOf(obj[7].toString()));
			user.setIsActive(Boolean.valueOf(obj[8].toString()));
			
		}catch(NoResultException | NonUniqueResultException e) {
			e.printStackTrace();
		}
		

		return user;
	}

}
