package com.lawencon.community.service;

import com.lawencon.community.dto.pricelistevent.DeleteByPriceListEventIdDtoRes;
import com.lawencon.community.dto.pricelistevent.GetAllPriceListEventDtoRes;
import com.lawencon.community.dto.pricelistevent.GetByPriceListEventIdDtoRes;
import com.lawencon.community.dto.pricelistevent.InsertPriceListEventDtoReq;
import com.lawencon.community.dto.pricelistevent.InsertPriceListEventDtoRes;
import com.lawencon.community.dto.pricelistevent.UpdatePriceListEventDtoReq;
import com.lawencon.community.dto.pricelistevent.UpdatePriceListEventDtoRes;

public interface PriceListEventService {
	public GetAllPriceListEventDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public GetByPriceListEventIdDtoRes findById(String id) throws Exception;
	public InsertPriceListEventDtoRes insert(InsertPriceListEventDtoReq data) throws Exception;
	public UpdatePriceListEventDtoRes update(UpdatePriceListEventDtoReq data) throws Exception;
	public DeleteByPriceListEventIdDtoRes deleteById(String id) throws Exception;
}
