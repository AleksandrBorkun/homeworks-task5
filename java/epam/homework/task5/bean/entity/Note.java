package epam.homework.task5.bean.entity;

import epam.homework.task5.date.FullDate;

public class Note{

	FullDate date = new FullDate();

	private String note;

	public Note(String note) {

		this.note = note;

	}

	public String getNote() {
		return note;
	}

	public String getDate() {

		return date.getFullDate();
	}

}
