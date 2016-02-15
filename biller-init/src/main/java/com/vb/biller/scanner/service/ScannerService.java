package com.vb.biller.scanner.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.vb.biller.bean.Customer;
import org.vb.biller.bean.Job;

public interface ScannerService {
	
	public List<Customer> scanCustomers(String folder);

	public HashMap<Customer, ArrayList<Job>> scan(List<Customer> customers);
	
	public void save(HashMap<Customer, ArrayList<Job>> customerJobs);
}
