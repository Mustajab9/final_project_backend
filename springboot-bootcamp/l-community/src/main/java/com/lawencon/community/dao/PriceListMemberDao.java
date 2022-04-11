package com.lawencon.community.dao;

import com.lawencon.community.model.PriceListMember;
import com.lawencon.model.SearchQuery;

public interface PriceListMemberDao {
	SearchQuery<PriceListMember> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	PriceListMember findById(String id) throws Exception;
	PriceListMember save(PriceListMember data) throws Exception;
	boolean deleteById(String id) throws Exception;
	Long countAll() throws Exception;
}
