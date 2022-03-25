package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.PriceListMemberDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.PriceListMemberService;

@Service
public class PriceListMemberServiceImpl extends BaseService implements PriceListMemberService {
	private PriceListMemberDao priceListMemberDao;

	@Autowired
	public PriceListMemberServiceImpl(PriceListMemberDao priceListMemberDao) {
		this.priceListMemberDao = priceListMemberDao;
	}
}
