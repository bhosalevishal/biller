package org.vb.biller.executable;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.vb.biller.bean.Bill;
import org.vb.biller.bean.Customer;
import org.vb.biller.bean.Job;
import org.vb.biller.exception.BillSaveException;
import org.vb.biller.exception.CustomerSaveException;
import org.vb.biller.exception.JobSaveException;
import org.vb.biller.service.impl.BillService;
import org.vb.biller.service.impl.CustomerService;
import org.vb.biller.service.impl.JobService;
import org.vb.biller.util.ModeOfWork;
public class Executable {
	
	public static void main(String [] args){
		
		// Acquire Context
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
		
		CustomerService customerService = (CustomerService)  context.getBean("customerService");
		JobService jobService = (JobService)  context.getBean("jobService");
		BillService billService = (BillService) context.getBean("billService");
		
		/// ---------------------------------
		
		Customer customer = new Customer();
		customer.setName("Test Customer");
		customer.setPath("test path");
		
		try {
			customer = customerService.saveAndFlush(customer);
		} catch (CustomerSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/// ---------------------------------
		
		customer = customerService.findCustomerByPath("test path");
		
		Job job = new Job();
		job.setCustomer(customer);
		job.setCreateDate(new Date());
		job.setName("Job Name");
		job.setPath("Job Path");
		job.setPages(2);
		job.setRate(5.00);
		job.setModeOfWork(ModeOfWork.NEW);
		
		try {
			job = jobService.saveAndFlush(job);
		} catch (JobSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Job job1 = new Job();
		job1.setCustomer(customer);
		job1.setCreateDate(new Date());
		job1.setName("Job Name 1");
		job1.setPath("Job Path 1");
		job1.setPages(3);
		job1.setRate(5.00);
		job1.setModeOfWork(ModeOfWork.MODIFIED);
		
		try {
			job = jobService.saveAndFlush(job1);
		} catch (JobSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/// ---------------------------------
		
		List<Job> jobs = jobService.findAll();
		
		Bill bill = new Bill();
		bill.setJobs(jobs);
		bill.setCustomer(customer);
		bill.setPrintDate(new Date());
		
		try {
			billService.saveAndFlush(bill);
		} catch (BillSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/// ---------------------------------
		
		// Close context
		context.close();
	}
}