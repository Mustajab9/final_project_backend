package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.CategoryDao;
import com.lawencon.community.model.Category;
import com.lawencon.model.SearchQuery;

@Repository
public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao {

	@Override
	public SearchQuery<Category> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Category> sq = new SearchQuery<>();
		List<Category> data = null;

		if (startPage == null || maxPage == null) {
			data = getAll();
			sq.setData(data);
		} else {
			if (query == null) {
				data = getAll(startPage, maxPage);
				int count = countAll().intValue();

				sq.setData(data);
				sq.setCount(count);
			} else {
				return super.getAll(query, startPage, maxPage, "categoryName", "categoryCode");
			}
		}

		return sq;
	}

	@Override
	public Category findById(String id) throws Exception {
		return super.getById(id);
	}

	@Override
	public Category save(Category entity) throws Exception {
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
	public Category findByCode(String code) throws Exception {
		List<Category> types = createQuery("FROM Category WHERE categoryCode = ?1", Category.class)
				.setParameter(1, code).getResultList();

		return resultCheck(types);
	}
}
