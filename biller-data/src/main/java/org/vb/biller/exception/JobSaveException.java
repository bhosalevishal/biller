package org.vb.biller.exception;

import org.apache.log4j.Logger;

public class JobSaveException extends Exception {
	
	Logger logger = Logger.getLogger(JobSaveException.class);
	
	public JobSaveException(String message)
	{
		super(message);
	}
}
