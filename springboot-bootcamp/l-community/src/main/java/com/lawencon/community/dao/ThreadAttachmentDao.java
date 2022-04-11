package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.ThreadAttachment;

public interface ThreadAttachmentDao {
	List<ThreadAttachment> findAll() throws Exception;
	ThreadAttachment findById(String id) throws Exception;
	ThreadAttachment save(ThreadAttachment data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<ThreadAttachment> findByThread(String id);
}
