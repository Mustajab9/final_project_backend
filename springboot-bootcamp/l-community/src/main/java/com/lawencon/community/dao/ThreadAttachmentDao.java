package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.ThreadAttachment;

public interface ThreadAttachmentDao {
	public List<ThreadAttachment> findAll() throws Exception;
	public ThreadAttachment findById(String id) throws Exception;
	public ThreadAttachment save(ThreadAttachment data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<ThreadAttachment> findByThread(String id);
}
