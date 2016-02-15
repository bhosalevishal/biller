package poc;

import java.text.DecimalFormat;

public class DecimalFormatExample {

	public static void main(String[] args) {
		//formatting numbers upto 2 decimal places in Java
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        System.out.println(df.format(400000));
        
	}
}
