package com.vb.biller.scanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.vb.biller.bean.Customer;
import org.vb.biller.bean.Job;

import com.vb.biller.property.PropertyReader;
import com.vb.biller.scanner.service.ScannerService;
import com.vb.biller.scanner.service.ScannerServiceImpl;

public class Scanner {
	
	private static Logger logger = Logger.getLogger(Scanner.class);
	
	public static void main(String[] args) throws IOException {
		
		String billerInitLog4j = "properties/init-log4j.properties";
		PropertyConfigurator.configure(billerInitLog4j);
		
		String billerInitConfig = "properties/config.properties";
		PropertyConfigurator.configure(billerInitConfig);
		
		
		PropertyReader reader = new PropertyReader("config.properties");
		Collection<String> dirScan = reader.getPropValue("dir.scan");

		ScannerService scanner = new ScannerServiceImpl();

		logger.info("Scanner : Started...");

		for (String directory : dirScan) {
			List<Customer> customers = scanner.scanCustomers(directory);
			HashMap<Customer, ArrayList<Job>> customerJobs = scanner.scan(customers);
			scanner.save(customerJobs);
		}

		logger.info("Scanner : Finished...");
	}

}
