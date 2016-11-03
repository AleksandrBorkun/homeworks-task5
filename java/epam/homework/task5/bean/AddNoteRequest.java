package epam.homework.task5.bean;

import epam.homework.task5.bean.entity.Note;

public class AddNoteRequest extends Request {
	private String note;
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Note getNote() {
		Note notes = new Note(note);
		return notes;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
