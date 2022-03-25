package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.PriceListEvent;

public interface PriceListEventDao {
	public List<PriceListEvent> findAll(int startPage, int maxPage) throws Exception;
	public PriceListEvent findById(String id) throws Exception;
	public PriceListEvent save(PriceListEvent data) throws Exception;
	public boolean deleteById(String id) throws Exception;
}
