package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.ThreadComment;

public interface ThreadCommentDao {
	public List<ThreadComment> getByThread(String id) throws Exception;
}
