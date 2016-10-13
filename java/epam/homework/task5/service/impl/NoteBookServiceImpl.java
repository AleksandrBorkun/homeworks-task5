package epam.homework.task5.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import epam.homework.task5.bean.entity.Note;
import epam.homework.task5.bean.entity.NoteBook;
import epam.homework.task5.dao.factory.DAOFactory;
import epam.homework.task5.date.DateChekFormat;
import epam.homework.task5.service.NoteBookService;
import epam.homework.task5.service.exception.ServiceException;
import epam.homework.task5.source.NoteBookProvider;

public class NoteBookServiceImpl implements NoteBookService {

	// проверка валидности даты и передача управления слою ДАО
	@Override
	public boolean findNotesByDate(String dateKey, int userID) throws ServiceException {

		// parameters validation

		if (!DateChekFormat.isValid(dateKey)) {
			throw new ServiceException(
					"WRONG FORMAT" + dateKey + "\nPlease write date in correct format. \'yyyy-MM-dd\' ");
		}

		// после проверки даты на валидность передаем ее слою ДАО на выполнение
		DAOFactory.getInstance().getNoteBookDAO().findNotesByDate(dateKey, userID);

		return false;

	}

	// считываем информацию из файла и передаем в notebook
	@Override
	public void loadNoteBookFromFile(String loadFileName) throws ServiceException {

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
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					count++;
					}
				}
			if(count == 0){
				throw new ServiceException("File not found.");
			}

		// после успешной записи файла передаем ноутбук на выполнение в слой ДАО
		DAOFactory.getInstance().getNoteBookDAO().loadNotesFromFile(noteBook.getNotes());
	}
	}
	/*
	 * Данные методы не используеться в новой версии NoteBook
	 * 
	 * 
	 * @Override public void createNewNoteBook(int userID) {
	 * 
	 * DAOFactory.getInstance().getNoteBookDAO().createNewNoteBook(userID);
	 * 
	 * // NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook(); //
	 * noteBook.clearNoteBook();
	 * 
	 * }
	 * 
	 * public void addNote(String note, int userID) throws ServiceException {
	 * 
	 * // parameters validation if (note == null || "".equals(note)) { throw new
	 * ServiceException("Wrong parameter!"); }
	 * 
	 * Note newNote = new Note(note);
	 * 
	 * NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
	 * noteBook.add(newNote);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * public List<Note> findNotesByContent(String keyWords) throws
	 * ServiceException {
	 * 
	 * // parameters validation
	 * 
	 * if (keyWords == null || "".equals(keyWords)) {
	 * 
	 * throw new ServiceException("Wrong parameter!");
	 * 
	 * }
	 * 
	 * List<Note> foundNotes = new ArrayList<Note>();
	 * 
	 * NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
	 * 
	 * foundNotes = noteBook.FindNotesByContent(keyWords); if
	 * (foundNotes.isEmpty())
	 * System.out.println("There is nothing found by yor request"); else
	 * System.out.println("\nHere is found notes:\n"); return foundNotes;
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Override
	 * 
	 * public void saveNoteBookToFile(String fileLocation) throws
	 * ServiceException {
	 * 
	 * try {
	 * 
	 * FileOutputStream saveFile = new FileOutputStream(fileLocation);
	 * 
	 * ObjectOutputStream codingSaveFile = new ObjectOutputStream(saveFile);
	 * 
	 * NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
	 * 
	 * for (Note note : noteBook.getNotes()) {
	 * 
	 * codingSaveFile.writeObject(note);
	 * 
	 * codingSaveFile.reset();
	 * 
	 * }
	 * 
	 * codingSaveFile.close();
	 * 
	 * saveFile.close();
	 * 
	 * } catch (FileNotFoundException e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * } catch (IOException e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * }
	 */

