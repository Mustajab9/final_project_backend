package com.lawencon.community.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.SocialMediaDao;
import com.lawencon.community.model.SocialMedia;
import com.lawencon.model.SearchQuery;

@Repository
public class SocialMediaDaoImpl extends BaseDao<SocialMedia> implements SocialMediaDao {

	@Override
	public SearchQuery<SocialMedia> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<SocialMedia> sq = new SearchQuery<>();
		List<SocialMedia> data = null;
		
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
				return super.getAll(query, startPage, maxPage, "socialMediaName", "socialMediaCode", "socialMediaIcon");
			}
		}
		
		return sq;
	}
	
	@Override
	public SocialMedia findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public SocialMedia save(SocialMedia entity) throws Exception {
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
	public SocialMedia findByCode(String code) throws Exception {
		List<SocialMedia> types = createQuery("FROM SocialMedia WHERE socialMediaCode = ?1", SocialMedia.class)
											.setParameter(1, code)
											.getResultList();
		
		return resultCheck(types);	
	}
	
	@Override
	public List<?> validateDelete(String id) throws Exception {
		String sql = "SELECT s.id FROM social_media AS s WHERE s.id = ?1";
		
		List<?> listObj = createNativeQuery(sql).setParameter(1, id).setMaxResults(1).getResultList();
		List<String> result = new ArrayList<>();
		
		listObj.forEach(val -> {
			Object obj = (Object) val;
			result.add(obj != null ? obj.toString() : null);
		});
		
		return result;
	}
}
