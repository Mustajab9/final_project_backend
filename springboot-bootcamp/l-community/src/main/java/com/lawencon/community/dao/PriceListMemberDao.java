package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.PriceListMember;

public interface PriceListMemberDao {
	public List<PriceListMember> findAll(int startPage, int maxPage) throws Exception;
	public PriceListMember findById(String id) throws Exception;
	public PriceListMember save(PriceListMember data) throws Exception;
	public boolean deleteById(String id) throws Exception;
}
