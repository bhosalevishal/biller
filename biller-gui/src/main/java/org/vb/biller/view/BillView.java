/*
 * Created on 23.12.2004
 *
 */
package org.vb.biller.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.table.TableCellEditor;

import org.tikeswing.core.component.YButton;
import org.tikeswing.core.component.YPanel;
import org.tikeswing.core.component.field.YDoubleFormatter;
import org.tikeswing.core.component.field.YFormattedTextField;
import org.tikeswing.core.component.field.YIntegerFormatter;
import org.tikeswing.core.component.label.YLabel;
import org.tikeswing.core.component.list.YComboBox;
import org.tikeswing.core.component.table.YColumn;
import org.tikeswing.core.component.table.YComboBoxEditor;
import org.tikeswing.core.component.table.YFormattedFieldEditor;
import org.tikeswing.core.component.table.YFormattedFieldRenderer;
import org.tikeswing.core.component.table.YTable;
import org.tikeswing.core.tools.YUIToolkit;
import org.vb.biller.util.ModeOfWork;


/**
 * View showing Job data of selected customer. 
 * Includes editable table for data and button Save.
 * 
 * @author Tomi Tuomainen
 */
public class BillView extends YPanel {
	
	private YLabel labelCustomer = new YLabel ("Customer Name");
	private YLabel labelTotal = new YLabel ("Total");
	private YLabel fieldCustomer = new YLabel();
	private YLabel fieldBillTotal = new YLabel(" ");
	private YLabel fieldSBillTotalWords = new YLabel(" ");
	
	// column meta data for the table:
		private YColumn[] columns = { 
				new YColumn("name", "Name", 10, false),
				new YColumn("modeOfWork", "Mode of Work", 10, true),
				new YColumn("pages", "Pages", 10, true),
				new YColumn("rate", "Rate", 10, true),
				new YColumn("amount", "Amount", 10, false)
			};
		
	YComboBox editorCombo = new YComboBox();
    
    // search result table:
    private YTable tableBills = new YTable(columns){
    	public TableCellEditor getCellEditor(int row,int column) {
    		if (column == 1) {
    			return new YComboBoxEditor(editorCombo);
    		} else  {
    			return super.getCellEditor(row, column);
    		}
    	}
    };
    
    // adding table in a scrollpane:
    private JScrollPane panelBills = new JScrollPane(tableBills);
    
    // this panel just for grouping:
    private YPanel panelCreatePDF = new YPanel();
    private YPanel panelFilter = new YPanel();
    
    private YButton buttonCreatePDF = new YButton("Create PDF");
    
  
    public BillView() {
        super();
        setMVCNames();
        addComponents();
        initTable();
        
        List<ModeOfWork> comboModel = new ArrayList<ModeOfWork>();
        comboModel.add(ModeOfWork.NEW);
        comboModel.add(ModeOfWork.MODIFIED);
        comboModel.add(ModeOfWork.DIGITAL);
        
        editorCombo.setComboModel(comboModel);

        // using automatic view components registration (via reflection): 
        YUIToolkit.guessViewComponents(this);
    }
    
    /**
     * Setting names that are used in YController and YModel.
     */
    private void setMVCNames() {
        // JobModel has a collection named "jobs":
        tableBills.setMvcName("bills");
        
        fieldCustomer.setMvcName("customer.name");
        fieldBillTotal.setMvcName("billtotalnumbers");
        fieldSBillTotalWords.setMvcName("billtotalwords");
        
        // this is for controller events:
        buttonCreatePDF.setMvcName("buttonCreatePDF");
    }
    
    /**
     * Adding components to the panel.
     */
    private void addComponents() {
        this.add(panelFilter, BorderLayout.NORTH);
        JComponent[][] filterComps = {
                { labelCustomer, fieldCustomer }
        };
        
        int[][] filterWidths = {
                { 1, 6 },
                { 2 , 5}
                };
        panelFilter.addComponents(filterComps, filterWidths, 8);

        
        this.setLayout(new BorderLayout());
        this.add(panelBills, BorderLayout.CENTER);
        
        
        this.add(panelCreatePDF, BorderLayout.SOUTH);
        JComponent[][] pdfComps = {
                { labelTotal, fieldBillTotal, fieldSBillTotalWords },
                { buttonCreatePDF }
        };
        
        int[][] pdfWidths = {
                { 2 , 3 , 3 },
                { 1 , 4 , 3 }
                };
        panelCreatePDF.addComponents(pdfComps, pdfWidths, 8);
    }
    
    /**
     * This method demonstrates additional behaviour for editable
     * columns. 
     */
    private void initTable() {
    	panelBills.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	
    	tableBills.setRowHeight(25);
    	
    	Dimension dimension = new Dimension(2000, 15);	
    	fieldCustomer.setPreferredSize(dimension);
    	fieldBillTotal.setPreferredSize(dimension);
    	
    	// DOUBLE
    	//  setting editor:
    	YDoubleFormatter doubleFormatter = new YDoubleFormatter();
    	doubleFormatter.setMinimum(new Double(1)); // minimum editor value
    	tableBills.setDefaultEditor(Double.class, 
    			new YFormattedFieldEditor(
    					new YFormattedTextField(doubleFormatter)));
    	// setting renderer:
    	tableBills.setDefaultRenderer(Double.class,
    			new YFormattedFieldRenderer(
    					new YFormattedTextField(doubleFormatter)));
    	
    	// INTEGER
    	//  setting editor:
    	YIntegerFormatter integerFormatter = new YIntegerFormatter();
    	integerFormatter.setMinimum(new Integer(1)); // minimum editor value
    	tableBills.setDefaultEditor(Integer.class, 
    			new YFormattedFieldEditor(
    					new YFormattedTextField(integerFormatter)));
    	// setting renderer:
    	tableBills.setDefaultRenderer(Double.class,
    			new YFormattedFieldRenderer(
    					new YFormattedTextField(integerFormatter)));
    }
    
    public YTable getTableBills() {
        return tableBills;
    }
}
