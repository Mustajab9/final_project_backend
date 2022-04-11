package com.lawencon.community.service;

import com.lawencon.community.dto.socialmedia.DeleteBySocialMediaIdDtoRes;
import com.lawencon.community.dto.socialmedia.GetAllSocialMediaDtoRes;
import com.lawencon.community.dto.socialmedia.GetBySocialMediaIdDtoRes;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoReq;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoRes;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoReq;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoRes;

public interface SocialMediaService {
	GetAllSocialMediaDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetBySocialMediaIdDtoRes findById(String id) throws Exception;
	InsertSocialMediaDtoRes insert(InsertSocialMediaDtoReq data) throws Exception;
	UpdateSocialMediaDtoRes update(UpdateSocialMediaDtoReq data) throws Exception;
	DeleteBySocialMediaIdDtoRes deleteById(String id) throws Exception;
}
