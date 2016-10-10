package epam.homework.task5.service;

import java.util.List;

import epam.homework.task5.bean.entity.Note;
import epam.homework.task5.service.exception.ServiceException;;

public interface NoteBookService {

	void addNote(String note, int userId) throws ServiceException;

	List<Note> findNotesByContent(String keyWords) throws ServiceException;

	boolean findNotesByDate(String dateKey, int userID) throws ServiceException;

	void loadNoteBookFromFile(String loadFileName) throws ServiceException;

	void saveNoteBookToFile(String saveFileName) throws ServiceException;

	void createNewNoteBook(int userID);

}
