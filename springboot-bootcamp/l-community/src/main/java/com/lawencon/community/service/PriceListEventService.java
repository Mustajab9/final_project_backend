package com.lawencon.community.service;

import com.lawencon.community.dto.pricelistevent.DeleteByPriceListEventIdDtoRes;
import com.lawencon.community.dto.pricelistevent.GetAllPriceListEventDtoRes;
import com.lawencon.community.dto.pricelistevent.GetByPriceListEventIdDtoRes;
import com.lawencon.community.dto.pricelistevent.InsertPriceListEventDtoReq;
import com.lawencon.community.dto.pricelistevent.InsertPriceListEventDtoRes;
import com.lawencon.community.dto.pricelistevent.UpdatePriceListEventDtoReq;
import com.lawencon.community.dto.pricelistevent.UpdatePriceListEventDtoRes;

public interface PriceListEventService {
	GetAllPriceListEventDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetByPriceListEventIdDtoRes findById(String id) throws Exception;
	InsertPriceListEventDtoRes insert(InsertPriceListEventDtoReq data) throws Exception;
	UpdatePriceListEventDtoRes update(UpdatePriceListEventDtoReq data) throws Exception;
	DeleteByPriceListEventIdDtoRes deleteById(String id) throws Exception;
}
