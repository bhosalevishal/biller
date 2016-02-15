/*
 * Created on 22.12.2004
 *
 */
package org.vb.biller.controller;



import java.awt.Component;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.tikeswing.core.YApplicationEvent;
import org.tikeswing.core.YController;
import org.tikeswing.core.YIModelComponent;
import org.tikeswing.core.error.YModelSetValueException;
import org.tikeswing.core.tools.YUIToolkit;
import org.vb.biller.bean.Customer;
import org.vb.biller.model.CustomerModel;
import org.vb.biller.util.BillerConstants;
import org.vb.biller.view.CustomerView;



/**
 * The controller for CustomerView and CustomerModel.
 *
 * @author Tomi Tuomainen
 */
public class CustomerController extends YController {
	
	private static Logger logger = Logger.getLogger(CustomerController.class);
  
	private CustomerView customerView = new CustomerView();
	private CustomerModel customerModel = new CustomerModel();
	
	public CustomerController() {
		this.setUpMVC(customerModel, customerView);
		// registering for listening to CUSTOMER_CHANGED event
		this.register(BillerConstants.CUSTOMER_CHANGED);
	}
	
	/**
     * Handling CUSTOMER_CHANGED event (sent by FindCustomerController).
     */
 	public void receivedApplicationEvent(YApplicationEvent ev) {
		if (ev.getName().equals(BillerConstants.CUSTOMER_CHANGED)) {
			Customer customer = (Customer)ev.getValue(); 
			
			// setting view model:
			customerModel.setCustomer(customer);
	        
	        // resetting view for unsaved changes tracking
	        resetViewChanges(); 
		}
	}
	
    /**
     * Executed when user presses a key in a field with MVC-name
     * "customer.name".
     */
    public void customerNameKeyReleased() {
        logger.info("Customer name key released.");
    }
    
    /**
     * Executed when user has changed data in "customer.name" field.
     */
    public void customerNameChanged() {
    	logger.info("Customer name changed");
    }
    
    /**
     * Executed when user has changed any field in a view. 
     */
    public void viewChanged(YIModelComponent comp) {
    	// enabling buttonSave:
    	//customerView.setEditable(true, true);
    }
    
    /**
     * This method is executed if YModel throws an exception 
     * (when the framework copies data to the model).
     * 
     * @see Customer#setId(Long)
     */
    public void modelSetterException(Throwable ex, YModelSetValueException detailedException) {
    	JOptionPane.showOptionDialog(
	                YUIToolkit.findParentWindow((Component)this.getView()),
					ex.getMessage(),
	                "Error",
	                JOptionPane.DEFAULT_OPTION,
	                JOptionPane.ERROR_MESSAGE,
	                null,
	                new String[] {"Ok"},
	                "Ok");
    }
}
