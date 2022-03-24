package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Attachment;

public interface AttachmentDao {
	public List<Attachment> findAll() throws Exception;
	public Attachment findById(String id) throws Exception;
	public Attachment save(Attachment data) throws Exception;
	public boolean deleteById(String id) throws Exception;
}
