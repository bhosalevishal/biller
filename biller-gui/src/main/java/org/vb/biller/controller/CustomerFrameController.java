package org.vb.biller.controller;

import java.awt.Component;

import org.tikeswing.core.YController;
import org.vb.biller.view.MainFrame;

/**
 * The controller for CustomerFrame. Since CustomerFrame does not hold
 * any data directly, YModel is not used. 
 * 
 * @author Tomi Tuomainen
 */
public class CustomerFrameController extends YController {
	
	private MainFrame customerFrame = new MainFrame();
	
	// child controllers:
	private FindCustomerController findCustomerController = new FindCustomerController();
	private CustomerController customerController = new CustomerController();
    private JobController jobController = new JobController();
    private BillController billController = new BillController();
     
	public CustomerFrameController() {
		this.setUpMVC(null, customerFrame); // model is null since CustomerFrame doesn't hold any data itself
		
		// adding views from child controllers to the frame:
        customerFrame.addComponents(
                (Component) findCustomerController.getView(),
                (Component) customerController.getView(),
                (Component) jobController.getView(),
                (Component) billController.getView()
        		);
		addChildren();
		customerFrame.setVisible(true);		
	}
	
	/**
	 * Adding child controllers. This creates hierarchical structure
	 * for controllers, which could be used for implementing chain of 
	 * responsibility pattern.
	 */
	private void addChildren() {
		this.addChild(findCustomerController);
		this.addChild(customerController);
		this.addChild(jobController);
		this.addChild(billController);
	}
	
	/**
	 * This method is executed when user closes the frame.
	 */
	public void customerFrameClosing() {
		  System.exit(0);
	}

}
