package com.lawencon.community.dto.pricelistmember;

import java.util.List;

public class GetAllPriceListMemberDtoRes {
	private String msg;
	private List<GetAllPriceListMemberDtoDataRes> data;
	private Long total;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllPriceListMemberDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPriceListMemberDtoDataRes> data) {
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
