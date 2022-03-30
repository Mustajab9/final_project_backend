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

@Service
public class SocialMediaServiceImpl extends BaseService implements SocialMediaService {
	private SocialMediaDao socialMediaDao;

	@Autowired
	public SocialMediaServiceImpl(SocialMediaDao socialMediaDao) {
		this.socialMediaDao = socialMediaDao;
	}
	
	@Override
	public GetAllSocialMediaDtoRes findAll(int startPage, int maxPage) throws Exception {
		GetAllSocialMediaDtoRes getAll = new GetAllSocialMediaDtoRes();

		List<SocialMedia> socialMedias = socialMediaDao.findAll(startPage, maxPage);
		List<GetAllSocialMediaDtoDataRes> listSocialMedia = new ArrayList<>();

		for (int i = 0; i < socialMedias.size(); i++) {
			SocialMedia socialMedia = socialMedias.get(i);
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
}
