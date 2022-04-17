package com.lawencon.community.dao;

import com.lawencon.community.model.Position;
import com.lawencon.model.SearchQuery;

public interface PositionDao {
	SearchQuery<Position> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	Position findById(String id) throws Exception;
	Position save(Position data) throws Exception;
	Position findByCode(String code) throws Exception;
	boolean deleteById(String id) throws Exception;
	Long countAll() throws Exception;
}
