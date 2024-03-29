package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.AttachmentDao;
import com.lawencon.community.dao.IndustryDao;
import com.lawencon.community.dao.PositionDao;
import com.lawencon.community.dao.ProfilesDao;
import com.lawencon.community.dao.RegencyDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.profiles.DeleteByProfilesIdDtoRes;
import com.lawencon.community.dto.profiles.GetAllProfilesDtoDataRes;
import com.lawencon.community.dto.profiles.GetAllProfilesDtoRes;
import com.lawencon.community.dto.profiles.GetByProfilesIdDtoDataRes;
import com.lawencon.community.dto.profiles.GetByProfilesIdDtoRes;
import com.lawencon.community.dto.profiles.GetProfileByUserDtoDataRes;
import com.lawencon.community.dto.profiles.GetProfileByUserDtoRes;
import com.lawencon.community.dto.profiles.InsertProfilesDtoDataRes;
import com.lawencon.community.dto.profiles.InsertProfilesDtoReq;
import com.lawencon.community.dto.profiles.InsertProfilesDtoRes;
import com.lawencon.community.dto.profiles.UpdateProfilesDtoDataRes;
import com.lawencon.community.dto.profiles.UpdateProfilesDtoReq;
import com.lawencon.community.dto.profiles.UpdateProfilesDtoRes;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Industry;
import com.lawencon.community.model.Position;
import com.lawencon.community.model.Profiles;
import com.lawencon.community.model.Regency;
import com.lawencon.community.model.User;
import com.lawencon.community.service.ProfilesService;

@Service
public class ProfilesServiceImpl extends BaseService implements ProfilesService {
	private ProfilesDao profilesDao;
	private UserDao userDao;
	private IndustryDao industryDao;
	private PositionDao positionDao;
	private RegencyDao regencyDao;
	private AttachmentDao attachmentDao;

	@Autowired
	public ProfilesServiceImpl(ProfilesDao profilesDao, UserDao userDao, 
			IndustryDao industryDao, PositionDao positionDao,
			RegencyDao regencyDao, AttachmentDao attachmentDao) {
		this.profilesDao = profilesDao;
		this.userDao = userDao;
		this.industryDao = industryDao;
		this.positionDao = positionDao;
		this.regencyDao = regencyDao;
		this.attachmentDao = attachmentDao;
	}
	
	@Override
	public GetAllProfilesDtoRes findAll() throws Exception {
		GetAllProfilesDtoRes getAll = new GetAllProfilesDtoRes();

		List<Profiles> profiles = profilesDao.findAll();
		List<GetAllProfilesDtoDataRes> profileList = new ArrayList<>();

		for (int i = 0; i < profiles.size(); i++) {
			Profiles profile = profiles.get(i);
			GetAllProfilesDtoDataRes data = new GetAllProfilesDtoDataRes();

			data.setId(profile.getId());
			data.setProfileCode(profile.getProfileCode());
			data.setProfileName(profile.getProfileName());
			data.setProfileCompany(profile.getProfileCompany());
			data.setProfilePhone(profile.getProfilePhone());
			data.setProfilePortalCode(profile.getProfilePostalCode());
			
			if(profile.getProfileImage() != null) {				
				data.setProfileImageId(profile.getProfileImage().getId());
				data.setProfileImageExtension(profile.getProfileImage().getAttachmentExtension());
			}
			
			data.setUserId(profile.getUserId().getId());
			data.setEmail(profile.getUserId().getEmail());
			data.setPassword(profile.getUserId().getPassword());
			
			if(profile.getIndustryId() != null) {
				data.setIndustryId(profile.getIndustryId().getId());
				data.setIndustyName(profile.getIndustryId().getIndustryName());
			}
			
			if(profile.getPositionId() != null) {
				data.setPositionId(profile.getPositionId().getId());
				data.setPositionName(profile.getPositionId().getPositionName());						
			}
			
			if(profile.getRegencyId() != null) {
				data.setProvinceId(profile.getRegencyId().getProvinceId().getId());
				data.setProvinceName(profile.getRegencyId().getProvinceId().getProvinceName());
				data.setProvinceCode(profile.getRegencyId().getProvinceId().getProvinceCode());
				data.setRegencyId(profile.getRegencyId().getId());
				data.setRegencyCode(profile.getRegencyId().getRegencyCode());
				data.setRegencyName(profile.getRegencyId().getRegencyName());
			}
			
			data.setVersion(profile.getVersion());
			data.setIsActive(profile.getIsActive());

			profileList.add(data);
		}

		getAll.setData(profileList);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByProfilesIdDtoRes findById(String id) throws Exception {
		GetByProfilesIdDtoRes getById = new GetByProfilesIdDtoRes();

		Profiles profile = profilesDao.findById(id);
		GetByProfilesIdDtoDataRes data = new GetByProfilesIdDtoDataRes();

		data.setId(profile.getId());
		data.setProfileCode(profile.getProfileCode());
		data.setProfileName(profile.getProfileName());
		data.setProfileCompany(profile.getProfileCompany());
		data.setProfilePhone(profile.getProfilePhone());
		data.setProfilePortalCode(profile.getProfilePostalCode());
		
		if(profile.getProfileImage() != null) {				
			data.setProfileImageId(profile.getProfileImage().getId());
			data.setProfileImageExtension(profile.getProfileImage().getAttachmentExtension());
		}
		
		data.setUserId(profile.getUserId().getId());
		data.setEmail(profile.getUserId().getEmail());
		data.setPassword(profile.getUserId().getPassword());
		
		if(profile.getIndustryId() != null) {
			data.setIndustryId(profile.getIndustryId().getId());
			data.setIndustyName(profile.getIndustryId().getIndustryName());
		}
		
		if(profile.getPositionId() != null) {
			data.setPositionId(profile.getPositionId().getId());
			data.setPositionName(profile.getPositionId().getPositionName());						
		}
		
		if(profile.getRegencyId() != null) {
			data.setProvinceId(profile.getRegencyId().getProvinceId().getId());
			data.setProvinceName(profile.getRegencyId().getProvinceId().getProvinceName());
			data.setProvinceCode(profile.getRegencyId().getProvinceId().getProvinceCode());
			data.setRegencyId(profile.getRegencyId().getId());
			data.setRegencyCode(profile.getRegencyId().getRegencyCode());
			data.setRegencyName(profile.getRegencyId().getRegencyName());
		}
		
		data.setVersion(profile.getVersion());
		data.setIsActive(profile.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertProfilesDtoRes insert(InsertProfilesDtoReq data) throws Exception {
		InsertProfilesDtoRes insert = new InsertProfilesDtoRes();

		try {
			validateInsert(data);			
			
			Profiles profile = new Profiles();
			String code = getAlphaNumericString(5);
			profile.setProfileCode(code);
			profile.setProfileName(data.getProfileName());
			profile.setProfileCompany(data.getProfileCompany());
			profile.setProfilePhone(data.getProfilePhone());
			
			User user = userDao.findById(data.getUserId());
			profile.setUserId(user);
			
			Industry industry = industryDao.findById(data.getIndustryId());
			profile.setIndustryId(industry);
			
			Position position = positionDao.findById(data.getPositionId());
			profile.setPositionId(position);
			
			profile.setCreatedBy(data.getUserId());
			
			begin();
			Profiles profileInsert = profilesDao.save(profile);
			commit();

			InsertProfilesDtoDataRes dataDto = new InsertProfilesDtoDataRes();
			dataDto.setId(profileInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(null);
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdateProfilesDtoRes update(String content, MultipartFile file) throws Exception {
		UpdateProfilesDtoRes update = new UpdateProfilesDtoRes();

		try {
			UpdateProfilesDtoReq data = new ObjectMapper().readValue(content, UpdateProfilesDtoReq.class);
			System.out.println("VERSION: " + data.getVersion());
			if (data.getVersion() != null) {
				Profiles profile = profilesDao.findById(data.getId());

				profile.setProfileName(data.getProfileName());
				profile.setProfileCompany(data.getProfileCompany());
				
				Industry industry = industryDao.findById(data.getIndustryId());
				profile.setIndustryId(industry);
				
				Position position = positionDao.findById(data.getPositionId());
				profile.setPositionId(position);
				
				Regency regency = regencyDao.findById(data.getRegencyId());
				profile.setRegencyId(regency);
				profile.setVersion(data.getVersion());
				profile.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					profile.setIsActive(data.getIsActive());
				}
				
				System.out.println("FILE: " + file);
				
				if (file != null) {
					Attachment attachment = new Attachment();
					attachment.setAttachmentCode(getAlphaNumericString(5));
					attachment.setAttachmentContent(file.getBytes());
					
					String extension = fileExtensionName(file);
					attachment.setAttachmentExtension(extension);
					
					attachment.setCreatedBy(getId());
					attachment.setVersion(0);
					attachment.setIsActive(true);
					
					begin();
					Attachment attachmentInsert = attachmentDao.save(attachment);
					commit();
					
					profile.setProfileImage(attachmentInsert);
				}
				
				begin();
				Profiles profileUpdate = profilesDao.save(profile);
				commit();

				UpdateProfilesDtoDataRes dataDto = new UpdateProfilesDtoDataRes();
				dataDto.setVersion(profileUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Profile " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByProfilesIdDtoRes deleteById(String id) throws Exception {
		DeleteByProfilesIdDtoRes deleteById = new DeleteByProfilesIdDtoRes();

		try {
			begin();
			boolean isDeleted = profilesDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Profiles " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetProfileByUserDtoRes findByUser() throws Exception {
		GetProfileByUserDtoRes getByUser = new GetProfileByUserDtoRes();

		Profiles profile = profilesDao.findByUser(getId());
		GetProfileByUserDtoDataRes data = new GetProfileByUserDtoDataRes();

		data.setId(profile.getId());
		
		data.setProfileName(profile.getProfileName());
		data.setProfileCompany(profile.getProfileCompany());
		data.setProfilePhone(profile.getProfilePhone());
		
		if(profile.getUserId() != null) {
			data.setUserId(profile.getUserId().getId());
			data.setEmail(profile.getUserId().getEmail());			
		}
		
		if(profile.getProfileImage() != null) {				
			data.setProfileImageId(profile.getProfileImage().getId());
			data.setProfileImageExtension(profile.getProfileImage().getAttachmentExtension());
		}
		
		if(profile.getIndustryId() != null) {
			data.setIndustryId(profile.getIndustryId().getId());
			data.setIndustyName(profile.getIndustryId().getIndustryName());
		}
		
		if(profile.getPositionId() != null) {
			data.setPositionId(profile.getPositionId().getId());
			data.setPositionName(profile.getPositionId().getPositionName());						
		}
		
		if(profile.getRegencyId() != null) {
			data.setProvinceId(profile.getRegencyId().getProvinceId().getId());
			data.setProvinceName(profile.getRegencyId().getProvinceId().getProvinceName());
			data.setRegencyId(profile.getRegencyId().getId());
			data.setRegencyName(profile.getRegencyId().getRegencyName());
		}
		
		data.setRoleCode(profile.getCreatedBy());
		data.setVersion(profile.getVersion());
		data.setIsActive(profile.getIsActive());

		getByUser.setData(data);
		getByUser.setMsg(null);

		return getByUser;
	}
	
	private void validateInsert(InsertProfilesDtoReq data) throws Exception {
		if (data.getUserId() == null || data.getUserId().trim().equals("")) {
			throw new Exception("User Id Cant Null");
		} else {
			if (data.getIndustryId() == null || data.getIndustryId().trim().equals("")) {
				throw new Exception("Industry Cant Null");
			}
			if (data.getPositionId() == null || data.getPositionId().trim().equals("")) {
				throw new Exception("Position Cant Null");
			}
			if (data.getProfileName() == null || data.getProfileName().trim().equals("")) {
				throw new Exception("Profile Name Cant Null");
			}
			if (data.getProfileCompany() == null || data.getProfileCompany().trim().equals("")) {
				throw new Exception("Company Name Cant Null");
			}
			if (data.getProfilePhone() == null || data.getProfilePhone().trim().equals("")) {
				throw new Exception("Profile Phone Cant Null");
			}
		}
	}
}
