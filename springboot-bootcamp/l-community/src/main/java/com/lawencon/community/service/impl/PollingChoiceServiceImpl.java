package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.PollingChoiceDao;
import com.lawencon.community.dao.PollingDao;
import com.lawencon.community.dto.pollingchoice.DeleteByPollingChoiceIdDtoRes;
import com.lawencon.community.dto.pollingchoice.GetAllPollingChoiceDtoDataRes;
import com.lawencon.community.dto.pollingchoice.GetAllPollingChoiceDtoRes;
import com.lawencon.community.dto.pollingchoice.GetByPollingChoiceIdDtoDataRes;
import com.lawencon.community.dto.pollingchoice.GetByPollingChoiceIdDtoRes;
import com.lawencon.community.dto.pollingchoice.GetByPollingIdDtoDataRes;
import com.lawencon.community.dto.pollingchoice.GetByPollingIdDtoRes;
import com.lawencon.community.dto.pollingchoice.InsertPollingChoiceDtoDataRes;
import com.lawencon.community.dto.pollingchoice.InsertPollingChoiceDtoReq;
import com.lawencon.community.dto.pollingchoice.InsertPollingChoiceDtoRes;
import com.lawencon.community.dto.pollingchoice.UpdatePollingChoiceDtoDataRes;
import com.lawencon.community.dto.pollingchoice.UpdatePollingChoiceDtoReq;
import com.lawencon.community.dto.pollingchoice.UpdatePollingChoiceDtoRes;
import com.lawencon.community.model.Polling;
import com.lawencon.community.model.PollingChoice;
import com.lawencon.community.service.PollingChoiceService;

@Service
public class PollingChoiceServiceImpl extends BaseService implements PollingChoiceService {
	private PollingChoiceDao pollingChoiceDao;
	private PollingDao pollingDao;

	@Autowired
	public PollingChoiceServiceImpl(PollingChoiceDao pollingChoiceDao, PollingDao pollingDao) {
		this.pollingChoiceDao = pollingChoiceDao;
		this.pollingDao = pollingDao;
	}
	
	@Override
	public GetAllPollingChoiceDtoRes findAll() throws Exception {
		GetAllPollingChoiceDtoRes getAll = new GetAllPollingChoiceDtoRes();

		List<PollingChoice> users = pollingChoiceDao.findAll();
		List<GetAllPollingChoiceDtoDataRes> listUser = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {
			PollingChoice pollingChoice = users.get(i);
			GetAllPollingChoiceDtoDataRes data = new GetAllPollingChoiceDtoDataRes();

			data.setId(pollingChoice.getId());
			data.setChoiceCode(pollingChoice.getChoiceCode());
			data.setChoiceName(pollingChoice.getChoiceName());
			data.setPollingId(pollingChoice.getPollingId().getId());
			data.setPollingCode(pollingChoice.getPollingId().getPollingCode());
			data.setPollingName(pollingChoice.getPollingId().getPollingName());
			data.setThreadId(pollingChoice.getPollingId().getThreadId().getId());
			data.setThreadTitle(pollingChoice.getPollingId().getThreadId().getThreadTitle());
			data.setThreadContent(pollingChoice.getPollingId().getThreadId().getThreadContent());
			data.setVersion(pollingChoice.getVersion());
			data.setIsActive(pollingChoice.getIsActive());

			listUser.add(data);
		}

		getAll.setData(listUser);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByPollingChoiceIdDtoRes findById(String id) throws Exception {
		GetByPollingChoiceIdDtoRes getById = new GetByPollingChoiceIdDtoRes();

		PollingChoice pollingChoice = pollingChoiceDao.findById(id);
		GetByPollingChoiceIdDtoDataRes data = new GetByPollingChoiceIdDtoDataRes();

		data.setId(pollingChoice.getId());
		data.setChoiceCode(pollingChoice.getChoiceCode());
		data.setChoiceName(pollingChoice.getChoiceName());
		data.setPollingId(pollingChoice.getPollingId().getId());
		data.setPollingCode(pollingChoice.getPollingId().getPollingCode());
		data.setPollingName(pollingChoice.getPollingId().getPollingName());
		data.setThreadId(pollingChoice.getPollingId().getThreadId().getId());
		data.setThreadTitle(pollingChoice.getPollingId().getThreadId().getThreadTitle());
		data.setThreadContent(pollingChoice.getPollingId().getThreadId().getThreadContent());
		data.setVersion(pollingChoice.getVersion());
		data.setIsActive(pollingChoice.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertPollingChoiceDtoRes insert(InsertPollingChoiceDtoReq data) throws Exception {
		InsertPollingChoiceDtoRes insert = new InsertPollingChoiceDtoRes();

		try {
			validateInsert(data);
			
			PollingChoice pollingChoice = new PollingChoice();
			pollingChoice.setChoiceCode(getAlphaNumericString(5));
			pollingChoice.setChoiceName(data.getChoiceName());
			
			Polling polling = pollingDao.findById(data.getPollingId());
			pollingChoice.setPollingId(polling);
			pollingChoice.setCreatedBy(getId());

			PollingChoice pollingChoiceInsert = pollingChoiceDao.save(pollingChoice);

			InsertPollingChoiceDtoDataRes dataDto = new InsertPollingChoiceDtoDataRes();
			dataDto.setId(pollingChoiceInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(null);
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdatePollingChoiceDtoRes update(UpdatePollingChoiceDtoReq data) throws Exception {
		UpdatePollingChoiceDtoRes update = new UpdatePollingChoiceDtoRes();

		try {
			if (data.getVersion() != null) {
				PollingChoice pollingChoice = pollingChoiceDao.findById(data.getId());
				pollingChoice.setChoiceName(data.getChoiceName());
				pollingChoice.setVersion(data.getVersion());
				
				if (data.getIsActive() != null) {
					pollingChoice.setIsActive(data.getIsActive());
				}

				begin();
				PollingChoice pollingChoiceUpdate = pollingChoiceDao.save(pollingChoice);
				commit();

				UpdatePollingChoiceDtoDataRes dataDto = new UpdatePollingChoiceDtoDataRes();
				dataDto.setVersion(pollingChoiceUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByPollingChoiceIdDtoRes deleteById(String id) throws Exception {
		DeleteByPollingChoiceIdDtoRes deleteById = new DeleteByPollingChoiceIdDtoRes();

		try {
			begin();
			boolean isDeleted = pollingChoiceDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetByPollingIdDtoRes findByPolling(String id) throws Exception {
		GetByPollingIdDtoRes getByPolling = new GetByPollingIdDtoRes();

		List<PollingChoice> users = pollingChoiceDao.findByPolling(id);
		List<GetByPollingIdDtoDataRes> listUser = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {
			PollingChoice pollingChoice = users.get(i);
			GetByPollingIdDtoDataRes data = new GetByPollingIdDtoDataRes();

			data.setId(pollingChoice.getId());
			data.setChoiceCode(pollingChoice.getChoiceCode());
			data.setChoiceName(pollingChoice.getChoiceName());
			data.setPollingId(pollingChoice.getPollingId().getId());
			data.setPollingCode(pollingChoice.getPollingId().getPollingCode());
			data.setPollingName(pollingChoice.getPollingId().getPollingName());
			data.setThreadId(pollingChoice.getPollingId().getThreadId().getId());
			data.setThreadTitle(pollingChoice.getPollingId().getThreadId().getThreadTitle());
			data.setThreadContent(pollingChoice.getPollingId().getThreadId().getThreadContent());
			data.setVersion(pollingChoice.getVersion());
			data.setIsActive(pollingChoice.getIsActive());

			listUser.add(data);
		}

		getByPolling.setData(listUser);
		getByPolling.setMsg(null);

		return getByPolling;
	}
	
	private void validateInsert(InsertPollingChoiceDtoReq data) throws Exception {
		if (data.getPollingId() == null || data.getPollingId().trim().equals("")) {
			throw new Exception("Polling Cant Null");
		} else {
			if (data.getChoiceName() == null || data.getChoiceName().trim().equals("")) {
				throw new Exception("Choice Name Cant Null");
			}
		}
	}
}
