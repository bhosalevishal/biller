package org.vb.biller.execute;

import javax.swing.SwingUtilities;

import org.apache.log4j.PropertyConfigurator;
import org.vb.biller.controller.CustomerFrameController;

/**
 * RunApplication Class calls CustomerFrameController to running the
 * application.
 * 
 */
public class RunBillerApplication {
	public static void main(String[] args) {
		
		String billerGuiLog4j = "properties/gui-log4j.properties";
		PropertyConfigurator.configure(billerGuiLog4j);
		
		String billerGuiCustomer = "properties/customer.properties";
		PropertyConfigurator.configure(billerGuiCustomer);
		
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new CustomerFrameController();
			}
		});
	}
}
