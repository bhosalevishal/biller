/*
 * Created on Dec 18, 2004
 *
 */
package org.vb.biller.model;

import java.util.Collection;

import org.tikeswing.core.YModel;
import org.vb.biller.bean.Customer;
import org.vb.biller.service.CustomerServiceDelegate;

/**
 * FindCustomer view model.
 * 
 * @author Tomi Tuomainen
 */
public class FindCustomerModel extends YModel {

	// search criteria:
	private String name;

	// result of the search, collection of domain objects:
	private Collection<Customer> customers;

	/**
	 * This methods gets customers from "remote application" via delegate (and
	 * notifies connected views).
	 */
	public void findCustomers() {
		this.customers = CustomerServiceDelegate.findCustomers(name);
		notifyObservers("customers");
	}

	// JavaBean getters and setters...

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
		notifyObservers("customers");		
	}

}
