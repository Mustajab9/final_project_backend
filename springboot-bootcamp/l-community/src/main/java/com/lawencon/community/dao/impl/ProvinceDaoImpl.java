package com.lawencon.community.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.ProvinceDao;
import com.lawencon.community.model.Province;
import com.lawencon.model.SearchQuery;

@Repository
public class ProvinceDaoImpl extends BaseDao<Province> implements ProvinceDao {
	
	@Override
	public SearchQuery<Province> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Province> sq = new SearchQuery<>();
		List<Province> data = null;
		
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
				return super.getAll(query, startPage, maxPage, "provinceName", "provinceCode");
			}
		}
		
		return sq;
	}
	
	@Override
	public Province findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public Province save(Province entity) throws Exception {
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
	public Province findByCode(String code) throws Exception {
		List<Province> types = createQuery("FROM Province WHERE provinceCode = ?1", Province.class)
											.setParameter(1, code)
											.getResultList();
		
		return resultCheck(types);	
	}
	
	@Override
	public List<?> validateDelete(String id) throws Exception {
		String sql = "SELECT p.id FORM provinces AS p WHERE p.id = ?1";
		
		List<?> listObj = createNativeQuery(sql).setParameter(1, id).setMaxResults(1).getResultList();
		List<String> result = new ArrayList<>();
		
		listObj.forEach(val -> {
			Object obj = (Object) val;
			result.add(obj != null ? obj.toString() : null);
		});
		
		return result;
	}
}
