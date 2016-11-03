package epam.homework.task5.bean;

import java.util.ArrayList;
import java.util.List;

public class Response {
	private boolean errorStatus;

	private String errorMessage;
	private String resultMessage;

	private List<String> findNoteByContent = new ArrayList<>();
	List<String> findNotesByDateLsit = new ArrayList<>();
	List<String> showNotesList = new ArrayList<>();
	
	public List<String> getShowNotesList() {
		return showNotesList;
	}

	public void setShowNotesList(List<String> showNotesList) {
		this.showNotesList = showNotesList;
	}

	public List<String> getFindNotesByDateLsit() {
		return findNotesByDateLsit;
	}

	public void setFindNotesByDateLsit(List<String> findNotesByDateLsit) {
		this.findNotesByDateLsit = findNotesByDateLsit;
	}

	public List<String> getFindNoteByContent() {
		return findNoteByContent;
	}

	public void setFindNoteByContent(List<String> findNoteByContent) {
		this.findNoteByContent = findNoteByContent;
	}

	public boolean isErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}
