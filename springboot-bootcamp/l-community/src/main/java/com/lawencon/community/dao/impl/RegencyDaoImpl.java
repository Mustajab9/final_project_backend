package com.lawencon.community.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.RegencyDao;
import com.lawencon.community.model.Province;
import com.lawencon.community.model.Regency;
import com.lawencon.model.SearchQuery;

@Repository
public class RegencyDaoImpl extends BaseDao<Regency> implements RegencyDao {
	
	@Override
	public SearchQuery<Regency> findAll(String query, Integer startPage, Integer maxPage) throws Exception {		
		SearchQuery<Regency> sq = new SearchQuery<>();
		List<Regency> data = null;
		
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
				return super.getAll(query, startPage, maxPage, "regencyName", "regencyCode", "provinceId.provinceName", "provinceId.provinceCode");
			}
		}
		
		return sq;
	}
	
	@Override
	public Regency findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public Regency save(Regency entity) throws Exception {
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
	public List<Regency> findByProvince(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT r.id AS regency_id, r.regency_code, r.regency_name, p.id AS province_id, p.province_code, p.province_name, r.version, r.is_active");
		builder.append(" FROM regencies r");
		builder.append(" JOIN provinces p ON p.id = r.province_id");
		builder.append(" WHERE p.id = :id");
		
		List<?> results = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getResultList();
		List<Regency> listResult = new ArrayList<>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			Regency data = new Regency();
			
			data.setId(obj[0].toString());
			data.setRegencyCode(obj[1].toString());
			data.setRegencyName(obj[2].toString());
			
			Province province = new Province();
			province.setId(obj[3].toString());
			province.setProvinceCode(obj[4].toString());
			province.setProvinceName(obj[5].toString());
			data.setProvinceId(province);
			
			data.setVersion(Integer.valueOf(obj[6].toString()));
			data.setIsActive(Boolean.valueOf(obj[7].toString()));
			
			listResult.add(data);
		});
		
		return listResult;
	}
}
