package epam.homework.task5.dao.factory;

import epam.homework.task5.dao.NoteBookDAO;
import epam.homework.task5.dao.UserDAO;
import epam.homework.task5.dao.impl.NoteBookDAOImpl;
import epam.homework.task5.dao.impl.UserDAOImpl;

public class DAOFactory {

	private static final DAOFactory INSTANCE = new DAOFactory();

	private final NoteBookDAO noteBookDAO = new NoteBookDAOImpl();
	private final UserDAO userDAO = new UserDAOImpl();

	private DAOFactory() {

	}

	public static DAOFactory getInstance() {
		return INSTANCE;
	}

	public NoteBookDAO getNoteBookDAO() {
		return noteBookDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

}
