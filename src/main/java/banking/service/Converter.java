package banking.service;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author ±èº¸·É
 *
 */
public class Converter {
	/**
	 * yyyy-MM-dd hh:mm:ss
	 * @param date
	 * @return
	 */
	public String matchDateToFormat(Date date) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String outDate = format.format(date);
		
		return outDate;
	}
}
