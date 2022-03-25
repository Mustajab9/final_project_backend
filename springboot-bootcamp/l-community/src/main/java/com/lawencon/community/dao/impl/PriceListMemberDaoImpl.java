package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PriceListMemberDao;
import com.lawencon.community.model.PriceListMember;

@Repository
public class PriceListMemberDaoImpl extends BaseDao<PriceListMember> implements PriceListMemberDao {

	@Override
	public List<PriceListMember> findAll(int startPage, int maxPage) throws Exception {
		return super.getAll(startPage, maxPage);
	}
	
	@Override
	public PriceListMember findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public PriceListMember save(PriceListMember entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
}
