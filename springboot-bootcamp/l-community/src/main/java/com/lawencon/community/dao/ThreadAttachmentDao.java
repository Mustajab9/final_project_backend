package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.ThreadAttachment;

public interface ThreadAttachmentDao {
	public List<ThreadAttachment> getByThread(String id);
}
