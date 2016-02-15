/*
 * Created on Dec 18, 2004
 *
 */
package org.vb.biller.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.vb.biller.bean.Customer;
import org.vb.biller.util.ServiceUtil;

/**
 * This "a stand-alone service" for getting POJOs for view models and
 * saving POJOs.
 * (in real life this class would connect to a server application). 
 * 
 *  @author Tomi Tuomainen
 */
public class CustomerServiceDelegate {

	private static Logger logger = Logger.getLogger (CustomerServiceDelegate.class);
	
	private static List<org.vb.biller.bean.Customer> customers;
	
	public static List<Customer> findCustomers(String name) {
		logger.info("Finding customers: name=" + name);
		
		if(name != null){
			Customer customer = ServiceUtil.getCustomerService().findCustomerByName(name);
			if(customer != null){
				customers = new ArrayList<Customer>();
				customers.add(customer);
			} else {
				return null;
			}
			
		} else {
			customers = ServiceUtil.getCustomerService().findAll();
		}

		return customers;
	}
}
