package org.vb.biller.pdf;

import java.util.Date;

import org.vb.biller.bean.Job;
import org.vb.biller.model.JobModel;
import org.vb.biller.util.DateFormatter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.vb.biller.property.PropertyReader;

public class PdfServiceImpl extends PdfPageEventHelper implements PdfService {
	
	private final Font fontBold16 = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
	private final Font fontNormal10 = new Font(FontFactory.getFont(FontFactory.HELVETICA, 10));
	private final Font fontBold10 =  new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10));
	private final Font fontBold14 = new Font(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
	
	private PropertyReader property = new PropertyReader("customer.properties");
	
	private PdfPTable invoiceDetails;
	private JobModel jobModel;
	
	public PdfServiceImpl(JobModel jobModel) {
		this.jobModel = jobModel;
	}
	
	public void createHeader(Document document) throws DocumentException {
		float[] columnWidths = {5f, 5f};
        PdfPTable companyInvoiceDate = new PdfPTable(columnWidths);
        // set table width a percentage of the page width
        companyInvoiceDate.setWidthPercentage(100f);
        
        PdfPTable tableCompanyDetails = new PdfPTable(1);
        PdfPTable tableInvoiceAndDate = new PdfPTable(1);
        
        companyInvoiceDate.getDefaultCell().setBorderWidth(0f);
        tableCompanyDetails.getDefaultCell().setBorderWidth(0f);
        tableInvoiceAndDate.getDefaultCell().setBorderWidth(10f);
        
        tableCompanyDetails.addCell(new Paragraph(property.readPrperty("customer.company"), fontBold16));
        tableCompanyDetails.addCell(new Paragraph(property.readPrperty("customer.city"), fontBold14));
        companyInvoiceDate.addCell(tableCompanyDetails);
        
        PdfPCell cellInvoice = new PdfPCell(new Paragraph("INVOICE", fontBold16));
        cellInvoice.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellInvoice.setBorder(Rectangle.NO_BORDER);
        tableInvoiceAndDate.addCell(cellInvoice);
        
        String billDate = DateFormatter.getFormattedDate("dd-MMMM-yyyy", new Date());
        PdfPCell cellDate = new PdfPCell(new Paragraph(billDate, fontNormal10));
        cellDate.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellDate.setBorder(Rectangle.NO_BORDER);
        tableInvoiceAndDate.addCell(cellDate); 
        companyInvoiceDate.addCell(tableInvoiceAndDate);
        
        companyInvoiceDate.completeRow();
        
        document.add(companyInvoiceDate);
        document.add(new Paragraph(" "));
	}

	public void createBillTo(Document document) throws DocumentException {
		float[] columnWidths1 = {5f};
        PdfPTable billTo = new PdfPTable(columnWidths1);
        billTo.setWidthPercentage(100f);
        
        billTo.getDefaultCell().setBorderWidth(0f);
    	
        billTo.addCell(new Paragraph("Bill To,", fontBold10));
        billTo.addCell(new Paragraph(this.jobModel.getCustomer().getName(), fontNormal10));
        billTo.addCell(new Paragraph(property.readPrperty("customer.city"), fontNormal10));
        billTo.addCell(new Paragraph(" "));
        billTo.addCell(new Paragraph("Dear " + this.jobModel.getCustomer().getName() + ","));
        billTo.addCell(new Paragraph("I am herewith submitting Bill. So do the needul as usual."));
        billTo.addCell(new Paragraph(" "));
        
        billTo.completeRow();
        document.add(billTo);
		
	}

	public void createTableHeader(Document document) throws DocumentException {
		float[] columnWidths = {1.5f, 15f, 4f, 3.5f, 2.5f, 3.5f, 3.5f};
        invoiceDetails = new PdfPTable(columnWidths);
        invoiceDetails.setHeaderRows(1);
        invoiceDetails.setWidthPercentage(100f);
        
        PdfPCell cellSr = new PdfPCell(new Paragraph("Sr.No.", fontBold10));
    	cellSr.setPaddingBottom(5);
    	cellSr.setBackgroundColor(new BaseColor(215,215,215));
    	cellSr.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cellSr.setBorderColor(BaseColor.GRAY);
    	
    	PdfPCell cellDescription = new PdfPCell(new Paragraph("Name of Work", fontBold10));
    	cellDescription.setBackgroundColor(new BaseColor(215,215,215));
    	cellDescription.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cellDescription.setBorderColor(BaseColor.GRAY);
    	
    	PdfPCell cellDate = new PdfPCell(new Paragraph("Date", fontBold10));
    	cellDate.setBackgroundColor(new BaseColor(215,215,215));
    	cellDate.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cellDate.setBorderColor(BaseColor.GRAY);
    	
    	PdfPCell cellType = new PdfPCell(new Paragraph("Mode of Work", fontBold10));
    	cellType.setBackgroundColor(new BaseColor(215,215,215));
    	cellType.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cellType.setBorderColor(BaseColor.GRAY);
    	
    	PdfPCell cellPages = new PdfPCell(new Paragraph("No. of Pages", fontBold10));
    	cellPages.setBackgroundColor(new BaseColor(215,215,215));
    	cellPages.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cellPages.setBorderColor(BaseColor.GRAY);
    	
    	PdfPCell cellRate = new PdfPCell(new Paragraph("Rate Per Copy", fontBold10));
    	cellRate.setBackgroundColor(new BaseColor(215,215,215));
    	cellRate.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cellRate.setBorderColor(BaseColor.GRAY);
    	
    	PdfPCell cellAmount = new PdfPCell(new Paragraph("Amount", fontBold10));
    	cellAmount.setBackgroundColor(new BaseColor(215,215,215));
    	cellAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cellAmount.setBorderColor(BaseColor.GRAY);
    	
    	invoiceDetails.addCell(cellSr);
    	invoiceDetails.addCell(cellDescription);
    	invoiceDetails.addCell(cellDate);
    	invoiceDetails.addCell(cellType);
    	invoiceDetails.addCell(cellPages);
    	invoiceDetails.addCell(cellRate);
    	invoiceDetails.addCell(cellAmount);
    	
    	invoiceDetails.completeRow();
    	
    	document.add(new Phrase(" "));
    	document.add(invoiceDetails);
	}
	
	public void createTableDetail(Document document) throws DocumentException {
		
		int srNo = 1;
		for(Job job : this.jobModel.getBills()) {
    		
    		PdfPCell cellSr = new PdfPCell(new Paragraph(Integer.toString(srNo), fontNormal10));
    		cellSr.setPaddingBottom(5);
    		cellSr.setBorderColor(BaseColor.GRAY);
    		cellSr.setHorizontalAlignment(Element.ALIGN_CENTER);
    		
    		PdfPCell cellDescription = new PdfPCell(new Paragraph(job.getName(), fontNormal10));
    		cellDescription.setPaddingBottom(5);
    		cellDescription.setBorderColor(BaseColor.GRAY);
    		cellDescription.setHorizontalAlignment(Element.ALIGN_LEFT);
    		
    		String jobDate = DateFormatter.getFormattedDate("dd/MM/yyyy", job.getModifyDate());
    		PdfPCell cellDate = new PdfPCell(new Paragraph(jobDate, fontNormal10));
    		cellDate.setPaddingBottom(5);
    		cellDate.setBorderColor(BaseColor.GRAY);
    		cellDate.setHorizontalAlignment(Element.ALIGN_CENTER);
    		
    		PdfPCell cellType = new PdfPCell(new Paragraph("NEW", fontNormal10));
    		cellType.setPaddingBottom(5);
    		cellType.setBorderColor(BaseColor.GRAY);
    		cellType.setHorizontalAlignment(Element.ALIGN_CENTER);
    		
    		PdfPCell cellPages = new PdfPCell(new Paragraph(job.getPages().toString(), fontNormal10));
    		cellPages.setPaddingBottom(5);
    		cellPages.setBorderColor(BaseColor.GRAY);
    		cellPages.setHorizontalAlignment(Element.ALIGN_RIGHT);
    		
    		PdfPCell cellRate = new PdfPCell(new Paragraph(job.getRate().toString(), fontNormal10));
    		cellRate.setPaddingBottom(5);
    		cellRate.setBorderColor(BaseColor.GRAY);
    		cellRate.setHorizontalAlignment(Element.ALIGN_RIGHT);
    		
    		PdfPCell cellAmount = new PdfPCell(new Paragraph(job.getAmount().toString(), fontNormal10));
    		cellAmount.setPaddingBottom(5);
    		cellAmount.setBorderColor(BaseColor.GRAY);
    		cellAmount.setHorizontalAlignment(Element.ALIGN_RIGHT);

    		invoiceDetails.addCell(cellSr);
    		invoiceDetails.addCell(cellDescription);
    		invoiceDetails.addCell(cellDate);
    		invoiceDetails.addCell(cellType);
    		invoiceDetails.addCell(cellPages);
    		invoiceDetails.addCell(cellRate);
    		invoiceDetails.addCell(cellAmount);
    		
    		invoiceDetails.completeRow();
    		
    		srNo++;
    		
    	}
	}

	public void createTableTotal(Document document) throws DocumentException {
    	PdfPCell cellEmpty = new PdfPCell(new Paragraph(" ", fontNormal10));
    	cellEmpty.setColspan(3);
    	cellEmpty.setBorder(Rectangle.NO_BORDER);
    	invoiceDetails.addCell(cellEmpty);
    	
    	PdfPCell cellTotal = new PdfPCell(new Paragraph("TOTAL", fontBold14));
		cellTotal.setPaddingBottom(5);
		cellTotal.setColspan(2);
		cellTotal.setBorderColor(BaseColor.GRAY);
		cellTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellTotal.setBackgroundColor(new BaseColor(215,215,215));
		invoiceDetails.addCell(cellTotal);
		
		PdfPCell cellTotalAmount = new PdfPCell(new Paragraph(this.jobModel.getBilltotalnumbers(), fontBold14));
		cellTotalAmount.setPaddingBottom(5);
		cellTotalAmount.setColspan(3);
		cellTotalAmount.setBorderColor(BaseColor.GRAY);
		cellTotalAmount.setHorizontalAlignment(Element.ALIGN_RIGHT);
		invoiceDetails.addCell(cellTotalAmount);
		
		invoiceDetails.completeRow();
		
		document.add(invoiceDetails);
	}
	
	public void createTotalInWords(Document document) throws DocumentException {
		float[] columnWidths1 = {5f};
        PdfPTable totalInWords = new PdfPTable(columnWidths1);
        totalInWords.setWidthPercentage(100f);
        
        totalInWords.getDefaultCell().setBorderWidth(0f);
    	
        totalInWords.addCell(new Paragraph("Rs. "+ this.jobModel.getBilltotalwords(), fontBold10));
        
        document.add(new Phrase(" "));
        document.add(totalInWords);
	}
	
	public void createFooter(Document document) throws DocumentException {
		float[] columnWidths1 = {5f};
        PdfPTable thanks = new PdfPTable(columnWidths1);
        thanks.setWidthPercentage(100f);
        
        thanks.getDefaultCell().setBorderWidth(0f);
    	
        thanks.addCell(new Paragraph("Thanks & Regards", fontNormal10));
        thanks.addCell(new Paragraph("Yours Faithfully,", fontNormal10));
        thanks.addCell(new Paragraph(" "));
        thanks.addCell(new Paragraph("For " + property.readPrperty("customer.company"), fontBold10));
        thanks.addCell(new Paragraph(" "));
        thanks.addCell(new Paragraph(" "));
        thanks.addCell(new Paragraph(property.readPrperty("customer.name"), fontNormal10));
        
        document.add(new Phrase(" "));
        document.add(thanks);
		
	}
}
