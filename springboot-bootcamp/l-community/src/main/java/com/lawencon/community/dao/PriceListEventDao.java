package com.lawencon.community.dao;

import com.lawencon.community.model.PriceListEvent;
import com.lawencon.model.SearchQuery;

public interface PriceListEventDao {
	public SearchQuery<PriceListEvent> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public PriceListEvent findById(String id) throws Exception;
	public PriceListEvent save(PriceListEvent data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Long countAll() throws Exception;
}
