/*
 * Created on Dec 18, 2004
 *
 */
package org.vb.biller.controller;

import org.tikeswing.core.YApplicationEvent;
import org.tikeswing.core.YController;
import org.tikeswing.core.error.YErrorManager;
import org.tikeswing.core.worker.YWorker;
import org.tikeswing.core.worker.YWorkerCommand;
import org.vb.biller.model.FindCustomerModel;
import org.vb.biller.util.BillerConstants;
import org.vb.biller.view.FindCustomerView;


/**
 * The controller for FindCustomerView and FindCustomerModel. 
 *
 *@author Tomi Tuomainen
 */
public class FindCustomerController extends YController {
    	
	private FindCustomerView findView = new FindCustomerView();
	private FindCustomerModel findModel = new FindCustomerModel();

	public FindCustomerController() {
		super();
		this.setUpMVC(findModel, findView);
	}
	
    /**
     * Executed when "buttonFind" is pressed.
     */
	public void buttonFindPressed() {
		// executing remote call outside event dispatching thread, so that GUI
		// doesn't "freeze":
		YWorkerCommand command = new YWorkerCommand() {
			public Object execute() {
				findModel.findCustomers();
				return null;
			}
		};
		try {
			new YWorker().execute(command, "Please wait...");
		} catch (Exception ex) {
			// exception occurred during model.save()
			YErrorManager.handleException(ex);
		}
		// updating view state, no unsaved changes:
		resetViewChanges();
	}
    
    /**
     * Executed when selection in "customers" table is changed. 
     */
    public void customersSelectionChanged(Object selection) {
    	sendApplicationEvent(new YApplicationEvent(
    			BillerConstants.CUSTOMER_CHANGED, selection));
    }

}
