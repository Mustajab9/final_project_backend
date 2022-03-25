package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.CategoryDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.CategoryService;

@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {
	private CategoryDao categoryDao;

	@Autowired
	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
}
