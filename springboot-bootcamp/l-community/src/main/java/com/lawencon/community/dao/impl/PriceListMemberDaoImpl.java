package com.lawencon.community.dao.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PriceListMemberDao;
import com.lawencon.community.model.PriceListMember;
import com.lawencon.model.SearchQuery;

@Repository
public class PriceListMemberDaoImpl extends BaseDao<PriceListMember> implements PriceListMemberDao {

	@Override
	public SearchQuery<PriceListMember> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PriceListMember> sq = new SearchQuery<>();
		List<PriceListMember> data = null;
		
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
				return super.getAll(query, startPage, maxPage, "priceCode");
			}
		}
		
		return sq;
	}
	
	@Override
	public PriceListMember findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public PriceListMember save(PriceListMember entity) throws Exception {
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
	public PriceListMember findByCode(String code) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT id, price_code, price_nominal, duration, created_by, created_at, version, is_active");
		builder.append(" FROM price_list_member");
		builder.append(" WHERE price_code = :code");
		
		PriceListMember data = null;
		try {
			Object result = createNativeQuery(builder.toString())
								.setParameter("code", code)
								.getSingleResult();
			
			Object[] obj = (Object[]) result;
			data = new PriceListMember();
			
			data.setId(obj[0].toString());
			data.setPriceCode(obj[1].toString());
			data.setPriceNominal(BigInteger.valueOf(((Number) obj[2]).longValue()));
			data.setDuration(Integer.valueOf(obj[3].toString()));
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
