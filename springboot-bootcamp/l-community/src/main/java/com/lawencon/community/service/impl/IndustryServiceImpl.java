package com.lawencon.community.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.IndustryDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.industry.DeleteByIndustryIdDtoRes;
import com.lawencon.community.dto.industry.GetAllIndustryDtoRes;
import com.lawencon.community.dto.industry.GetByIndustryIdDtoRes;
import com.lawencon.community.dto.industry.InsertIndustryDtoReq;
import com.lawencon.community.dto.industry.InsertIndustryDtoRes;
import com.lawencon.community.dto.industry.UpdateIndustryDtoReq;
import com.lawencon.community.dto.industry.UpdateIndustryDtoRes;
import com.lawencon.community.dto.user.DeleteByUserIdDtoRes;
import com.lawencon.community.dto.user.GetAllUserDtoDataRes;
import com.lawencon.community.dto.user.GetAllUserDtoRes;
import com.lawencon.community.dto.user.GetByUserIdDtoDataRes;
import com.lawencon.community.dto.user.GetByUserIdDtoRes;
import com.lawencon.community.dto.user.InsertUserDtoDataRes;
import com.lawencon.community.dto.user.InsertUserDtoRes;
import com.lawencon.community.dto.user.UpdateUserDtoDataRes;
import com.lawencon.community.dto.user.UpdateUserDtoRes;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;
import com.lawencon.community.service.IndustryService;

@Service
public class IndustryServiceImpl extends BaseService implements IndustryService {
	private IndustryDao industryDao;

	@Autowired
	public IndustryServiceImpl(IndustryDao industryDao) {
		this.industryDao = industryDao;
	}
	
	@Override
	public GetAllIndustryDtoRes findAll(int startPage, int maxPage) throws Exception {
		GetAllUserDtoRes getAll = new GetAllUserDtoRes();

		List<User> users = userDao.findAll(startPage, maxPage);
		List<GetAllUserDtoDataRes> listUser = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			GetAllUserDtoDataRes data = new GetAllUserDtoDataRes();

			data.setId(user.getId());
			data.setUsername(user.getEmail());
			data.setPassword(user.getPassword());
			data.setRoleId(user.getRoleId().getId());
			data.setRoleName(user.getRoleId().getRoleName());
			data.setVersion(user.getVersion());
			data.setIsActive(user.getIsActive());

			listUser.add(data);
		}

		getAll.setData(listUser);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByIndustryIdDtoRes findById(String id) throws Exception {
		GetByUserIdDtoRes getById = new GetByUserIdDtoRes();

		User user = userDao.findById(id);
		GetByUserIdDtoDataRes data = new GetByUserIdDtoDataRes();

		data.setId(user.getId());
		data.setUsername(user.getEmail());
		data.setPassword(user.getPassword());
		data.setRoleId(user.getRoleId().getId());
		data.setRoleName(user.getRoleId().getRoleName());
		data.setVersion(user.getVersion());
		data.setIsActive(user.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertIndustryDtoRes insert(InsertIndustryDtoReq data) throws Exception {
		InsertUserDtoRes insert = new InsertUserDtoRes();

		try {
			User user = new User();
			user.setEmail(data.getUsername());

			String password = getAlphaNumericString(10);

			String passwordEncode = passwordEncoder.encode(password);
			user.setPassword(passwordEncode);

			Role role = roleDao.findById(data.getRoleId());
			user.setRoleId(role);

			begin();
			User insertUser = userDao.save(user);
			commit();

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

			messageHelper.setTo(data.getUsername());
			messageHelper.setText(text + password, true);
			messageHelper.setSubject(subject);
			messageHelper.setFrom(email);

			ExecutorService executor = Executors.newSingleThreadExecutor();

			executor.submit(() -> {
				mailSender.send(message);
			});
			executor.shutdown();

			InsertUserDtoDataRes dataDto = new InsertUserDtoDataRes();
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
	public UpdateIndustryDtoRes update(UpdateIndustryDtoReq data) throws Exception {
		UpdateUserDtoRes update = new UpdateUserDtoRes();

		try {
			if (data.getVersion() != null) {
				User user = userDao.findById(data.getId());

				user.setEmail(data.getEmail());
				user.setVersion(data.getVersion());

				user.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					user.setIsActive(data.getIsActive());
				}

				begin();
				User userUpdate = userDao.save(user);
				commit();

				UpdateUserDtoDataRes dataDto = new UpdateUserDtoDataRes();
				dataDto.setVersion(userUpdate.getVersion());

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
	public DeleteByIndustryIdDtoRes deleteById(String id) throws Exception {
		DeleteByUserIdDtoRes deleteById = new DeleteByUserIdDtoRes();

		try {
			begin();
			boolean isDeleted = userDao.deleteById(id);
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
