/*
 * Created on 23.12.2004
 *
 */
package org.vb.biller.model;

import java.util.Collection;

import org.tikeswing.core.YModel;
import org.vb.biller.bean.Customer;
import org.vb.biller.bean.Job;
import org.vb.biller.exception.JobSaveException;
import org.vb.biller.service.JobServiceDelegate;

/**
 * Job view model.
 *
 * @author Tomi Tuomainen
 */
public class BillModel extends YModel {
	
	// search criteria:
	private String name;

	// the Job JavaBean
	private Job job;

	// the Customer JavaBean
	private Customer customer;

	private Collection<Job> jobs;

	/**
	 * This method notifies CustomerView that customer has changed.
	 */

	public void findJobs() {
		this.jobs = JobServiceDelegate.findJobs(name, customer);
		notifyObservers("jobs");
	}

	public void findJobById(Integer id) {
		this.job = JobServiceDelegate.findJobById(id);
		notifyObservers("job");
	}
	
	public void updateJob() {
		try {
			this.job = JobServiceDelegate.updateJob(job);
		} catch (JobSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notifyObservers("job");
	}

	// JavaBean getters and setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		notifyObservers("name");
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
		// notifying a view and a controller that field "job" has changed:
		notifyObservers("job");
	}

	public Collection<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Collection<Job> jobs) {
		this.jobs = jobs;
		// notifying a view and a controller that field "job" has changed:
		notifyObservers("jobs");
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		// notifying a view and a controller that field "customer" has changed:
		notifyObservers("customer");
	}

	public Customer getCustomer() {
		return customer;
	}
}
