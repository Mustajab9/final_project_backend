package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;

@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {
	
	@Override
	public List<User> findAll(int startPage, int maxPage) throws Exception {
		return super.getAll(startPage, maxPage);
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
	public User findByUser(String email) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT u.id, u.email, u.password, u.verification_code, r.id AS role_id, r.role_code, r.role_name, u.version, u.is_active");
		builder.append(" FROM users u");
		builder.append(" INNER JOIN roles r ON r.id = u.role_id");
		builder.append(" WHERE u.email = :email");

		Object result = createNativeQuery(builder.toString()).setParameter("email", email).getSingleResult();

		Object[] obj = (Object[]) result;
		User user = new User();
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

		return user;
	}

}
