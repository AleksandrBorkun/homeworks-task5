package epam.homework.task5.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import epam.homework.task5.bean.entity.Note;
import epam.homework.task5.bean.entity.NoteBook;
import epam.homework.task5.dao.exception.DAOException;
import epam.homework.task5.dao.factory.DAOFactory;
import epam.homework.task5.date.DateChekFormat;
import epam.homework.task5.service.NoteBookService;
import epam.homework.task5.service.exception.ServiceException;
import epam.homework.task5.source.NoteBookProvider;

public class NoteBookServiceImpl implements NoteBookService {

	// проверка валидности даты и передача управления слою ДАО
	@Override
	public List<String> findNotesByDate(String dateKey, int userID) throws ServiceException {

		// parameters validation

		if (!DateChekFormat.isValid(dateKey)) {
			throw new ServiceException(
					"WRONG FORMAT" + dateKey + "\nPlease write date in correct format. \'yyyy-MM-dd\' ");
		}

		List<String> findNotesByDateLsit = new ArrayList<>();

		// после проверки даты на валидность передаем ее слою ДАО на выполнение
		try {
			findNotesByDateLsit = DAOFactory.getInstance().getNoteBookDAO().findNotesByDate(dateKey, userID);
			if (findNotesByDateLsit == null) {
				throw new ServiceException("Nothing was found");
			}
			return findNotesByDateLsit;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	// считываем информацию из файла и передаем в notebook
	@Override
	public boolean loadNoteBookFromFile(String loadFileName) throws ServiceException {

		loadFileName = loadFileName.trim();
		if (loadFileName == null || loadFileName.length() < 1) {
			throw new ServiceException("ERROR! Wrong name!");
		}
		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
		noteBook.clearNoteBook();

		File loadFile = new File("./");
		String[] find = loadFile.list();
		int count = 0;
		for (String searchName : find) {
			if (searchName.equals(loadFileName)) {
				File f = new File(searchName);
				BufferedReader bf;
				try {
					bf = new BufferedReader(new FileReader(f));
					String line;
					while ((line = bf.readLine()) != null)
						noteBook.add(new Note(line));
					bf.close();
				} catch (FileNotFoundException e) {
					throw new ServiceException(e.getMessage());
				} catch (IOException e) {
					throw new ServiceException(e.getMessage());
				}
				count++;
			}
		}
		if (count == 0) {
			throw new ServiceException("File not found.");
		}

		// после успешной записи файла передаем ноутбук на выполнение в слой ДАО
		try {
			if (DAOFactory.getInstance().getNoteBookDAO().loadNotesFromFile(noteBook.getNotes())) {
				return true;
			} else {
				return false;
			}

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<String> findNoteByContent(String keyWord, int userID) throws ServiceException {

		keyWord = keyWord.trim();
		if (keyWord == null || keyWord.length() < 1 || userID == 0) {
			throw new ServiceException("Wrong data format. Pls try Again");
		}
		List<String> findNoteByContent = new ArrayList<>();

		try {
			findNoteByContent = DAOFactory.getInstance().getNoteBookDAO().findNoteByContent(keyWord, userID);
			if (findNoteByContent != null) {
				return findNoteByContent;
			} else {
				throw new ServiceException("Nothing was found");
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public boolean createNewNoteBook(int userID) throws ServiceException {

		if (userID <= 0) {
			throw new ServiceException("Wrong UserId Number");
		}

		try {
			if (DAOFactory.getInstance().getNoteBookDAO().createNewNoteBook(userID)) {
				return true;
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return false;

	}

	@Override
	public List<String> showAllNotes(int userID) throws ServiceException {

		if (userID <= 0) {
			throw new ServiceException("ERROR! Wrong UserID!");
		}

		List<String> showNotesList = new ArrayList<>();

		try {
			showNotesList = DAOFactory.getInstance().getNoteBookDAO().showAllNotes(userID);
			if (showNotesList != null) {
				return showNotesList;
			} else {
				throw new ServiceException("ERROR! Notes not found try again");
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public boolean saveNoteBook(int userID) throws ServiceException {

		if (userID <= 0) {
			throw new ServiceException("Error!!! Wrong userID");
		}

		try {
			if (DAOFactory.getInstance().getNoteBookDAO().saveNoteBook(userID)) {
				return true;
			} else {
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public boolean addNote(Note note, int userID) throws ServiceException {

		if (note == null || userID <= 0) {
			throw new ServiceException("Error! Wrong data!!");
		}

		try {
			if (DAOFactory.getInstance().getNoteBookDAO().addNote(note, userID)) {
				return true;
			} else {
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

}
