package epam.homework.task5.bean;

public class AddNoteRequest extends Request {
	private String note;
	private String fileName;
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	
}
