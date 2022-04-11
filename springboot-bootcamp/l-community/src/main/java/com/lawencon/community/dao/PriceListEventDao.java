package com.lawencon.community.dao;

import com.lawencon.community.model.PriceListEvent;
import com.lawencon.model.SearchQuery;

public interface PriceListEventDao {
	SearchQuery<PriceListEvent> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	PriceListEvent findById(String id) throws Exception;
	PriceListEvent save(PriceListEvent data) throws Exception;
	boolean deleteById(String id) throws Exception;
	Long countAll() throws Exception;
	PriceListEvent findByCode(String code) throws Exception;
}
