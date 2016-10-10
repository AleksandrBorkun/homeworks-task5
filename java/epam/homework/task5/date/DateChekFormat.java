package epam.homework.task5.date;

import java.util.regex.Pattern;

public class DateChekFormat {

	private final static Pattern pattern = Pattern
			.compile("^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$");

	public static boolean isValid(String date) {

		return pattern.matcher(date).find();

	}

}
