package com.lawencon.community.dao;

import com.lawencon.community.model.PriceListMember;
import com.lawencon.model.SearchQuery;

public interface PriceListMemberDao {
	public SearchQuery<PriceListMember> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public PriceListMember findById(String id) throws Exception;
	public PriceListMember save(PriceListMember data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Long countAll() throws Exception;
}
