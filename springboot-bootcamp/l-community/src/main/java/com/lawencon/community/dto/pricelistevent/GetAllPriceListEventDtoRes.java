package com.lawencon.community.dto.pricelistevent;

import java.util.List;

public class GetAllPriceListEventDtoRes {
	private String msg;
	private List<GetAllPriceListEventDtoDataRes> data;
	private Long total;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllPriceListEventDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPriceListEventDtoDataRes> data) {
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
