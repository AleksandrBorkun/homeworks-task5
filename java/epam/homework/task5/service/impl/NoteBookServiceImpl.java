package epam.homework.task5.service.impl;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import epam.homework.task5.bean.entity.Note;
import epam.homework.task5.bean.entity.NoteBook;
import epam.homework.task5.dao.factory.DAOFactory;
import epam.homework.task5.date.DateChekFormat;
import epam.homework.task5.service.NoteBookService;
import epam.homework.task5.service.exception.ServiceException;
import epam.homework.task5.source.NoteBookProvider;

public class NoteBookServiceImpl implements NoteBookService {

	public void addNote(String note, int userID) throws ServiceException {

		// parameters validation
		if (note == null || "".equals(note)) {
			throw new ServiceException("Wrong parameter!");
		}

		DAOFactory.getInstance().getNoteBookDAO().addNote(new Note(note), userID);

		
		 Note newNote = new Note(note);
		 
		 NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
		 noteBook.add(newNote);
		
	}

	@Override
	public void createNewNoteBook(int userID) {

		DAOFactory.getInstance().getNoteBookDAO().createNewNoteBook(userID);
		
//		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
//		noteBook.clearNoteBook();

	}

	public List<Note> findNotesByContent(String keyWords) throws ServiceException {

		// parameters validation

		if (keyWords == null || "".equals(keyWords)) {

			throw new ServiceException("Wrong parameter!");

		}

		List<Note> foundNotes = new ArrayList<Note>();

		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();

		foundNotes = noteBook.FindNotesByContent(keyWords);
		if (foundNotes.isEmpty())
			System.out.println("There is nothing found by yor request");
		else
			System.out.println("\nHere is found notes:\n");
		return foundNotes;

	}

	@Override

	public boolean findNotesByDate(String dateKey, int userID) throws ServiceException {

		// parameters validation

		if (!DateChekFormat.isValid(dateKey)) {
			throw new ServiceException(
					"WRONG FORMAT" + dateKey + "\nPlease write date in correct format. \'yyyy-MM-dd\' ");
		}

	DAOFactory.getInstance().getNoteBookDAO().findNotesByDate(dateKey, userID);
/*
		if (!foundNotes.isEmpty()) {
			System.out.println("\nTake a result of search:\n");
		} else {
			System.out.println("\nSorry, but we found nothing by your date key\n");
		}
		*/
		return false;

	}

	@Override

	public void loadNoteBookFromFile(String loadFileName) throws ServiceException {

		try {

			FileInputStream loadFile = new FileInputStream(loadFileName);

			ObjectInputStream uncoding = new ObjectInputStream(loadFile);

			NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();

			while (loadFile.available() > 0) {

				Note currentNote = (Note) uncoding.readObject();
				noteBook.add(currentNote);

			}

			uncoding.close();

			loadFile.close();

		} catch (EOFException e) {

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

	}

	@Override

	public void saveNoteBookToFile(String fileLocation) throws ServiceException {

		try {

			FileOutputStream saveFile = new FileOutputStream(fileLocation);

			ObjectOutputStream codingSaveFile = new ObjectOutputStream(saveFile);

			NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();

			for (Note note : noteBook.getNotes()) {

				codingSaveFile.writeObject(note);

				codingSaveFile.reset();

			}

			codingSaveFile.close();

			saveFile.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
