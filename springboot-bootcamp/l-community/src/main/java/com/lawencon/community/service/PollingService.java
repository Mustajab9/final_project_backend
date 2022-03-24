package com.lawencon.community.service;

import com.lawencon.community.dto.polling.DeleteByPollingIdDtoRes;
import com.lawencon.community.dto.polling.GetAllPollingDtoRes;
import com.lawencon.community.dto.polling.GetByPollingIdDtoRes;
import com.lawencon.community.dto.polling.InsertPollingDtoReq;
import com.lawencon.community.dto.polling.InsertPollingDtoRes;
import com.lawencon.community.dto.polling.UpdatePollingDtoReq;
import com.lawencon.community.dto.polling.UpdatePollingDtoRes;
import com.lawencon.community.model.Polling;

public interface PollingService {
	public GetAllPollingDtoRes getAll() throws Exception;
	public GetByPollingIdDtoRes getById(String id) throws Exception;
	public InsertPollingDtoRes insert(InsertPollingDtoReq dasta) throws Exception;
	public UpdatePollingDtoRes update(UpdatePollingDtoReq data) throws Exception;
	public DeleteByPollingIdDtoRes deleteById(String id) throws Exception;
	public Polling getByThread(String id) throws Exception;
}
