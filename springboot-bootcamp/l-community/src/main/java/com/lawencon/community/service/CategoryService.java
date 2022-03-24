package com.lawencon.community.service;

import com.lawencon.community.dto.category.DeleteByCategoryIdDtoRes;
import com.lawencon.community.dto.category.GetAllCategoryDtoRes;
import com.lawencon.community.dto.category.GetByCategoryIdDtoRes;
import com.lawencon.community.dto.category.InsertCategoryDtoReq;
import com.lawencon.community.dto.category.InsertCategoryDtoRes;
import com.lawencon.community.dto.category.UpdateCategoryDtoReq;
import com.lawencon.community.dto.category.UpdateCategoryDtoRes;

public interface CategoryService {
	public GetAllCategoryDtoRes getAll() throws Exception;
	public GetByCategoryIdDtoRes getById(String id) throws Exception;
	public InsertCategoryDtoRes insert(InsertCategoryDtoReq data) throws Exception;
	public UpdateCategoryDtoRes update(UpdateCategoryDtoReq data) throws Exception;
	public DeleteByCategoryIdDtoRes deleteById(String id) throws Exception;
}
