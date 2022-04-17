package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.dto.threadlike.GetThreadLikeByThreadDtoRes;
import com.lawencon.community.model.ThreadLike;

public interface ThreadLikeDao {
	List<ThreadLike> findAll() throws Exception;
	ThreadLike findById(String id) throws Exception;
	ThreadLike save(ThreadLike data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<ThreadLike> findByUser(String id) throws Exception;
	GetThreadLikeByThreadDtoRes countByThread(String id) throws Exception;
	GetThreadLikeByThreadDtoRes countByThreadAndUser(String id, String threadId) throws Exception;
	List<?> validateDelete(String id) throws Exception;
}
