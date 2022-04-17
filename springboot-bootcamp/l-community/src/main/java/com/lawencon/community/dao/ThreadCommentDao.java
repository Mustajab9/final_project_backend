package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.dto.threadcomment.GetCountCommentByThreadDtoRes;
import com.lawencon.community.model.ThreadComment;

public interface ThreadCommentDao {
	List<ThreadComment> findAll() throws Exception;
	ThreadComment findById(String id) throws Exception;
	ThreadComment save(ThreadComment data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<ThreadComment> findByThread(String id) throws Exception;
	GetCountCommentByThreadDtoRes countByThread(String id) throws Exception;
	List<?> validateDelete(String id) throws Exception;
}
