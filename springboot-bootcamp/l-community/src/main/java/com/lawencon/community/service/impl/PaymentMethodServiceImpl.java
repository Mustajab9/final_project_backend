package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.PaymentMethodDao;
import com.lawencon.community.dto.paymentmethod.DeleteByPaymentMethodIdDtoRes;
import com.lawencon.community.dto.paymentmethod.GetAllPaymentMethodDtoDataRes;
import com.lawencon.community.dto.paymentmethod.GetAllPaymentMethodDtoRes;
import com.lawencon.community.dto.paymentmethod.GetByPaymentMethodIdDtoDataRes;
import com.lawencon.community.dto.paymentmethod.GetByPaymentMethodIdDtoRes;
import com.lawencon.community.dto.paymentmethod.InsertPaymentMethodDtoDataRes;
import com.lawencon.community.dto.paymentmethod.InsertPaymentMethodDtoReq;
import com.lawencon.community.dto.paymentmethod.InsertPaymentMethodDtoRes;
import com.lawencon.community.dto.paymentmethod.UpdatePaymentMethodDtoDataRes;
import com.lawencon.community.dto.paymentmethod.UpdatePaymentMethodDtoReq;
import com.lawencon.community.dto.paymentmethod.UpdatePaymentMethodDtoRes;
import com.lawencon.community.model.PaymentMethod;
import com.lawencon.community.service.PaymentMethodService;

@Service
public class PaymentMethodServiceImpl extends BaseService implements PaymentMethodService {
	private PaymentMethodDao paymentMethodDao;

	@Autowired
	public PaymentMethodServiceImpl(PaymentMethodDao paymentMethodDao) {
		this.paymentMethodDao = paymentMethodDao;
	}
	
	@Override
	public GetAllPaymentMethodDtoRes findAll(int startPage, int maxPage) throws Exception {
		GetAllPaymentMethodDtoRes getAll = new GetAllPaymentMethodDtoRes();

		List<PaymentMethod> paymentMethods = paymentMethodDao.findAll(startPage, maxPage);
		List<GetAllPaymentMethodDtoDataRes> listPaymentMethod = new ArrayList<>();

		for (int i = 0; i < paymentMethods.size(); i++) {
			PaymentMethod paymentMethod = paymentMethods.get(i);
			GetAllPaymentMethodDtoDataRes data = new GetAllPaymentMethodDtoDataRes();

			data.setId(paymentMethod.getId());
			data.setPaymentCode(paymentMethod.getPaymentCode());
			data.setPaymentName(paymentMethod.getPaymentName());
			data.setVersion(paymentMethod.getVersion());
			data.setIsActive(paymentMethod.getIsActive());

			listPaymentMethod.add(data);
		}

		getAll.setData(listPaymentMethod);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByPaymentMethodIdDtoRes findById(String id) throws Exception {
		GetByPaymentMethodIdDtoRes getById = new GetByPaymentMethodIdDtoRes();

		PaymentMethod paymentMethod = paymentMethodDao.findById(id);
		GetByPaymentMethodIdDtoDataRes data = new GetByPaymentMethodIdDtoDataRes();

		data.setId(paymentMethod.getId());
		data.setPaymentCode(paymentMethod.getPaymentCode());
		data.setPaymentName(paymentMethod.getPaymentName());
		data.setVersion(paymentMethod.getVersion());
		data.setIsActive(paymentMethod.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertPaymentMethodDtoRes insert(InsertPaymentMethodDtoReq data) throws Exception {
		InsertPaymentMethodDtoRes insert = new InsertPaymentMethodDtoRes();

		try {
			PaymentMethod paymentMethod = new PaymentMethod();
			paymentMethod.setPaymentCode(getAlphaNumericString(5));
			paymentMethod.setPaymentName(data.getPaymentName());
			paymentMethod.setCreatedBy(getId());
			
			begin();
			PaymentMethod paymentMethodInsert = paymentMethodDao.save(paymentMethod);
			commit();
			
			InsertPaymentMethodDtoDataRes dataDto = new InsertPaymentMethodDtoDataRes();
			dataDto.setId(paymentMethodInsert.getId());

			insert.setData(dataDto);
			insert.setMsg("Insert Success");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdatePaymentMethodDtoRes update(UpdatePaymentMethodDtoReq data) throws Exception {
		UpdatePaymentMethodDtoRes update = new UpdatePaymentMethodDtoRes();

		try {
			if (data.getVersion() != null) {
				PaymentMethod paymentMethod = paymentMethodDao.findById(data.getId());
				paymentMethod.setPaymentName(data.getPaymentName());
				paymentMethod.setUpdatedBy(getId());
				paymentMethod.setVersion(data.getVersion());

				if (data.getIsActive() != null) {
					paymentMethod.setIsActive(data.getIsActive());
				}

				begin();
				PaymentMethod userUpdate = paymentMethodDao.save(paymentMethod);
				commit();

				UpdatePaymentMethodDtoDataRes dataDto = new UpdatePaymentMethodDtoDataRes();
				dataDto.setVersion(userUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg("Update Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByPaymentMethodIdDtoRes deleteById(String id) throws Exception {
		DeleteByPaymentMethodIdDtoRes deleteById = new DeleteByPaymentMethodIdDtoRes();

		try {
			begin();
			boolean isDeleted = paymentMethodDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg("Delete Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
}
