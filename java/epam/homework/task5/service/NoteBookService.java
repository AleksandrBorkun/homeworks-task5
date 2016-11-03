package epam.homework.task5.service;

import java.util.List;

import epam.homework.task5.bean.entity.Note;
import epam.homework.task5.service.exception.ServiceException;

public interface NoteBookService {

	List<String> findNotesByDate(String dateKey, int userID) throws ServiceException;

	boolean loadNoteBookFromFile(String loadFileName) throws ServiceException;

	public List<String> findNoteByContent(String keyWord, int userID) throws ServiceException;

	public boolean createNewNoteBook(int userID) throws ServiceException;

	public List<String> showAllNotes(int userID) throws ServiceException;

	public boolean saveNoteBook(int userID) throws ServiceException;
	
	public boolean addNote(Note note, int userID) throws ServiceException;
	

}
