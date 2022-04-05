package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.PositionDao;
import com.lawencon.community.dto.position.DeleteByPositionIdDtoRes;
import com.lawencon.community.dto.position.GetAllPositionDtoDataRes;
import com.lawencon.community.dto.position.GetAllPositionDtoRes;
import com.lawencon.community.dto.position.GetByPositionIdDtoDataRes;
import com.lawencon.community.dto.position.GetByPositionIdDtoRes;
import com.lawencon.community.dto.position.InsertPositionDtoDataRes;
import com.lawencon.community.dto.position.InsertPositionDtoReq;
import com.lawencon.community.dto.position.InsertPositionDtoRes;
import com.lawencon.community.dto.position.UpdatePositionDtoDataRes;
import com.lawencon.community.dto.position.UpdatePositionDtoReq;
import com.lawencon.community.dto.position.UpdatePositionDtoRes;
import com.lawencon.community.model.Position;
import com.lawencon.community.service.PositionService;
import com.lawencon.model.SearchQuery;

@Service
public class PositionServiceImpl extends BaseService implements PositionService {
	private PositionDao positionDao;

	@Autowired
	public PositionServiceImpl(PositionDao positionDao) {
		this.positionDao = positionDao;
	}
	
	@Override
	public GetAllPositionDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllPositionDtoRes getAll = new GetAllPositionDtoRes();

		SearchQuery<Position> positions = positionDao.findAll(query, startPage, maxPage);
		List<GetAllPositionDtoDataRes> positionList = new ArrayList<>();

		for (int i = 0; i < positions.getData().size(); i++) {
			Position position = positions.getData().get(i);
			GetAllPositionDtoDataRes data = new GetAllPositionDtoDataRes();

			data.setId(position.getId());
			data.setPositionName(position.getPositionName());
			data.setPositionCode(position.getPositionCode());
			data.setVersion(position.getVersion());
			data.setIsActive(position.getIsActive());

			positionList.add(data);
		}

		getAll.setData(positionList);
		getAll.setMsg(null);
		getAll.setTotal(positions.getCount());

		return getAll;
	}
	
	@Override
	public GetByPositionIdDtoRes findById(String id) throws Exception {
		GetByPositionIdDtoRes getById = new GetByPositionIdDtoRes();

		Position position = positionDao.findById(id);
		GetByPositionIdDtoDataRes data = new GetByPositionIdDtoDataRes();

		data.setId(position.getId());
		data.setPositionName(position.getPositionName());
		data.setPositionCode(position.getPositionCode());
		data.setVersion(position.getVersion());
		data.setIsActive(position.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertPositionDtoRes insert(InsertPositionDtoReq data) throws Exception {
		InsertPositionDtoRes insert = new InsertPositionDtoRes();

		try {
			Position position = new Position();
			position.setPositionName(data.getPositionName());
			position.setPositionCode(data.getPositionCode());
			position.setCreatedBy(getId());

			begin();
			Position positionInsert = positionDao.save(position);
			commit();

			InsertPositionDtoDataRes dataDto = new InsertPositionDtoDataRes();
			dataDto.setId(positionInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(CommonConstant.ACTION_ADD.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Position " + CommonConstant.HAS_BEEN_ADDED.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdatePositionDtoRes update(UpdatePositionDtoReq data) throws Exception {
		UpdatePositionDtoRes update = new UpdatePositionDtoRes();

		try {
			if (data.getVersion() != null) {
				Position position = positionDao.findById(data.getId());

				position.setPositionName(data.getPositionName());
				position.setVersion(data.getVersion());
				position.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					position.setIsActive(data.getIsActive());
				}

				begin();
				Position updatePosition = positionDao.save(position);
				commit();

				UpdatePositionDtoDataRes dataDto = new UpdatePositionDtoDataRes();
				dataDto.setVersion(updatePosition.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Position " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByPositionIdDtoRes deleteById(String id) throws Exception {
		DeleteByPositionIdDtoRes deleteById = new DeleteByPositionIdDtoRes();

		try {
			begin();
			boolean isDeleted = positionDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Position " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
}
