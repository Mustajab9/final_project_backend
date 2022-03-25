package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.AttachmentDao;
import com.lawencon.community.model.Attachment;

@Repository
public class AttachmentDaoImpl extends BaseDao<Attachment> implements AttachmentDao {
	
	@Override
	public List<Attachment> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public Attachment findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public Attachment save(Attachment entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
}
