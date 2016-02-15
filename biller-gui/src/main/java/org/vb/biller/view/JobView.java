/*
 * Created on 23.12.2004
 *
 */
package org.vb.biller.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import org.tikeswing.core.component.YButton;
import org.tikeswing.core.component.YCheckBox;
import org.tikeswing.core.component.YPanel;
import org.tikeswing.core.component.field.YDateFormatter;
import org.tikeswing.core.component.field.YFormattedTextField;
import org.tikeswing.core.component.label.YLabel;
import org.tikeswing.core.component.list.YComboBox;
import org.tikeswing.core.component.table.YCheckBoxEditor;
import org.tikeswing.core.component.table.YCheckBoxRenderer;
import org.tikeswing.core.component.table.YColumn;
import org.tikeswing.core.component.table.YFormattedFieldRenderer;
import org.tikeswing.core.component.table.YTable;
import org.tikeswing.core.tools.YUIToolkit;


/**
 * View showing Job data of selected customer. 
 * Includes editable table for data and button Save.
 * 
 * @author Tomi Tuomainen
 */
public class JobView extends YPanel {
	
	private YLabel labelEmpty = new YLabel (" ");
	private YLabel labelName = new YLabel ("Job Name");
	private YLabel labelCustomer = new YLabel ("Customer Name");
	
	private YFormattedTextField fieldName = new YFormattedTextField();
	private YLabel fieldCustomer = new YLabel();
	
    // column meta data for the table:
	private YColumn[] columns = { 
			new YColumn("valid", "Valid", 10, true),
			new YColumn("id", "Id", 10, false),
			new YColumn("name", "Name", 10, false),
			new YColumn("createDate", "Create Date", 10, false),
			new YColumn("modifyDate", "Modify Date", 10, false)
		};
    
    // search result table:
    private YTable tableJobs = new YTable(columns);
    
    private YButton buttonFind = new YButton("Find");
    
    private YLabel labelType = new YLabel ("Type");
    private YComboBox comboType = new YComboBox();

    // adding table in a scrollpane:
    private JScrollPane panelJobs = new JScrollPane(tableJobs);
    
    // this panel just for grouping:
    private YPanel panelFilter = new YPanel();
    
    private YLabel labelFrom = new YLabel ("From");
    private UtilDateModel modelFrom = new UtilDateModel();
    JDatePanelImpl datePanelFrom = new JDatePanelImpl(modelFrom);
    JDatePickerImpl datePickerFrom = new JDatePickerImpl(datePanelFrom);
    
    private YLabel labelTo = new YLabel ("To");
    private UtilDateModel modelTo = new UtilDateModel();
    JDatePanelImpl datePanelTo = new JDatePanelImpl(modelTo);
    JDatePickerImpl datePickerTo = new JDatePickerImpl(datePanelTo);
     
    public JobView() {
        super();
        setMVCNames();
        addComponents();
        initTable();

        // using automatic view components registration (via reflection): 
        YUIToolkit.guessViewComponents(this);
    }
    
    /**
     * Setting names that are used in YController and YModel.
     */
    private void setMVCNames() {
        // JobModel has a collection named "jobs":
        tableJobs.setMvcName( "jobs");
       
        // setting names for mapping, must match field names in FindCustomerModel:
        fieldName.setMvcName("name");
        fieldCustomer.setMvcName("customer.name");
        
        // this is just for controller events:
        buttonFind.setMvcName("buttonFind");
    }
    
    /**
     * Adding components to the panel.
     */
    private void addComponents() {
        this.setLayout(new BorderLayout());
        this.add(panelJobs, BorderLayout.CENTER);
        
        this.add(panelFilter, BorderLayout.NORTH);
        JComponent[][] filterComps = {
                { labelCustomer, fieldCustomer },
                { labelName, fieldName },
                { labelFrom, datePickerFrom,},
                { labelTo , datePickerTo },
                { labelType, comboType },
                { labelEmpty, buttonFind }
        };
        
        int[][] filterWidths = {
                { 1 , 6 },
                { 1 , 6 },
                { 1 , 1 },
                { 1 , 1 },
                { 1 , 1 },
                { 1 , 1 }
        };
        panelFilter.addComponents(filterComps, filterWidths, 8);
    }
    
    /**
     * Initializes combo model.
     * @param types collection of Filter objects
     */
    public void initTypeList() {
        List newTypes = new ArrayList();
        newTypes.add("01 Created On");
        newTypes.add("02 Modified On");
    	 this.comboType.setComboModel(newTypes, null, false);
    	 this.comboType.setEditable(true);
    }
    
    /**
     * This method demonstrates additional behaviour for editable
     * columns. 
     */
    private void initTable() {
    	Dimension dimension = new Dimension(2000, 15);	
    	fieldCustomer.setPreferredSize(dimension);
    	
    	panelJobs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	
    	datePickerTo.setSize(100, 100);

    	//tableJobs.setRowHeight(25);
    	
    	// DATE
    	//  setting editor:
    	YDateFormatter dateFormatter = new YDateFormatter();
    	dateFormatter.setFormat(new SimpleDateFormat("dd/MM/yyyy"));
    	
    	// setting renderer:
    	tableJobs.setDefaultRenderer(Date.class,
    			new YFormattedFieldRenderer(
    					new YFormattedTextField(dateFormatter)));
    	
    	//  BOOLEAN
    	// setting editor
    	tableJobs.setDefaultEditor(Boolean.class, new YCheckBoxEditor(new YCheckBox()));
    	// setting renderer 
    	tableJobs.setDefaultRenderer(Boolean.class, new YCheckBoxRenderer(new YCheckBox()));

    }
    
    public YTable getTableJobs() {
        return tableJobs;
    }
    
}
