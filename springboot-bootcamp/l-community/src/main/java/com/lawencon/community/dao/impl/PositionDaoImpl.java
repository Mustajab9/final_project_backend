package com.lawencon.community.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PositionDao;
import com.lawencon.community.model.Position;
import com.lawencon.model.SearchQuery;

@Repository
public class PositionDaoImpl extends BaseDao<Position> implements PositionDao {
	
	@Override
	public SearchQuery<Position> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Position> sq = new SearchQuery<>();
		List<Position> data = null;
		
		if(startPage == null || maxPage == null) {
			data = getAll();
			sq.setData(data);
		}else {
			if(query == null) {
				data = getAll(startPage, maxPage);
				int count = countAll().intValue();
				
				sq.setData(data);
				sq.setCount(count);
			}else {
				return super.getAll(query, startPage, maxPage, "positionName", "positionCode");
			}
		}
		
		return sq;
	}
	
	@Override
	public Position findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public Position save(Position entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public Long countAll() {
		return super.countAll();
	}

	@Override
	public Position findByCode(String code) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT id, position_name, position_code, created_by, created_at, version, is_active");
		builder.append(" FROM positions");
		builder.append(" WHERE position_code = :code");
		
		Position data = null;
		try {
			Object result = createNativeQuery(builder.toString())
								.setParameter("code", code)
								.getSingleResult();
			
			Object[] obj = (Object[]) result;
			data = new Position();
			
			data.setId(obj[0].toString());
			data.setPositionName(obj[1].toString());
			data.setPositionCode(obj[2].toString());
			if(obj[3] != null) {				
				data.setCreatedBy(obj[3].toString());
			}
			data.setCreatedAt(((Timestamp)obj[4]).toLocalDateTime());
			data.setVersion(Integer.valueOf(obj[5].toString()));
			data.setIsActive(Boolean.valueOf(obj[6].toString()));
			
		}catch(NoResultException | NonUniqueResultException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
