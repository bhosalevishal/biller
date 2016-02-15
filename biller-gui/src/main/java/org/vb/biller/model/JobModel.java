/*
 * Created on 23.12.2004
 *
 */
package org.vb.biller.model;

import java.util.List;

import javax.swing.table.TableModel;

import org.tikeswing.core.YModel;
import org.vb.biller.bean.Customer;
import org.vb.biller.bean.Job;
import org.vb.biller.exception.JobSaveException;
import org.vb.biller.pdf.CreatePDF;
import org.vb.biller.service.JobServiceDelegate;
import org.vb.biller.util.DecimalFormatter;
import org.vb.biller.util.ModeOfWork;
import org.vb.biller.util.NumberInWords;

/**
 * Job view model.
 *
 * @author Tomi Tuomainen
 */
public class JobModel extends YModel {
	
	// search criteria:
	private String name;

	// the Job JavaBean
	private Job job;

	// the Customer JavaBean
	private Customer customer;

	private List<Job> jobs;
	
	private List<Job> bills;
	
	private Double billtotal;
	
	private String billtotalnumbers;
	private String billtotalwords;

	/**
	 * This method notifies CustomerView that customer has changed.
	 */

	public void findJobs() {
		this.jobs = (List<Job>) JobServiceDelegate.findJobs(name, customer);
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
			e.printStackTrace();
		}
		notifyObservers("job");
	}
	
	public void findJobByValidTrue() {
		this.bills = (List<Job>) JobServiceDelegate.findJobByValidTrueAndCustomer(customer);
		notifyObservers("bills");
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

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = (List<Job>) jobs;
		// notifying a view and a controller that field "job" has changed:
		notifyObservers("jobs");
	}
	
	public List<Job> getBills() {
		return bills;
	}

	public void setBills(List<Job> bills) {
		this.bills = (List<Job>) bills;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		// notifying a view and a controller that field "customer" has changed:
		notifyObservers("customer");
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public Double getBilltotal() {
		return billtotal;
	}

	public void setBilltotal(Double billtotal) {
		this.billtotal = billtotal;
		notifyObservers("billtotal");
	}
	
	public String getBilltotalnumbers() {
		return billtotalnumbers;
	}

	public void setBilltotalnumbers(String billtotalnumbers) {
		this.billtotalnumbers = billtotalnumbers;
		notifyObservers("billtotalnumbers");
	}

	public String getBilltotalwords() {
		return billtotalwords;
	}

	public void setBilltotalwords(String billtotalwords) {
		
		if(billtotalwords.length() > 0){
			this.billtotalwords = billtotalwords.concat("Only.");
		}
		
		notifyObservers("billtotalwords");
	}

	public void calculateLineItemTotal(TableModel model, int row) {
    	Integer pages = (Integer) model.getValueAt(row, 2);
    	Double rate = (Double) model.getValueAt(row, 3);
    	Double total = pages * rate;
    	model.setValueAt(total, row, 4);
	}

	public void calculateBillTotal(TableModel model) {
		Double totalAmount = new Double(0);
		Integer rowCount = model.getRowCount();
		for(int row = 0; row  <rowCount; row++){
			Double lineItemTotal = (Double) model.getValueAt(row, 4);
			totalAmount += lineItemTotal;
		}
		
		this.setBilltotal(totalAmount);
		
		NumberInWords numberInWords = NumberInWords.getInstance(totalAmount.longValue());
		this.setBilltotalwords(numberInWords.getNumberInWords());
		this.setBilltotalnumbers(DecimalFormatter.formatDecimal(totalAmount.longValue()));
	}
	
	public void changeModeOfWork(TableModel model, int row) {
		ModeOfWork modeOfWork = (ModeOfWork) model.getValueAt(row, 1);
    	model.setValueAt(modeOfWork, row, 1);
	}

	public void createPdf() {
		CreatePDF createPDF = new CreatePDF();
		createPDF.generatePdf(this);
	}
}
