package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.ThreadType;

public interface ThreadTypeDao {
	public List<ThreadType> findAll(int startPage, int maxPage) throws Exception;
	public ThreadType findById(String id) throws Exception;
	public ThreadType save(ThreadType data) throws Exception;
	public boolean deleteById(String id) throws Exception;
}
