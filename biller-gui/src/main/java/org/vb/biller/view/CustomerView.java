/*
 * Created on 22.12.2004
 *
 */
package org.vb.biller.view;


import java.awt.Dimension;

import javax.swing.JComponent;

import org.tikeswing.core.YIComponent;
import org.tikeswing.core.component.YPanel;
import org.tikeswing.core.component.label.YLabel;
import org.tikeswing.core.tools.YUIToolkit;


/**
 * View showing basic data of selected customer. Includes fields and
 * buttons Save, New and Delete. 
 * 
 * @author Tomi Tuomainen
 */
public class CustomerView extends YPanel  {
    
    private YLabel labelId = new YLabel("Id");
    private YLabel labelName = new YLabel ("Name");
    private YLabel labelPath = new YLabel("Path");
    
    // view model "id" is Long, so YIntegerFormatter must be used:
    private YLabel fieldId = new YLabel();
    private YLabel fieldName = new YLabel();
    private YLabel fieldPath = new YLabel();
    
    public CustomerView() {
    	setMVCNames();
        addComponents();
        initComponents();
        setName("CustomerView");
        
        // registering components
        YUIToolkit.guessViewComponents(this);
        
    }
    
    /**
     * Setting names that are used in YController and YModel.
     */
   private void setMVCNames() {
        // setting names for component binding, must match field names in CustomerModel:
        fieldId.setMvcName("customer.id");
        fieldName.setMvcName("customer.name");
        fieldPath.setMvcName("customer.path");
    }
    
    /**
     * Adding components to the panel.
     *
     */
    private void addComponents() {
        // using YPanel "layout manager" that encapsulates GridBagLayout
    	JComponent[][] comps = {
                { labelId, fieldId },
                { labelName, fieldName },
                { labelPath, fieldPath }
        } ;
    	
        int[][] widths = {
                { 1, 6 },
                { 1, 6 },
                { 1, 6 }
			} ;
        
        addComponents(comps, widths, 8);
    }
    
    /**
     * Setting initial state of components.
     */
    private void initComponents() {
    	Dimension dimension = new Dimension(500, 500);	
    	
    	fieldId.setPreferredSize(dimension);
    	fieldName.setPreferredSize(dimension);
    	fieldPath.setPreferredSize(dimension);
    }
}
