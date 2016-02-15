package org.vb.biller.exception;

import org.apache.log4j.Logger;

public class CustomerSaveException extends Exception {
	
	Logger logger = Logger.getLogger(CustomerSaveException.class);
	
	public CustomerSaveException(String message)
	{
		super(message);
	}
}
