package com.lawencon.community.dao.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PriceListEventDao;
import com.lawencon.community.model.PriceListEvent;
import com.lawencon.model.SearchQuery;

@Repository
public class PriceListEventDaoImpl extends BaseDao<PriceListEvent> implements PriceListEventDao {
	
	@Override
	public SearchQuery<PriceListEvent> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PriceListEvent> sq = new SearchQuery<>();
		List<PriceListEvent> data = null;
		
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
				return super.getAll(query, startPage, maxPage, "priceName", "priceCode");
			}
		}
		
		return sq;
	}
	
	@Override
	public PriceListEvent findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public PriceListEvent save(PriceListEvent entity) throws Exception {
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
	public PriceListEvent findByCode(String code) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT id, price_code, price_name, price_nominal, created_by, created_at, version, is_active");
		builder.append(" FROM price_list_event");
		builder.append(" WHERE price_code = :code");
		
		PriceListEvent data = null;
		try {
			Object result = createNativeQuery(builder.toString())
								.setParameter("code", code)
								.getSingleResult();
			
			Object[] obj = (Object[]) result;
			data = new PriceListEvent();
			
			data.setId(obj[0].toString());
			data.setPriceCode(obj[1].toString());
			data.setPriceName(obj[2].toString());
			data.setPriceNominal(BigInteger.valueOf(((Number) obj[3]).longValue()));			
			data.setCreatedBy(obj[4].toString());
			data.setCreatedAt(((Timestamp)obj[5]).toLocalDateTime());
			data.setVersion(Integer.valueOf(obj[6].toString()));
			data.setIsActive(Boolean.valueOf(obj[7].toString()));
			
		}catch(NoResultException | NonUniqueResultException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
