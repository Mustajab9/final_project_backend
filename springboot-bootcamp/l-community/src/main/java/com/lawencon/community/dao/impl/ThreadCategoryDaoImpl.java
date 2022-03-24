package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.dao.ThreadCategoryDao;
import com.lawencon.community.model.ThreadCategory;

@Repository
public class ThreadCategoryDaoImpl extends BaseDaoImpl<ThreadCategory> implements ThreadCategoryDao {
	@Override
	public List<ThreadCategoryDao> getByThread(String id) throws Exception {
		
		return null;
	}
}
