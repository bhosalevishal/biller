package org.vb.biller.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class CustomerExistsException extends DataIntegrityViolationException  {

	public CustomerExistsException(String msg) {
		super(msg);
	}
}
