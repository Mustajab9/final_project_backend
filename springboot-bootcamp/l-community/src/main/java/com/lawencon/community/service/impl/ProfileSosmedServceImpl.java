package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ProfileSosmedDao;
import com.lawencon.community.dao.ProfilesDao;
import com.lawencon.community.dao.SocialMediaDao;
import com.lawencon.community.dto.profilesosmed.DeleteByProfileSosmedIdDtoRes;
import com.lawencon.community.dto.profilesosmed.GetAllProfileSosmedDtoDataRes;
import com.lawencon.community.dto.profilesosmed.GetAllProfileSosmedDtoRes;
import com.lawencon.community.dto.profilesosmed.GetByProfileSosmedIdDtoDataRes;
import com.lawencon.community.dto.profilesosmed.GetByProfileSosmedIdDtoRes;
import com.lawencon.community.dto.profilesosmed.GetProfileSosmedByUserDtoDataRes;
import com.lawencon.community.dto.profilesosmed.GetProfileSosmedByUserDtoRes;
import com.lawencon.community.dto.profilesosmed.InsertProfileSosmedDtoDataRes;
import com.lawencon.community.dto.profilesosmed.InsertProfileSosmedDtoReq;
import com.lawencon.community.dto.profilesosmed.InsertProfileSosmedDtoRes;
import com.lawencon.community.dto.profilesosmed.UpdateProfileSosmedDtoDataRes;
import com.lawencon.community.dto.profilesosmed.UpdateProfileSosmedDtoReq;
import com.lawencon.community.dto.profilesosmed.UpdateProfileSosmedDtoRes;
import com.lawencon.community.model.ProfileSosmed;
import com.lawencon.community.model.Profiles;
import com.lawencon.community.model.SocialMedia;
import com.lawencon.community.service.ProfileSosmedService;

@Service
public class ProfileSosmedServceImpl extends BaseService implements ProfileSosmedService {
	private ProfileSosmedDao profileSosmedDao;
	private ProfilesDao profileDao;
	private SocialMediaDao socialMediaDao;

	@Autowired
	public ProfileSosmedServceImpl(ProfileSosmedDao profileSosmedDao, ProfilesDao profileDao, SocialMediaDao socialMediaDao) {
		this.profileSosmedDao = profileSosmedDao;
		this.profileDao = profileDao;
		this.socialMediaDao = socialMediaDao;
	}
	
	@Override
	public GetAllProfileSosmedDtoRes findAll() throws Exception {
		GetAllProfileSosmedDtoRes getAll = new GetAllProfileSosmedDtoRes();

		List<ProfileSosmed> profileSosmeds = profileSosmedDao.findAll();
		List<GetAllProfileSosmedDtoDataRes> profileList = new ArrayList<>();

		for (int i = 0; i < profileSosmeds.size(); i++) {
			ProfileSosmed profileSosmed = profileSosmeds.get(i);
			GetAllProfileSosmedDtoDataRes data = new GetAllProfileSosmedDtoDataRes();

			data.setId(profileSosmed.getId());
			data.setAccountName(profileSosmed.getAccountName());
			data.setProfileId(profileSosmed.getProfileId().getId());
			data.setProfileCode(profileSosmed.getProfileId().getProfileCode());
			data.setProfileName(profileSosmed.getProfileId().getProfileName());
			data.setProfileCompany(profileSosmed.getProfileId().getProfileCompany());
			data.setProfilePortalCode(profileSosmed.getProfileId().getProfilePortalCode());
			data.setUserId(profileSosmed.getProfileId().getUserId().getId());
			data.setEmail(profileSosmed.getProfileId().getUserId().getEmail());
			data.setSocialMediaid(profileSosmed.getSocialMediaId().getId());
			data.setSocialMediaName(profileSosmed.getSocialMediaId().getSocialMediaName());
			data.setSocialMediaCode(profileSosmed.getSocialMediaId().getSocialMediaCode());
			
			data.setVersion(profileSosmed.getVersion());
			data.setIsActive(profileSosmed.getIsActive());

			profileList.add(data);
		}

		getAll.setData(profileList);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByProfileSosmedIdDtoRes findById(String id) throws Exception {
		GetByProfileSosmedIdDtoRes getById = new GetByProfileSosmedIdDtoRes();

		ProfileSosmed profileSosmed = profileSosmedDao.findById(id);
		GetByProfileSosmedIdDtoDataRes data = new GetByProfileSosmedIdDtoDataRes();

		data.setId(profileSosmed.getId());
		data.setAccountName(profileSosmed.getAccountName());
		data.setProfileId(profileSosmed.getProfileId().getId());
		data.setProfileCode(profileSosmed.getProfileId().getProfileCode());
		data.setProfileName(profileSosmed.getProfileId().getProfileName());
		data.setProfileCompany(profileSosmed.getProfileId().getProfileCompany());
		data.setProfilePortalCode(profileSosmed.getProfileId().getProfilePortalCode());
		data.setUserId(profileSosmed.getProfileId().getUserId().getId());
		data.setEmail(profileSosmed.getProfileId().getUserId().getEmail());
		data.setSocialMediaid(profileSosmed.getSocialMediaId().getId());
		data.setSocialMediaName(profileSosmed.getSocialMediaId().getSocialMediaName());
		data.setSocialMediaCode(profileSosmed.getSocialMediaId().getSocialMediaCode());
		data.setVersion(profileSosmed.getVersion());
		data.setIsActive(profileSosmed.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertProfileSosmedDtoRes insert(InsertProfileSosmedDtoReq data) throws Exception {
		InsertProfileSosmedDtoRes insert = new InsertProfileSosmedDtoRes();

		try {
			ProfileSosmed profileSosmed = new ProfileSosmed();
			profileSosmed.setAccountName(data.getAccountName());
			
			Profiles profile = profileDao.findById(data.getProfileId());
			profileSosmed.setProfileId(profile);

			SocialMedia socialMedia = socialMediaDao.findById(data.getSocialMediaId());
			profileSosmed.setSocialMediaId(socialMedia);
			profileSosmed.setCreatedBy(getId());
			
			begin();
			ProfileSosmed profileSosmedInsert = profileSosmedDao.save(profileSosmed);
			commit();

			InsertProfileSosmedDtoDataRes dataDto = new InsertProfileSosmedDtoDataRes();
			dataDto.setId(profileSosmedInsert.getId());

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
	public UpdateProfileSosmedDtoRes update(UpdateProfileSosmedDtoReq data) throws Exception {
		UpdateProfileSosmedDtoRes update = new UpdateProfileSosmedDtoRes();

		try {
			if (data.getVersion() != null) {
				ProfileSosmed profileSosmed = profileSosmedDao.findById(data.getId());

				profileSosmed.setAccountName(data.getAccountName());
				profileSosmed.setVersion(data.getVersion());
				profileSosmed.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					profileSosmed.setIsActive(data.getIsActive());
				}

				begin();
				ProfileSosmed profileSosmedUpdate = profileSosmedDao.save(profileSosmed);
				commit();

				UpdateProfileSosmedDtoDataRes dataDto = new UpdateProfileSosmedDtoDataRes();
				dataDto.setVersion(profileSosmedUpdate.getVersion());

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
	public DeleteByProfileSosmedIdDtoRes deleteById(String id) throws Exception {
		DeleteByProfileSosmedIdDtoRes deleteById = new DeleteByProfileSosmedIdDtoRes();

		try {
			begin();
			boolean isDeleted = profileSosmedDao.deleteById(id);
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
	
	@Override
	public GetProfileSosmedByUserDtoRes findByUser(String id) throws Exception {
		GetProfileSosmedByUserDtoRes getByUser = new GetProfileSosmedByUserDtoRes();

		List<ProfileSosmed> profileSosmeds = profileSosmedDao.findByUser(id);
		List<GetProfileSosmedByUserDtoDataRes> profileList = new ArrayList<>();

		for (int i = 0; i < profileSosmeds.size(); i++) {
			ProfileSosmed profileSosmed = profileSosmeds.get(i);
			GetProfileSosmedByUserDtoDataRes data = new GetProfileSosmedByUserDtoDataRes();

			data.setId(profileSosmed.getId());
			data.setAccountName(profileSosmed.getAccountName());
			data.setProfileId(profileSosmed.getProfileId().getId());
			data.setProfileName(profileSosmed.getProfileId().getProfileName());
			data.setSocialMediaid(profileSosmed.getSocialMediaId().getId());
			data.setSocialMediaName(profileSosmed.getSocialMediaId().getSocialMediaName());
			data.setVersion(profileSosmed.getVersion());
			data.setIsActive(profileSosmed.getIsActive());

			profileList.add(data);
		}

		getByUser.setData(profileList);
		getByUser.setMsg(null);

		return getByUser;
	}
}
