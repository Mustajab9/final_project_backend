package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Position;

public interface PositionDao {
	public List<Position> findAll() throws Exception;
	public Position findById(String id) throws Exception;
	public Position save(Position data) throws Exception;
	public boolean deleteById(String id) throws Exception;
}
