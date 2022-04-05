package com.lawencon.community.dto.pricelistevent;

import java.util.List;

public class GetAllPriceListEventDtoRes {
	private String msg;
	private List<GetAllPriceListEventDtoDataRes> data;
	private Integer total;

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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
