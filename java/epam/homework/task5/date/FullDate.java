package epam.homework.task5.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FullDate{

	private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat time = new SimpleDateFormat("hh-mm-ss");

	private String fullDate = time.format(new Date()) + " " + date.format(new Date());

	public String getFullDate() {
		return fullDate;
	}
}
