package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.pollingchoice.DeleteByPollingChoiceIdDtoRes;
import com.lawencon.community.dto.pollingchoice.GetAllPollingChoiceDtoRes;
import com.lawencon.community.dto.pollingchoice.GetByPollingChoiceIdDtoRes;
import com.lawencon.community.dto.pollingchoice.InsertPollingChoiceDtoReq;
import com.lawencon.community.dto.pollingchoice.InsertPollingChoiceDtoRes;
import com.lawencon.community.dto.pollingchoice.UpdatePollingChoiceDtoReq;
import com.lawencon.community.dto.pollingchoice.UpdatePollingChoiceDtoRes;
import com.lawencon.community.model.PollingChoice;

public interface PollingChoiceService {
	public GetAllPollingChoiceDtoRes getAll() throws Exception;
	public GetByPollingChoiceIdDtoRes getById(String id) throws Exception;
	public InsertPollingChoiceDtoRes insert(InsertPollingChoiceDtoReq data) throws Exception;
	public UpdatePollingChoiceDtoRes update(UpdatePollingChoiceDtoReq data) throws Exception;
	public DeleteByPollingChoiceIdDtoRes deleteById(String id) throws Exception;
	public List<PollingChoice> getByPolling(String id) throws Exception;
}
