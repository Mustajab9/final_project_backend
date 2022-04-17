package com.lawencon.community.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.BaseEntity;

public class BaseDao<T extends BaseEntity> extends AbstractJpaDao<T> {
	
	protected byte[] convertObjToByteArray(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(obj);
        outputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }
	
	protected T resultCheck(List<T> model) {
		return model.size() > 0 ? model.get(0) : null;
	}

	protected List<T> resultCheckList(List<T> list) {
		return list.size() > 0 ? list : null;
	}
}
