package org.vb.biller.pdf;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.vb.biller.model.JobModel;

import poc.HeaderFooterPageEvent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF {

	private static final int INCH = 72;
	private static final float MARGIN_TOP = INCH / 2;
	private static final float MARGIN_BOTTOM = INCH / 2;
	private static final Rectangle PAGE_SIZE = PageSize.A4;

	private String pdfFileName;

	private void generatePdfFileName(JobModel jobModel) {
		Date curDate = new Date();
		String fileName = jobModel.getCustomer().getName().replace(" ", "_");
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyhhmmss");
		String dateToStr = format.format(curDate);

		String finalFileName = "docs/".concat(fileName).concat("_")
				.concat(dateToStr).concat(".pdf");

		File file = new File(finalFileName);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		setPdfFileName(finalFileName);
	}

	private void openPdfFile() {
		File file = new File(this.pdfFileName);

		try {
			if (file.exists()) {
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(file);
				} else {
					System.out.println("Awt Desktop is not supported!");
				}
			} else {
				System.out.println("File is not exists!");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void generatePdf(JobModel jobModel) {
		
		// generate pdf file name
		generatePdfFileName(jobModel);
		
		PdfService pdfService = new PdfServiceImpl(jobModel);
		
		Document document = new Document(PAGE_SIZE, 50, 50, MARGIN_TOP,
				MARGIN_BOTTOM);


		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
					getPdfFileName()));
			
			HeaderFooterPageEvent event = new HeaderFooterPageEvent();
	        writer.setPageEvent(event);
			
			document.open();

			//pdfService.createHeader(document);
			pdfService.createBillTo(document);
			pdfService.createTableHeader(document);
			pdfService.createTableDetail(document);
			pdfService.createTableTotal(document);
			pdfService.createTotalInWords(document);
			//pdfService.createFooter(document);

			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// open pdf file for further actions like view / print
		openPdfFile();
	}
	
	
	// ---- Getters & Setters

	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}
}
