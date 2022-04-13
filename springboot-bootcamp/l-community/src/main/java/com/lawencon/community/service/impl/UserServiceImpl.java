package com.lawencon.community.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.user.ChangePasswordDtoReq;
import com.lawencon.community.dto.user.DeleteByUserIdDtoRes;
import com.lawencon.community.dto.user.GetAllUserDtoDataRes;
import com.lawencon.community.dto.user.GetAllUserDtoRes;
import com.lawencon.community.dto.user.GetByUserIdDtoDataRes;
import com.lawencon.community.dto.user.GetByUserIdDtoRes;
import com.lawencon.community.dto.user.GetUserByEmailDtoDataRes;
import com.lawencon.community.dto.user.InsertUserDtoDataRes;
import com.lawencon.community.dto.user.InsertUserDtoReq;
import com.lawencon.community.dto.user.InsertUserDtoRes;
import com.lawencon.community.dto.user.UpdateUserDtoDataRes;
import com.lawencon.community.dto.user.UpdateUserDtoReq;
import com.lawencon.community.dto.user.UpdateUserDtoRes;
import com.lawencon.community.dto.user.VerificationCodeTemplate;
import com.lawencon.community.model.Role;
import com.lawencon.community.model.User;
import com.lawencon.community.service.UserService;
import com.lawencon.model.SearchQuery;

import freemarker.template.Configuration;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	private static final String text = "Kode Verifikasi Akun Anda : ";
//	private static final String subject = "Password App E-Learning";
	private static final String subject = "Registration Verification Code";
	private static final String email = "mustajabsa@gmail.com";
	private static final Boolean isActive = false;
	private UserDao userDao;
	private RoleDao roleDao;
	private PasswordEncoder passwordEncoder;
	private JavaMailSender mailSender;

	@Autowired
	public UserServiceImpl(UserDao userDao, RoleDao roleDoa, JavaMailSender mailSender) {
		this.userDao = userDao;
		this.roleDao = roleDoa;
		this.mailSender = mailSender;
	}
	
	@Autowired
    private Configuration freeMarkerConfiguration;

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public GetAllUserDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllUserDtoRes getAll = new GetAllUserDtoRes();

		SearchQuery<User> users = userDao.findAll(query, startPage, maxPage);
		List<GetAllUserDtoDataRes> listUser = new ArrayList<>();

		for (int i = 0; i < users.getData().size(); i++) {
			User user = users.getData().get(i);
			GetAllUserDtoDataRes data = new GetAllUserDtoDataRes();

			data.setId(user.getId());
			data.setUsername(user.getEmail());
			data.setPassword(user.getPassword());
			data.setVerificationCode(user.getVerificationCode());
			data.setRoleId(user.getRoleId().getId());
			data.setRoleName(user.getRoleId().getRoleName());
			data.setVersion(user.getVersion());
			data.setIsActive(user.getIsActive());

			listUser.add(data);
		}

		getAll.setData(listUser);
		getAll.setMsg(null);
		getAll.setTotal(users.getCount());

		return getAll;
	}

	@Override
	public GetByUserIdDtoRes findById(String id) throws Exception {
		GetByUserIdDtoRes getById = new GetByUserIdDtoRes();

		User user = userDao.findById(id);
		GetByUserIdDtoDataRes data = new GetByUserIdDtoDataRes();

		data.setId(user.getId());
		data.setUsername(user.getEmail());
		data.setPassword(user.getPassword());
		data.setVerificationCode(user.getVerificationCode());
		data.setRoleId(user.getRoleId().getId());
		data.setRoleName(user.getRoleId().getRoleName());
		data.setVersion(user.getVersion());
		data.setIsActive(user.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}

//	@Override
//	public InsertUserDtoRes insert(InsertUserDtoReq data) throws Exception {
//		InsertUserDtoRes insert = new InsertUserDtoRes();
//
//		try {
//			User user = new User();
//			user.setEmail(data.getUsername());
//
//			String password = data.getPassword();
//
//			String passwordEncode = passwordEncoder.encode(password);
//			user.setPassword(passwordEncode);
//			
//			String verificationCode = getAlphaNumericString(5);
//			user.setVerificationCode(verificationCode);
//
//			Role role = roleDao.findByCode(data.getRoleCode());
//			user.setRoleId(role);
//			user.setIsActive(isActive);
//			
//			begin();
//			User insertUser = userDao.save(user);
//			commit();
//
//			MimeMessage message = mailSender.createMimeMessage();
//			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
//					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//
//			messageHelper.setTo(data.getUsername());
//			messageHelper.setText(text + verificationCode, true);
//			messageHelper.setSubject(subject);
//			messageHelper.setFrom(email);
//
//			ExecutorService executor = Executors.newSingleThreadExecutor();
//
//			executor.submit(() -> {
//				mailSender.send(message);
//			});
//			executor.shutdown();
//
//			InsertUserDtoDataRes dataDto = new InsertUserDtoDataRes();
//			dataDto.setId(insertUser.getId());
//
//			insert.setData(dataDto);
//			insert.setMsg("Registrasi " + CommonConstant.SUCCESS.getDetail());
//		} catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			throw new Exception(e);
//		}
//
//		return insert;
//	}

	@Override
	public InsertUserDtoRes insert(InsertUserDtoReq data) throws Exception {
		InsertUserDtoRes dataRes = new InsertUserDtoRes();

		try {
			User user = new User();
			user.setEmail(data.getUsername());

			String password = data.getPassword();
			String encodedPassword = passwordEncoder.encode(password);
			user.setPassword(encodedPassword);

			String generatedVerificationCode = getRandomNumericString(5);
			user.setVerificationCode(generatedVerificationCode);

			Role role = roleDao.findByCode(data.getRoleCode());
			user.setRoleId(role);
			user.setIsActive(isActive);

			user.setVersion(0);
			user.setIsActive(false);

			begin();
			User insertUser = userDao.save(user);
			commit();
			
			VerificationCodeTemplate verifCodeTemplate = new VerificationCodeTemplate();
			verifCodeTemplate.setTo(insertUser.getEmail());
			verifCodeTemplate.setFrom(email);
			verifCodeTemplate.setSubject(subject);
			Map<String, Object> model = new HashMap<>();
//	        model.put("userEmail", insertUser.getEmail());
	        model.put("verificationCode", insertUser.getVerificationCode());
			verifCodeTemplate.setModel(model);
	        
			ExecutorService executor = Executors.newSingleThreadExecutor();

			executor.submit(() -> {
//				mailSender.send(messageHelper.getMimeMessage());
				sendEmail(verifCodeTemplate);
			});
			executor.shutdown();

			InsertUserDtoDataRes dataDto = new InsertUserDtoDataRes();
			dataDto.setId(insertUser.getId());

			dataRes.setData(dataDto);
			dataRes.setMsg("Registrasi " + CommonConstant.SUCCESS.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return dataRes;
	}

	@Override
	public UpdateUserDtoRes update(UpdateUserDtoReq data) throws Exception {
		UpdateUserDtoRes update = new UpdateUserDtoRes();

		try {
			if (data.getVersion() != null) {
				User user = userDao.findById(data.getId());

				user.setEmail(data.getEmail());

				String passwordEncode = passwordEncoder.encode(data.getPassword());
				user.setPassword(passwordEncode);

				Role role = roleDao.findById(data.getRoleId());
				user.setRoleId(role);

				user.setVersion(data.getVersion());
				user.setUpdatedBy(data.getId());

				if (data.getIsActive() != null) {
					user.setIsActive(data.getIsActive());
				}

				begin();
				User userUpdate = userDao.save(user);
				commit();

				UpdateUserDtoDataRes dataDto = new UpdateUserDtoDataRes();
				dataDto.setVersion(userUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail()
						+ ", Password " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}

	@Override
	public DeleteByUserIdDtoRes deleteById(String id) throws Exception {
		DeleteByUserIdDtoRes deleteById = new DeleteByUserIdDtoRes();

		try {
			begin();
			boolean isDeleted = userDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail()
						+ ", User " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}

	@Override
	public UpdateUserDtoRes forgotPassword(UpdateUserDtoReq data) throws Exception {
		UpdateUserDtoRes update = new UpdateUserDtoRes();

		try {
			if (data.getVersion() != null) {
				User user = userDao.findById(data.getId());

				user.setEmail(data.getEmail());

				String passwordEncode = passwordEncoder.encode(data.getPassword());
				user.setPassword(passwordEncode);

				Role role = roleDao.findById(data.getRoleId());
				user.setRoleId(role);

				user.setVersion(data.getVersion());
				user.setUpdatedBy(data.getId());

				if (data.getIsActive() != null) {
					user.setIsActive(data.getIsActive());
				}

				begin();
				User userUpdate = userDao.save(user);
				commit();

				UpdateUserDtoDataRes dataDto = new UpdateUserDtoDataRes();
				dataDto.setVersion(userUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail()
						+ ", Password " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}

	@Override
	public UpdateUserDtoRes changePassword(ChangePasswordDtoReq data) throws Exception {
		UpdateUserDtoRes update = new UpdateUserDtoRes();

		try {
			if (data.getVersion() != null) {
				User user = userDao.findById(data.getId());

				user.setEmail(data.getEmail());

				if (passwordEncoder.matches(data.getPassword(), user.getPassword()) == true) {
					String passwordEncode = passwordEncoder.encode(data.getNewPassword());
					user.setPassword(passwordEncode);
				}
				;

				Role role = roleDao.findById(data.getRoleId());
				user.setRoleId(role);

				user.setVersion(data.getVersion());
				user.setUpdatedBy(data.getId());

				if (data.getIsActive() != null) {
					user.setIsActive(data.getIsActive());
				}

				begin();
				User userUpdate = userDao.save(user);
				commit();

				UpdateUserDtoDataRes dataDto = new UpdateUserDtoDataRes();
				dataDto.setVersion(userUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail()
						+ ", Password " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}

	@Override
	public GetUserByEmailDtoDataRes findByUser(String email) throws Exception {
		GetUserByEmailDtoDataRes data = new GetUserByEmailDtoDataRes();
		User user = userDao.findByUser(email);

		data.setId(user.getId());
		data.setUsername(user.getEmail());
		data.setPassword(user.getPassword());
		data.setVerificationCode(user.getVerificationCode());
		data.setRoleId(user.getRoleId().getId());
		data.setRoleName(user.getRoleId().getRoleName());
		data.setVersion(user.getVersion());
		data.setIsActive(user.getIsActive());

		return data;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		GetUserByEmailDtoDataRes user = null;
		System.out.print(username);
		try {
			user = findByUser(username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(CommonConstant.INVALID_LOGIN.getDetail());
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public String getRandomNumericString(int n) {
		String randomNumericString = "0123456789";
		StringBuilder sb = new StringBuilder(n);
	
		for (int i = 0; i < n; i++) {
			int index = (int)(randomNumericString.length()* Math.random());
			while(i==0 && index==0) {
				index = (int)(randomNumericString.length()* Math.random());
			}
			sb.append(randomNumericString.charAt(index));
		}
		return sb.toString();
	}
	
	@Async
	public void sendEmail(VerificationCodeTemplate verifCode) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

//			messageHelper.setTo(data.getUsername());
			messageHelper.setSubject(verifCode.getSubject());
			messageHelper.setFrom(verifCode.getFrom());
			messageHelper.setTo(verifCode.getTo());
			verifCode.setContent(getContentFromTemplate(verifCode.getModel()));
			messageHelper.setText(verifCode.getContent(), true);
			mailSender.send(messageHelper.getMimeMessage());
		}catch(MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getContentFromTemplate(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils
                    .processTemplateIntoString(freeMarkerConfiguration.getTemplate("VerificationCodeEmailTemplate.flth"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
	
}
