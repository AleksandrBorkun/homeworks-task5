package epam.homework.task5.service;

import epam.homework.task5.service.exception.ServiceException;

public interface NoteBookService {

	boolean findNotesByDate(String dateKey, int userID) throws ServiceException;

	void loadNoteBookFromFile(String loadFileName) throws ServiceException;

	// void createNewNoteBook(int userID);

	// void saveNoteBookToFile(String saveFileName) throws ServiceException;

	// void addNote(String note, int userId) throws ServiceException;

	// List<Note> findNotesByContent(String keyWords) throws ServiceException;

}
