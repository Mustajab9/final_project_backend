package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.ThreadLike;

public interface ThreadLikeDao {
	public List<ThreadLike> getByUser(String id) throws Exception;
}
