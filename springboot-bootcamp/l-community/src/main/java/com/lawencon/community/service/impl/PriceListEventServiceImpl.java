package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.PriceListEventDao;
import com.lawencon.community.dto.pricelistevent.DeleteByPriceListEventIdDtoRes;
import com.lawencon.community.dto.pricelistevent.GetAllPriceListEventDtoDataRes;
import com.lawencon.community.dto.pricelistevent.GetAllPriceListEventDtoRes;
import com.lawencon.community.dto.pricelistevent.GetByPriceListEventIdDtoDataRes;
import com.lawencon.community.dto.pricelistevent.GetByPriceListEventIdDtoRes;
import com.lawencon.community.dto.pricelistevent.InsertPriceListEventDtoDataRes;
import com.lawencon.community.dto.pricelistevent.InsertPriceListEventDtoReq;
import com.lawencon.community.dto.pricelistevent.InsertPriceListEventDtoRes;
import com.lawencon.community.dto.pricelistevent.UpdatePriceListEventDtoDataRes;
import com.lawencon.community.dto.pricelistevent.UpdatePriceListEventDtoReq;
import com.lawencon.community.dto.pricelistevent.UpdatePriceListEventDtoRes;
import com.lawencon.community.model.PriceListEvent;
import com.lawencon.community.service.PriceListEventService;
import com.lawencon.model.SearchQuery;

@Service
public class PriceListEventServiceImpl extends BaseService implements PriceListEventService {
	private PriceListEventDao priceListEventDao;

	@Autowired
	public PriceListEventServiceImpl(PriceListEventDao priceListEventDao) {
		this.priceListEventDao = priceListEventDao;
	}
	
	@Override
	public GetAllPriceListEventDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllPriceListEventDtoRes getAll = new GetAllPriceListEventDtoRes();

		SearchQuery<PriceListEvent> priceListEvents = priceListEventDao.findAll(query, startPage, maxPage);
		List<GetAllPriceListEventDtoDataRes> priceListEventList = new ArrayList<>();

		for (int i = 0; i < priceListEvents.getData().size(); i++) {
			PriceListEvent priceListEvent = priceListEvents.getData().get(i);
			GetAllPriceListEventDtoDataRes data = new GetAllPriceListEventDtoDataRes();

			data.setId(priceListEvent.getId());
			data.setPriceName(priceListEvent.getPriceName());
			data.setPriceCode(priceListEvent.getPriceCode());
			data.setPriceNominal(priceListEvent.getPriceNominal());
			data.setVersion(priceListEvent.getVersion());
			data.setIsActive(priceListEvent.getIsActive());

			priceListEventList.add(data);
		}

		getAll.setData(priceListEventList);
		getAll.setMsg(null);
		getAll.setTotal(priceListEvents.getCount());

		return getAll;
	}
	
	@Override
	public GetByPriceListEventIdDtoRes findById(String id) throws Exception {
		GetByPriceListEventIdDtoRes getById = new GetByPriceListEventIdDtoRes();

		PriceListEvent priceListEvent = priceListEventDao.findById(id);
		GetByPriceListEventIdDtoDataRes data = new GetByPriceListEventIdDtoDataRes();

		data.setId(priceListEvent.getId());
		data.setPriceName(priceListEvent.getPriceName());
		data.setPriceCode(priceListEvent.getPriceCode());
		data.setPriceNominal(priceListEvent.getPriceNominal());
		data.setVersion(priceListEvent.getVersion());
		data.setIsActive(priceListEvent.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertPriceListEventDtoRes insert(InsertPriceListEventDtoReq data) throws Exception {
		InsertPriceListEventDtoRes insert = new InsertPriceListEventDtoRes();

		try {
			validateInsert(data);
			
			PriceListEvent priceListEvent = new PriceListEvent();
			priceListEvent.setPriceName(data.getPriceName());
			priceListEvent.setPriceCode(data.getPriceCode());
			priceListEvent.setPriceNominal(data.getPriceNominal());
			priceListEvent.setCreatedBy(getId());

			begin();
			PriceListEvent priceListEventInsert = priceListEventDao.save(priceListEvent);
			commit();

			InsertPriceListEventDtoDataRes dataDto = new InsertPriceListEventDtoDataRes();
			dataDto.setId(priceListEventInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(CommonConstant.ACTION_ADD.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Price List Event " + CommonConstant.HAS_BEEN_ADDED.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdatePriceListEventDtoRes update(UpdatePriceListEventDtoReq data) throws Exception {
		UpdatePriceListEventDtoRes update = new UpdatePriceListEventDtoRes();

		try {
			if (data.getVersion() != null) {
				validateUpdate(data);
				
				PriceListEvent priceListEvent = priceListEventDao.findById(data.getId());

				priceListEvent.setPriceName(data.getPriceName());
				priceListEvent.setPriceNominal(data.getPriceNominal());
				priceListEvent.setVersion(data.getVersion());
				priceListEvent.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					priceListEvent.setIsActive(data.getIsActive());
				}

				begin();
				PriceListEvent priceListEventUpdate = priceListEventDao.save(priceListEvent);
				commit();

				UpdatePriceListEventDtoDataRes dataDto = new UpdatePriceListEventDtoDataRes();
				dataDto.setVersion(priceListEventUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Price List Event " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByPriceListEventIdDtoRes deleteById(String id) throws Exception {
		DeleteByPriceListEventIdDtoRes deleteById = new DeleteByPriceListEventIdDtoRes();

		try {
			validateDelete(id);
			
			begin();
			boolean isDeleted = priceListEventDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Price List Eventt " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	private void validateInsert(InsertPriceListEventDtoReq data) throws Exception {
		if (data.getPriceCode() == null || data.getPriceCode().trim().equals("")) {
			throw new Exception("Price List Code Can't Be Null");
		} else {
			PriceListEvent priceListEvent = priceListEventDao.findByCode(data.getPriceCode());
			if (priceListEvent != null) {
				throw new Exception("Price List Code Already Existed");
			}
			if (data.getPriceName() == null || data.getPriceName().trim().equals("")) {
				throw new Exception("Price List Name Can't Be Null");
			}
		}
	}
	
	private void validateUpdate(UpdatePriceListEventDtoReq data) throws Exception {
		if (data.getId() == null || data.getId().trim().equals("")) {
			throw new Exception("Price List Id Can't Be Null");
		} else {
			PriceListEvent priceListEvent = priceListEventDao.findById(data.getId());
			
			if (data.getPriceName() == null || data.getPriceName().trim().equals("")) {
				throw new Exception("Price List Name Can't Be Null");
			}
			
			if (priceListEvent.getVersion() != data.getVersion()) {
				throw new Exception("Price List That You Update Already Updated By Someone");
			}
		}
	}

	private void validateDelete(String id) throws Exception {
		PriceListEvent priceListEvent = priceListEventDao.findById(id);
		
		if(priceListEvent == null) {
			throw new Exception("Price List Id Is Not Exist");
		}
	}
}
