package com.lawencon.community.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.RegencyDao;
import com.lawencon.community.model.Province;
import com.lawencon.community.model.Regency;

@Repository
public class RegencyDaoImpl extends BaseDao<Regency> implements RegencyDao {
	
	@Override
	public List<Regency> findAll(int startPage, int maxPage) throws Exception {
		return super.getAll(startPage, maxPage);
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
	public List<Regency> findByProvince(String code) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT r.id, r.regency_code, r.regency_name, p.id, p.province_code, p.province_name, r.version, r.is_active");
		builder.append(" FROM regencies r");
		builder.append(" JOIN provinces p ON p.id = r.province_id");
		builder.append(" WHERE p.province_code = :code");
		
		List<?> results = createNativeQuery(builder.toString())
				.setParameter("code", code)
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
