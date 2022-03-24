package com.lawencon.community.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.base.BaseEntity;

public class BaseDao<T extends BaseEntity> extends BaseDaoImpl<T> {
	
	protected byte[] convertObjToByteArray(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(obj);
        outputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }
}
