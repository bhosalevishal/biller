package org.vb.biller.util;

import java.text.DecimalFormat;

public class DecimalFormatter {

	public static String formatDecimal(long number2format){
		//formatting numbers upto 2 decimal places in Java
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        return df.format(number2format);
	}
}
