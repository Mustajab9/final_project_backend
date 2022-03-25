package com.lawencon.community.service;

import com.lawencon.community.dto.pricelistmember.DeleteByPriceListMemberIdDtoRes;
import com.lawencon.community.dto.pricelistmember.GetAllPriceListMemberDtoRes;
import com.lawencon.community.dto.pricelistmember.GetByPriceListMemberIdDtoRes;
import com.lawencon.community.dto.pricelistmember.InsertPriceListMemberDtoReq;
import com.lawencon.community.dto.pricelistmember.InsertPriceListMemberDtoRes;
import com.lawencon.community.dto.pricelistmember.UpdatePriceListMemberDtoReq;
import com.lawencon.community.dto.pricelistmember.UpdatePriceListMemberDtoRes;

public interface PriceListMemberService {
	public GetAllPriceListMemberDtoRes findAll() throws Exception;
	public GetByPriceListMemberIdDtoRes findById(String id) throws Exception;
	public InsertPriceListMemberDtoRes insert(InsertPriceListMemberDtoReq data) throws Exception;
	public UpdatePriceListMemberDtoRes update(UpdatePriceListMemberDtoReq data) throws Exception;
	public DeleteByPriceListMemberIdDtoRes deleteById(String id) throws Exception;
}
