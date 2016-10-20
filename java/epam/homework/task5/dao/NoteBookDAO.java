package epam.homework.task5.dao;

import java.util.List;

import epam.homework.task5.bean.entity.Note;

public interface NoteBookDAO {
	
	boolean addNote(Note note, int userID);

	boolean createNewNoteBook(int userID);

	boolean findNoteByContent(String keyWord, int userID);

	void findNotesByDate(String dateKey, int userID);

	void showAllNotes(int userID);

	void saveNoteBook(int userID);

	void loadNotesFromFile(List<Note> note);
	
	

}
