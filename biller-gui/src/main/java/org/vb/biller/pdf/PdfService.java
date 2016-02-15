package org.vb.biller.pdf;

import org.vb.biller.model.JobModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public interface PdfService {
	
	public void createHeader(Document document) throws DocumentException;
	
	public void createBillTo(Document document) throws DocumentException;
	
	public void createTableHeader(Document document) throws DocumentException;
	
	public void createTableDetail(Document document) throws DocumentException;
	
	public void createTableTotal(Document document) throws DocumentException;
	
	public void createTotalInWords(Document document) throws DocumentException;
	
	public void createFooter(Document document) throws DocumentException;

}
