
package org.vb.biller.view;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

import org.tikeswing.core.component.YButton;
import org.tikeswing.core.component.YPanel;
import org.tikeswing.core.component.field.YFormattedTextField;
import org.tikeswing.core.component.label.YLabel;
import org.tikeswing.core.component.table.YColumn;
import org.tikeswing.core.component.table.YTable;
import org.tikeswing.core.tools.YUIToolkit;
import org.vb.biller.bean.Customer;

/**
 * View for finding customers. Includes search criteria fields 
 * and customer table.
 *
 * @author Tomi Tuomainen
 */
public class FindCustomerView extends YPanel {
	    
	private YLabel labelName = new YLabel ("Customer Name");
	
	private YFormattedTextField fieldName = new YFormattedTextField();
	
    // column meta data for a table:
	private YColumn[] columns = {
                new YColumn("id", "Id", 10, false),
                new YColumn("name", "Name", 10, false)};
	
	// search result table:
    private YTable tableCustomers = new YTable(columns);
    
	// scrollpane for the table:
    private JScrollPane panelCustomers = new JScrollPane(tableCustomers);
    
    private YButton buttonFind = new YButton("Find");
    
    // this panel just for grouping:
    private YPanel panelFilter = new YPanel();
    
    public FindCustomerView () {
        setMVCNames();
        addComponents();
        initComponents();
        // using automatic view components registration (via reflection): 
		YUIToolkit.guessViewComponents(this);
    }
    
    /**
     * Setting names that are used in YController and YModel.
     */
    private void setMVCNames() {
        // setting names for mapping, must match field names in FindCustomerModel:
        fieldName.setMvcName("name");
        tableCustomers.setMvcName("customers");
        // this is just for controller events:
        buttonFind.setMvcName("buttonFind");
         
    }
    
    /**
     * Adding components to the panel.
     */
    private void addComponents() {
    	this.setLayout(new BorderLayout());
    	this.add(panelCustomers, BorderLayout.CENTER);
    	
        // using YPanel "layout manager" that encapsulates GridBagLayout...
    	this.add(panelFilter, BorderLayout.NORTH);
        JComponent[][] comps = {
                { labelName, fieldName },
                { buttonFind }
        } ;
        int[][] widths = {
                { 3, 5 },
                { 2 }};
        panelFilter.addComponents(comps, widths, 8);
    }
    
    /**
     * Setting initial state of components...
     +*/
    private void initComponents() {
    	this.setPreferredSize(new Dimension(300, -1));

    }

	public void setSelectedCustomer(Customer customer) {
		tableCustomers.setSelectedRow(customer);
	}
    
}
