package epam.homework.task5.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import epam.homework.task5.bean.entity.SQLUser;
import epam.homework.task5.dao.UserDAO;
import epam.homework.task5.dao.exception.DAOException;
import epam.homework.task5.dao.impl.pool.ConnectionPool;

public class UserDAOImpl implements UserDAO {

	// реализация авторизации
	@Override
	public boolean logination(String login, String password) throws DAOException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st
					.executeQuery("SELECT id FROM user where (user, pass)=('" + login + "','" + password + "');");
			if (result.isLast())
				return false;
			else {
				result.next();
				int userID = result.getInt("id");
			//	System.out.println(userID);  
				SQLUser.setUserID(userID);
				return true;
			}
		} catch (SQLException e) {
			throw new DAOException();
		} catch (InterruptedException e) {
			throw new DAOException();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			try {
				ConnectionPool.getInstance().returnConnection(con);
			} catch (SQLException e) {
			} catch (InterruptedException e) {
			}
		}

	}

	// регистрация пользователя в БД
	@Override
	public boolean registration(String login, String password) throws DAOException {

		Connection con = null;
		Statement st = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			int result = st.executeUpdate("INSERT INTO user(user, pass) VALUES('" + login + "','" + password + "');");
			if (result != 0) {
				st.close();
				return true;
			} else
				st.close();
			return false;
		}

		catch (Exception e) {

		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			try {
				ConnectionPool.getInstance().returnConnection(con);
			} catch (SQLException e) {
			} catch (InterruptedException e) {
			}
		}
		return false;
	}

}
