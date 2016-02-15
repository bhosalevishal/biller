package org.vb.biller.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.tikeswing.core.component.field.YDateFormatter;

public class DateFormatter {
	
	public static String getFormattedDate(String format, Date dateToFormat){
		
		String formattedDate = null;
		YDateFormatter dateFormatter = new YDateFormatter();
    	dateFormatter.setFormat(new SimpleDateFormat(format));
    	
    	try {
    		formattedDate = dateFormatter.valueToString(new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	return formattedDate;
	}

}
