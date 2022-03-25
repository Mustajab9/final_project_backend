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
import com.lawencon.community.dao.CategoryDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.attachment.GetAllAttachmentDtoDataRes;
import com.lawencon.community.dto.attachment.GetAllAttachmentDtoRes;
import com.lawencon.community.dto.attachment.GetByAttachmentIdDtoDataRes;
import com.lawencon.community.dto.attachment.GetByAttachmentIdDtoRes;
import com.lawencon.community.dto.category.DeleteByCategoryIdDtoRes;
import com.lawencon.community.dto.category.GetAllCategoryDtoRes;
import com.lawencon.community.dto.category.GetByCategoryIdDtoRes;
import com.lawencon.community.dto.category.InsertCategoryDtoReq;
import com.lawencon.community.dto.category.InsertCategoryDtoRes;
import com.lawencon.community.dto.category.UpdateCategoryDtoReq;
import com.lawencon.community.dto.category.UpdateCategoryDtoRes;
import com.lawencon.community.dto.user.DeleteByUserIdDtoRes;
import com.lawencon.community.dto.user.InsertUserDtoDataRes;
import com.lawencon.community.dto.user.InsertUserDtoRes;
import com.lawencon.community.dto.user.UpdateUserDtoDataRes;
import com.lawencon.community.dto.user.UpdateUserDtoRes;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;
import com.lawencon.community.service.CategoryService;

@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {
	private CategoryDao categoryDao;

	@Autowired
	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	@Override
	public GetAllCategoryDtoRes findAll(int startPage, int maxPage) throws Exception {
		GetAllAttachmentDtoRes getAll = new GetAllAttachmentDtoRes();

		List<Attachment> attachments = attachmentDao.findAll(startPage, maxPage);
		List<GetAllAttachmentDtoDataRes> listAttachment = new ArrayList<>();

		for (int i = 0; i < attachments.size(); i++) {
			Attachment attach = attachments.get(i);
			GetAllAttachmentDtoDataRes data = new GetAllAttachmentDtoDataRes();
			
			data.setId(attach.getId());
			data.setAttachmentCode(attach.getAttachmentCode());
			data.setAttachmentContent(attach.getAttachmentContent());
			data.setAttachmentExtension(attach.getAttachmentExtension());
			data.setVersion(attach.getVersion());
			data.setIsActive(attach.getIsActive());

			listAttachment.add(data);
		}

		getAll.setData(listAttachment);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByCategoryIdDtoRes findById(String id) throws Exception {
		GetByAttachmentIdDtoRes getById = new GetByAttachmentIdDtoRes();

		Attachment attach = attachmentDao.findById(id);
		GetByAttachmentIdDtoDataRes data = new GetByAttachmentIdDtoDataRes();

		data.setId(attach.getId());
		data.setAttachmentCode(attach.getAttachmentCode());
		data.setAttachmentContent(attach.getAttachmentContent());
		data.setAttachmentExtension(attach.getAttachmentExtension());
		data.setVersion(attach.getVersion());
		data.setIsActive(attach.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertCategoryDtoRes insert(InsertCategoryDtoReq data) throws Exception {
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
	public UpdateCategoryDtoRes update(UpdateCategoryDtoReq data) throws Exception {
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
	public DeleteByCategoryIdDtoRes deleteById(String id) throws Exception {
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
