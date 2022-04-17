package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.SocialMediaDao;
import com.lawencon.community.dto.socialmedia.DeleteBySocialMediaIdDtoRes;
import com.lawencon.community.dto.socialmedia.GetAllSocialMediaDtoDataRes;
import com.lawencon.community.dto.socialmedia.GetAllSocialMediaDtoRes;
import com.lawencon.community.dto.socialmedia.GetBySocialMediaIdDtoDataRes;
import com.lawencon.community.dto.socialmedia.GetBySocialMediaIdDtoRes;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoDataRes;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoReq;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoRes;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoDataRes;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoReq;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoRes;
import com.lawencon.community.model.SocialMedia;
import com.lawencon.community.service.SocialMediaService;
import com.lawencon.model.SearchQuery;

@Service
public class SocialMediaServiceImpl extends BaseService implements SocialMediaService {
	private SocialMediaDao socialMediaDao;

	@Autowired
	public SocialMediaServiceImpl(SocialMediaDao socialMediaDao) {
		this.socialMediaDao = socialMediaDao;
	}
	
	@Override
	public GetAllSocialMediaDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllSocialMediaDtoRes getAll = new GetAllSocialMediaDtoRes();
		
		SearchQuery<SocialMedia> socialMedias = socialMediaDao.findAll(query, startPage, maxPage);
		List<GetAllSocialMediaDtoDataRes> listSocialMedia = new ArrayList<>();

		for (int i = 0; i < socialMedias.getData().size(); i++) {
			SocialMedia socialMedia = socialMedias.getData().get(i);
			GetAllSocialMediaDtoDataRes data = new GetAllSocialMediaDtoDataRes();

			data.setId(socialMedia.getId());
			data.setSocialMediaCode(socialMedia.getSocialMediaCode());
			data.setSocialMediaName(socialMedia.getSocialMediaName());
			data.setVersion(socialMedia.getVersion());
			data.setIsActive(socialMedia.getIsActive());

			listSocialMedia.add(data);
		}

		getAll.setData(listSocialMedia);
		getAll.setMsg(null);
		getAll.setTotal(socialMedias.getCount());

		return getAll;
	}
	
	@Override
	public GetBySocialMediaIdDtoRes findById(String id) throws Exception {
		GetBySocialMediaIdDtoRes getById = new GetBySocialMediaIdDtoRes();

		SocialMedia socialMedia = socialMediaDao.findById(id);
		GetBySocialMediaIdDtoDataRes data = new GetBySocialMediaIdDtoDataRes();

		data.setId(socialMedia.getId());
		data.setSocialMediaCode(socialMedia.getSocialMediaCode());
		data.setSocialMediaName(socialMedia.getSocialMediaName());
		data.setVersion(socialMedia.getVersion());
		data.setIsActive(socialMedia.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertSocialMediaDtoRes insert(InsertSocialMediaDtoReq data) throws Exception {
		InsertSocialMediaDtoRes insert = new InsertSocialMediaDtoRes();

		try {
			validateInsert(data);
			
			SocialMedia socialMedia = new SocialMedia();

			socialMedia.setSocialMediaCode(data.getSocialMediaCode());
			socialMedia.setSocialMediaName(data.getSocialMediaName());
			socialMedia.setCreatedBy(getId());

			begin();
			SocialMedia socialMediaInsert = socialMediaDao.save(socialMedia);
			commit();

			InsertSocialMediaDtoDataRes dataDto = new InsertSocialMediaDtoDataRes();
			dataDto.setId(socialMediaInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(CommonConstant.ACTION_ADD.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Social Media" + CommonConstant.HAS_BEEN_ADDED.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdateSocialMediaDtoRes update(UpdateSocialMediaDtoReq data) throws Exception {
		UpdateSocialMediaDtoRes update = new UpdateSocialMediaDtoRes();

		try {
			validateUpdate(data);
			
			if (data.getVersion() != null) {
				SocialMedia socialMedia = socialMediaDao.findById(data.getId());
				
				socialMedia.setSocialMediaName(data.getSocialMediaName());
				socialMedia.setVersion(data.getVersion());
				socialMedia.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					socialMedia.setIsActive(data.getIsActive());
				}

				begin();
				SocialMedia socialMediaUpdate = socialMediaDao.save(socialMedia);
				commit();

				UpdateSocialMediaDtoDataRes dataDto = new UpdateSocialMediaDtoDataRes();
				dataDto.setVersion(socialMediaUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Social Media " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteBySocialMediaIdDtoRes deleteById(String id) throws Exception {
		DeleteBySocialMediaIdDtoRes deleteById = new DeleteBySocialMediaIdDtoRes();

		try {
			validateDelete(id);
			
			begin();
			boolean isDeleted = socialMediaDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Social Media " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	private void validateInsert(InsertSocialMediaDtoReq data) throws Exception {
		if (data.getSocialMediaCode() == null || data.getSocialMediaCode().trim().equals("")) {
			throw new Exception("Social Media Code Cant Null");
		} else {
			SocialMedia socialMedia = socialMediaDao.findByCode(data.getSocialMediaCode());
			if (socialMedia != null) {
				throw new Exception("Social Media Code Already Exsist");
			}
			if (data.getSocialMediaName() == null || data.getSocialMediaName().trim().equals("")) {
				throw new Exception("Social Media Name Cant Null");
			}
		}
	}

	private void validateUpdate(UpdateSocialMediaDtoReq data) throws Exception {
		if (data.getId() == null || data.getId().trim().equals("")) {
			throw new Exception("Social Media Id Cant Null");
		} else {
			SocialMedia socialMedia = socialMediaDao.findById(data.getId());
			if (data.getSocialMediaName() == null || data.getSocialMediaName().trim().equals("")) {
				throw new Exception("Social Media Name Cant Null");
			}
			if (socialMedia.getVersion() != data.getVersion()) {
				throw new Exception("Social Media You Update Already Update By Someone");
			}
		}
	}

	private void validateDelete(String id) throws Exception {
		SocialMedia socialMedia = socialMediaDao.findById(id);
		
		if(socialMedia == null) {
			throw new Exception("Social Media Id Not Exsist");
		}
	}
}
