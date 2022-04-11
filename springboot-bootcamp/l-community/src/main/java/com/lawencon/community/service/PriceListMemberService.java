package com.lawencon.community.service;

import com.lawencon.community.dto.pricelistmember.DeleteByPriceListMemberIdDtoRes;
import com.lawencon.community.dto.pricelistmember.GetAllPriceListMemberDtoRes;
import com.lawencon.community.dto.pricelistmember.GetByPriceListMemberIdDtoRes;
import com.lawencon.community.dto.pricelistmember.InsertPriceListMemberDtoReq;
import com.lawencon.community.dto.pricelistmember.InsertPriceListMemberDtoRes;
import com.lawencon.community.dto.pricelistmember.UpdatePriceListMemberDtoReq;
import com.lawencon.community.dto.pricelistmember.UpdatePriceListMemberDtoRes;

public interface PriceListMemberService {
	GetAllPriceListMemberDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetByPriceListMemberIdDtoRes findById(String id) throws Exception;
	InsertPriceListMemberDtoRes insert(InsertPriceListMemberDtoReq data) throws Exception;
	UpdatePriceListMemberDtoRes update(UpdatePriceListMemberDtoReq data) throws Exception;
	DeleteByPriceListMemberIdDtoRes deleteById(String id) throws Exception;
}
