package epam.homework.task5.dao;

import epam.homework.task5.dao.exception.DAOException;

public interface UserDAO {
	boolean logination(String login, String password) throws DAOException;

	public boolean registration(String login, String password) throws DAOException;
	
}

