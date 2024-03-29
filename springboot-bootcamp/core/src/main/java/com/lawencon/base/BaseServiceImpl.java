package com.lawencon.base;

/**
 * @author lawencon05
 */
public class BaseServiceImpl {

	protected void begin() {
		ConnHandler.begin();
	}

	protected void commit() {
		ConnHandler.commit();
	}

	protected void rollback() {
		ConnHandler.rollback();
	}

	protected void clear() {
		ConnHandler.clear();
	}
}
