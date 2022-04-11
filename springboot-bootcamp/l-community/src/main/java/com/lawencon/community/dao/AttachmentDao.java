package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Attachment;

public interface AttachmentDao {
	List<Attachment> findAll() throws Exception;
	Attachment findById(String id) throws Exception;
	Attachment save(Attachment data) throws Exception;
	boolean deleteById(String id) throws Exception;
}
