package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.dto.threadlike.GetThreadLikeByThreadDtoRes;
import com.lawencon.community.model.ThreadLike;

public interface ThreadLikeDao {
	public List<ThreadLike> findAll() throws Exception;
	public ThreadLike findById(String id) throws Exception;
	public ThreadLike save(ThreadLike data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<ThreadLike> findByUser(String id) throws Exception;
	public GetThreadLikeByThreadDtoRes countByThread(String id) throws Exception;
	public GetThreadLikeByThreadDtoRes countByThreadAndUser(String id, String threadId) throws Exception;
}
