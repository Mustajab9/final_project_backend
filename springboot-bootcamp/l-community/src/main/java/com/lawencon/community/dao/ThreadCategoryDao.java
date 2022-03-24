package com.lawencon.community.dao;

import java.util.List;

public interface ThreadCategoryDao {
	public List<ThreadCategoryDao> getByThread(String id) throws Exception;
}
