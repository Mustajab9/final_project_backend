package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.CategoryDao;
import com.lawencon.community.dto.category.DeleteByCategoryIdDtoRes;
import com.lawencon.community.dto.category.GetAllCategoryDtoDataRes;
import com.lawencon.community.dto.category.GetAllCategoryDtoRes;
import com.lawencon.community.dto.category.GetByCategoryIdDtoDataRes;
import com.lawencon.community.dto.category.GetByCategoryIdDtoRes;
import com.lawencon.community.dto.category.InsertCategoryDtoDataRes;
import com.lawencon.community.dto.category.InsertCategoryDtoReq;
import com.lawencon.community.dto.category.InsertCategoryDtoRes;
import com.lawencon.community.dto.category.UpdateCategoryDtoDataRes;
import com.lawencon.community.dto.category.UpdateCategoryDtoReq;
import com.lawencon.community.dto.category.UpdateCategoryDtoRes;
import com.lawencon.community.model.Category;
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
		GetAllCategoryDtoRes getAll = new GetAllCategoryDtoRes();

		List<Category> categories = categoryDao.findAll(startPage, maxPage);
		List<GetAllCategoryDtoDataRes> listCategory = new ArrayList<>();

		for (int i = 0; i < categories.size(); i++) {
			Category category = categories.get(i);
			GetAllCategoryDtoDataRes data = new GetAllCategoryDtoDataRes();
			
			data.setId(category.getId());
			data.setCategoryCode(category.getCategoryCode());
			data.setCategoryName(category.getCategoryName());
			data.setVersion(category.getVersion());
			data.setIsActive(category.getIsActive());

			listCategory.add(data);
		}

		getAll.setData(listCategory);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByCategoryIdDtoRes findById(String id) throws Exception {
		GetByCategoryIdDtoRes getById = new GetByCategoryIdDtoRes();

		Category category = categoryDao.findById(id);
		GetByCategoryIdDtoDataRes data = new GetByCategoryIdDtoDataRes();

		data.setId(category.getId());
		data.setCategoryCode(category.getCategoryCode());
		data.setCategoryName(category.getCategoryName());
		data.setVersion(category.getVersion());
		data.setIsActive(category.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertCategoryDtoRes insert(InsertCategoryDtoReq data) throws Exception {
		InsertCategoryDtoRes insert = new InsertCategoryDtoRes();

		try {
			Category category = new Category();
			category.setCategoryCode(getAlphaNumericString(5));
			category.setCategoryName(data.getCategoryName());
			category.setCreatedBy(getId());

			begin();
			Category categoryInsert = categoryDao.save(category);
			commit();

			InsertCategoryDtoDataRes dataDto = new InsertCategoryDtoDataRes();
			dataDto.setId(categoryInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(CommonConstant.ACTION_ADD.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Category " + CommonConstant.HAS_BEEN_ADDED.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdateCategoryDtoRes update(UpdateCategoryDtoReq data) throws Exception {
		UpdateCategoryDtoRes update = new UpdateCategoryDtoRes();

		try {
			if (data.getVersion() != null) {
				Category category = categoryDao.findById(data.getId());
				category.setCategoryName(data.getCategoryName());
				category.setUpdatedBy(getId());
				category.setVersion(data.getVersion());

				if (data.getIsActive() != null) {
					category.setIsActive(data.getIsActive());
				}

				begin();
				Category categoryUpdate = categoryDao.save(category);
				commit();

				UpdateCategoryDtoDataRes dataDto = new UpdateCategoryDtoDataRes();
				dataDto.setVersion(categoryUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Category " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
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
		DeleteByCategoryIdDtoRes deleteById = new DeleteByCategoryIdDtoRes();

		try {
			begin();
			boolean isDeleted = categoryDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Category " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
}
