package com.vb.biller.scanner.service;

import java.io.File;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.vb.biller.bean.Customer;
import org.vb.biller.bean.Job;
import org.vb.biller.exception.CustomerSaveException;
import org.vb.biller.exception.JobSaveException;
import org.vb.biller.util.ModeOfWork;
import org.vb.biller.util.ServiceUtil;

import com.vb.biller.scanner.util.BillerInitUtil;

public class ScannerServiceImpl implements ScannerService {

	private HashMap<Customer, ArrayList<Job>> customerJobs;
	private List<Customer> customers;
	private List<Job> jobs;

	@Override
	public HashMap<Customer, ArrayList<Job>> scan(List<Customer> customers) {
		customerJobs = new HashMap<Customer, ArrayList<Job>>();
		for (Customer customer : customers) {
			customerJobs.put(customer, (ArrayList<Job>) scanJobs(customer.getPath()));
		}
		return customerJobs;
	}
	
	
	private List<Job> scanJobs(String customerPath){
		jobs = new ArrayList<Job>();
		
		File customerFolder = new File(customerPath);
		for (final File jobEntry : customerFolder.listFiles()) {
			if (jobEntry.isDirectory()) {
				this.scanJobs(jobEntry.toString());
			} else {
				BasicFileAttributes jobAttr = BillerInitUtil.getBasicAttributes(jobEntry.getPath());
				
				String jobName = BillerInitUtil.removeExtension(jobEntry.getName());
				Job job = new Job(
						jobName, 
						jobEntry.getPath(), 
						new Integer(0), 
						new Double(0), 
						new Double(0), 
						ModeOfWork.NEW, 
						new Date(jobAttr.creationTime().toMillis()), 
						new Date(jobAttr.lastModifiedTime().toMillis())
						);

				jobs.add(job);
			}
		}
		return jobs;
	}

	@Override
	public void save(HashMap<Customer, ArrayList<Job>> customerJobs) {
		Customer savedCustomer = null;
		
		for (Entry<Customer, ArrayList<Job>> customerJob : customerJobs.entrySet()) {
			Customer customer = customerJob.getKey();
			try {
				savedCustomer = ServiceUtil.getCustomerService().saveAndFlush(customer);
			} catch (CustomerSaveException e) {
				e.printStackTrace();
			}

			ArrayList<Job> jobs = customerJob.getValue();
			for (Job job : jobs) {
				try {
					job.setCustomer(savedCustomer);
					ServiceUtil.getJobService().saveAndFlush(job);
				} catch (JobSaveException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<Customer> scanCustomers(String customerFolderPath) {
		customers = new ArrayList<Customer>();
		
		File customerFolder = new File(customerFolderPath);
		for(File customer : customerFolder.listFiles()){
			BasicFileAttributes customerAttr = BillerInitUtil.getBasicAttributes(customer.getPath());
			
			Customer newCustomer = new Customer(
					customer.getName(), 
					customer.getPath(),
					new Date(customerAttr.creationTime().toMillis()),
					new Date(customerAttr.lastModifiedTime().toMillis())
					);
			
			customers.add(newCustomer);
		}
		return customers;
	}
}
