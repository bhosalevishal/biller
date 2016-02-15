package org.vb.biller.controller;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;
import org.tikeswing.core.YApplicationEvent;
import org.tikeswing.core.YController;
import org.tikeswing.core.YIModelComponent;
import org.tikeswing.core.component.table.YTableChangeData;
import org.tikeswing.core.error.YErrorManager;
import org.tikeswing.core.error.YModelSetValueException;
import org.tikeswing.core.tools.YUIToolkit;
import org.tikeswing.core.worker.YWorker;
import org.tikeswing.core.worker.YWorkerCommand;
import org.vb.biller.bean.Customer;
import org.vb.biller.bean.Job;
import org.vb.biller.model.JobModel;
import org.vb.biller.util.BillerConstants;
import org.vb.biller.view.JobView;

/**
 * The controller for JobView and JobModel.
 * 
 * @author Tomi Tuomainen
 */
public class JobController extends YController {

	private static Logger logger = Logger.getLogger(JobController.class);
	
	private JobView jobView = new JobView();
	private JobModel jobModel = new JobModel();
	
    public JobController() {
    	this.setUpMVC(jobModel, jobView);
    	jobView.initTypeList();
    	
    	// registering for listening to CUSTOMER_CHANGED event
    	this.register(BillerConstants.CUSTOMER_CHANGED);
    	
    	this.register(BillerConstants.JOBS_CHANGED);
    }
    
    /**
     * Handling CUSTOMER_CHANGED event (sent by FindCustomerController).
     */
    public void receivedApplicationEvent(YApplicationEvent ev) {
		if (ev.getName().equals(BillerConstants.CUSTOMER_CHANGED)) {
			Customer customer = (Customer)ev.getValue(); 
			
			// setting view model:
			jobModel.setCustomer(customer);
			
			// setting the view dirty for refreshView method
			this.setViewDirty(customer);
		}
	}
    
    
    /**
     * This method is executed, if view is dirty when tab of this view 
     * will be selected.
     */
    public void refreshView(Object refreshObject) {
    	logger.info("JobView: refresh");
    	Customer customer = (Customer) refreshObject;
    	if (customer == null) {
    		jobModel.setJobs(null);
    	} else {
    		jobModel.findJobs();
    	}
        // resetting view for unsaved changes tracking:
    	resetViewChanges();
    }
    
    /**
     * Executed when "buttonFind" is pressed.
     */
	public void buttonFindPressed() {
		logger.info("JobView: buttonFindPressed()");
		
		// executing remote call outside event dispatching thread, so that GUI
		// doesn't "freeze":
		YWorkerCommand command = new YWorkerCommand() {
			public Object execute() {
				jobModel.findJobs();
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
     * Executed when "buttonFind" is pressed.
     */
    public void buttonGenerateBillPressed() {
    	logger.info("JobView: buttonGenerateBillPressed()");
    	//jobModel.getJobsForBill();
    }
    
    /**
     * Executed when used edits data in a table named "jobs".
     * @param event     the change event
     */
    public void jobsChanged(YTableChangeData data) {
    	logger.info("jobsChanged(YTableChangeData)");
    	logger.info(data.getColumn() + "," + data.getRow() + "," + data.getChangeObject());
    	
    	TableModel model = jobView.getTableJobs().getModel();
    	Integer jobId = (Integer) model.getValueAt(data.getRow(), 1);
    	jobModel.findJobById(jobId);
    	
    	if((Boolean) data.getChangeObject() == true){
    		jobModel.getJob().setValid(new Boolean(true));
    		jobModel.updateJob();
    	} else if((Boolean) data.getChangeObject() == false){
    		jobModel.getJob().setValid(new Boolean(false));
    		jobModel.updateJob();
    	}
    	
    	sendApplicationEvent(new YApplicationEvent(
    			BillerConstants.JOBS_CHANGED, jobModel.getJob()));
    }
    
    /**
    * This method is executed if YModel throws an exception 
     * (when the framework copies data to the model).
  * 
     * @see Job#setNumber(Long)
     */
    public void modelSetterException(Throwable ex, YModelSetValueException detailedException) {
    	    	JOptionPane.showOptionDialog(
	                YUIToolkit.getCurrentWindow(),
	                ex.getMessage(),
	                "Error",
	                JOptionPane.DEFAULT_OPTION,
	                JOptionPane.ERROR_MESSAGE,
	                null,
	                new String[] {"Ok"},
	                "Ok");
    }

    /**
     * Executed when user has changed any field in a view. 
     */
    public void viewChanged(YIModelComponent comp) {
    	// enabling buttonSave:
    	//jobView.setEditable(true, true);
    	
    	System.out.println(comp);
    }
    
}    
    
    
    
    

