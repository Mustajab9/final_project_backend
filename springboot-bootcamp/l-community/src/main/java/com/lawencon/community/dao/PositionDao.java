package com.lawencon.community.dao;

import com.lawencon.community.model.Position;
import com.lawencon.model.SearchQuery;

public interface PositionDao {
	public SearchQuery<Position> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public Position findById(String id) throws Exception;
	public Position save(Position data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Long countAll() throws Exception;
}
