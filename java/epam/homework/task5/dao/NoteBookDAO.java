package epam.homework.task5.dao;

import java.util.List;

import epam.homework.task5.bean.entity.Note;
import epam.homework.task5.dao.exception.DAOException;

public interface NoteBookDAO {
	
	boolean addNote(Note note, int userID) throws DAOException;

	boolean createNewNoteBook(int userID) throws DAOException;

	List<String> findNoteByContent(String keyWord, int userID) throws DAOException;

	List<String> findNotesByDate(String dateKey, int userID) throws DAOException;

	List<String> showAllNotes(int userID) throws DAOException;

	boolean saveNoteBook(int userID) throws DAOException;

	boolean loadNotesFromFile(List<Note> note) throws DAOException;
	
	

}
