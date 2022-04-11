package com.lawencon.community.service;

import com.lawencon.community.dto.category.DeleteByCategoryIdDtoRes;
import com.lawencon.community.dto.category.GetAllCategoryDtoRes;
import com.lawencon.community.dto.category.GetByCategoryIdDtoRes;
import com.lawencon.community.dto.category.InsertCategoryDtoReq;
import com.lawencon.community.dto.category.InsertCategoryDtoRes;
import com.lawencon.community.dto.category.UpdateCategoryDtoReq;
import com.lawencon.community.dto.category.UpdateCategoryDtoRes;

public interface CategoryService {
	GetAllCategoryDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetByCategoryIdDtoRes findById(String id) throws Exception;
	InsertCategoryDtoRes insert(InsertCategoryDtoReq data) throws Exception;
	UpdateCategoryDtoRes update(UpdateCategoryDtoReq data) throws Exception;
	DeleteByCategoryIdDtoRes deleteById(String id) throws Exception;
}
