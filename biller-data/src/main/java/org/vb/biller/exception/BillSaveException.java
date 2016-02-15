package org.vb.biller.exception;

import org.apache.log4j.Logger;

public class BillSaveException extends Exception {
	
	Logger logger = Logger.getLogger(BillSaveException.class);

	public BillSaveException(String message)
	{
		super(message);
	}
}
