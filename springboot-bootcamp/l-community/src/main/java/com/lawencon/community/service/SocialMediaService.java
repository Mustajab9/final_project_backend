package com.lawencon.community.service;

import com.lawencon.community.dto.socialmedia.DeleteBySocialMediaIdDtoRes;
import com.lawencon.community.dto.socialmedia.GetAllSocialMediaDtoRes;
import com.lawencon.community.dto.socialmedia.GetBySocialMediaIdDtoRes;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoReq;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoRes;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoReq;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoRes;

public interface SocialMediaService {
	public GetAllSocialMediaDtoRes findAll(int startPage, int maxPage) throws Exception;
	public GetBySocialMediaIdDtoRes findById(String id) throws Exception;
	public InsertSocialMediaDtoRes insert(InsertSocialMediaDtoReq data) throws Exception;
	public UpdateSocialMediaDtoRes update(UpdateSocialMediaDtoReq data) throws Exception;
	public DeleteBySocialMediaIdDtoRes deleteById(String id) throws Exception;
}
