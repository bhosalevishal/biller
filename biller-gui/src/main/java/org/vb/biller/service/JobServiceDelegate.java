package org.vb.biller.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.vb.biller.bean.Customer;
import org.vb.biller.bean.Job;
import org.vb.biller.exception.JobSaveException;
import org.vb.biller.util.ServiceUtil;

public class JobServiceDelegate {

	private static Logger logger = Logger.getLogger (JobServiceDelegate.class);
	
	private static List<Job> jobs;
	
	public static void saveJobs(List<Job> jobs) {
		logger.info("Saving jobs: ");
	       Iterator<Job> it = jobs.iterator();
	       while (it.hasNext()) {
	           Job j = (Job) it.next();
	           logger.info(j+"");
	       }
	}
	
	public static Job updateJob(Job job) throws JobSaveException{
		return ServiceUtil.getJobService().saveAndFlush(job);
	}

	public static Job findJobById(Integer id){
		return ServiceUtil.getJobService().findJobById(id);
	}

	public static List<Job> findJobs(String jobName, Customer customer) { 
		jobs = new ArrayList<Job>();
		String customerName = null;
		
		if(jobName != null && customer == null){
			Job fetchedJob = ServiceUtil.getJobService().findJobByName(jobName);
			if(fetchedJob != null){
				jobs.add(fetchedJob);
			} else {
				return null;
			}
		}
		
		if(customer != null && jobName == null){
			customerName = customer.getName();
			List<Job> fetchedJob = ServiceUtil.getJobService().findJobByCustomerName(customerName);
			if(fetchedJob != null){
				jobs.addAll(fetchedJob);
			} else {
				return null;
			}
		}
		
		if(jobName != null && customer != null){
			List<Job> fetchedJob = ServiceUtil.getJobService().findJobByNameAndCustomer(jobName, customer);
			jobs.addAll(fetchedJob);
		}
		
		if(jobName == null && customer == null){
			List<Job> fetchedJob = ServiceUtil.getJobService().findAll();
			jobs.addAll(fetchedJob);
		}
		
		return jobs;
	}

	public static List<Job> findJobByValidTrueAndCustomer(Customer customer) {
		return ServiceUtil.getJobService().findJobByValidTrueAndCustomer(customer);
	}


}
