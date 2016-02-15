package org.vb.biller.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.vb.biller.service.impl.BillService;
import org.vb.biller.service.impl.CustomerService;
import org.vb.biller.service.impl.JobService;

public final class ServiceUtil {
	
	// Acquire Context
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
			
	private static CustomerService customerService;
	private static JobService jobService;
	private static BillService billService;
	
	public static CustomerService getCustomerService(){
		customerService = (CustomerService)  context.getBean("customerService");
		return customerService;
	}
	
	public static JobService getJobService(){
		jobService = (JobService)  context.getBean("jobService");
		return jobService;
	}
	
	public static BillService getBillService(){
		billService = (BillService) context.getBean("billService");
		return billService;
	}

}
