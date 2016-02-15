/*
 * Created on 22.12.2004
 *
 */
package org.vb.biller.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;

import org.tikeswing.core.YIComponent;
import org.tikeswing.core.component.YFrame;
import org.tikeswing.core.component.YPanel;
import org.tikeswing.core.component.YTabbedPane;


/**
 * The customer application main frame.
 * 
 * @see FrameCustomerController
 * @author Tomi Tuomainen
 */
public class MainFrame extends YFrame {
	
	private YTabbedPane tabbedPaneCustomer = new YTabbedPane();
	private YPanel basePanel = new YPanel();
    
    @SuppressWarnings("unchecked")
	public MainFrame() {
    	setMVCNames();
    	
    	// maximum height and width for frame
    	this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    	
    	
        // enabling unsaved changes checking for the frame:
        this.getYProperty().put(YIComponent.CHECK_CHANGES, new Boolean(true));
        // enabling unsaved changes checking for the tabbed pane:
        tabbedPaneCustomer.getYProperty().put(YIComponent.CHECK_CHANGES, new Boolean(true));
        
   }
    
    /**
     * Setting names that are used in YController and YModel.
     */
    private void setMVCNames() {
        // setting name which is used in controller event methods...
        this.getYProperty().put(YIComponent.MVC_NAME, "customerFrame");
    }
    
 
    /**
     * Adds child views to this frame (this is called from
     * CustomerFrameController).
     */
    public void addComponents(Component findView, 
            Component customerView, Component jobView, Component billView) {
    	
         basePanel.setLayout(new BorderLayout());
         this.setContentPane(basePanel);

         tabbedPaneCustomer.addTab("Customer", customerView);
         
         tabbedPaneCustomer.addTab("Job", jobView);
         
         tabbedPaneCustomer.addTab("Bill", billView);
         
         basePanel.add(tabbedPaneCustomer, BorderLayout.CENTER);
         basePanel.add(findView, BorderLayout.WEST);
    }
       
}
