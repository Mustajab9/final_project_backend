package com.lawencon.community.service;

import com.lawencon.community.dto.attachment.InsertAttachmentDtoReq;
import com.lawencon.community.dto.attachment.UpdateAttachmentDtoReq;
import com.lawencon.community.dto.pricelistevent.DeleteByPriceListEventIdDtoRes;
import com.lawencon.community.dto.pricelistevent.GetAllPriceListEventDtoRes;
import com.lawencon.community.dto.pricelistevent.GetByPriceListEventIdDtoRes;
import com.lawencon.community.dto.pricelistevent.InsertPriceListEventDtoRes;
import com.lawencon.community.dto.pricelistevent.UpdatePriceListEventDtoRes;

public interface PriceListEventService {
	public GetAllPriceListEventDtoRes findAll(int startPage, int maxPage) throws Exception;
	public GetByPriceListEventIdDtoRes findById(String id) throws Exception;
	public InsertPriceListEventDtoRes insert(InsertAttachmentDtoReq data) throws Exception;
	public UpdatePriceListEventDtoRes update(UpdateAttachmentDtoReq data) throws Exception;
	public DeleteByPriceListEventIdDtoRes deleteById(String id) throws Exception;
}
