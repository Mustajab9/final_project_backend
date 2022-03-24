package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.ThreadComment;

public interface ThreadCommentDao {
	public List<ThreadComment> findAll() throws Exception;
	public ThreadComment findById(String id) throws Exception;
	public ThreadComment save(ThreadComment data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<ThreadComment> findByThread(String id) throws Exception;
}
