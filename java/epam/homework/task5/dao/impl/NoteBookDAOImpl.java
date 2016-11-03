package epam.homework.task5.dao.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import epam.homework.task5.bean.entity.Note;
import epam.homework.task5.bean.entity.SQLUser;
import epam.homework.task5.dao.NoteBookDAO;
import epam.homework.task5.dao.exception.DAOException;
import epam.homework.task5.dao.impl.pool.ConnectionPool;

public class NoteBookDAOImpl implements NoteBookDAO {

	// добавление записи в БД
	@Override
	public boolean addNote(Note note, int userID) throws DAOException {

		Connection con = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			try (Statement st = con.createStatement()) {

				int result = st.executeUpdate("INSERT INTO note(user_id, content, date) VALUES(" + userID + ",'"
						+ note.getNote() + "','" + note.getDate() + "');");
				st.close();
				if (result != 0) {
					return true;
				} else {
					throw new DAOException("Operation crash! Can't add note. Pls try again");
				}
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());

			}
		} catch (InterruptedException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (con != null) {
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (SQLException e) {
					throw new DAOException(e.getMessage());
				} catch (InterruptedException e) {
					throw new DAOException(e.getMessage());
				}
			}
		}

	}

	// создание нового noteBook'a сопровождаеться чисткой всех записей в БД
	@Override
	public boolean createNewNoteBook(int userID) throws DAOException {

		Connection con = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			Statement st = con.createStatement();
			int result = st.executeUpdate("delete FROM note where user_id='" + userID + "';");
			st.close();
			if (result != 0) {
				return true;
			} else {
				throw new DAOException("OOooppps!!! Operation failed!");
			}
		} catch (SQLException | InterruptedException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (con != null) {
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (SQLException | InterruptedException e) {
					throw new DAOException(e.getMessage());
				}
			}
		}

	}

	// поиск записей в БД по содержимому
	@Override
	public List<String> findNoteByContent(String content, int userID) throws DAOException {

		List<String> findNoteByContent = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st.executeQuery(
					"SELECT * FROM note where user_id='" + userID + "' and content like('%" + content + "%');");

			if (result.next() == false) {
				throw new DAOException("Nothing was found");
			} else {
				do {
					findNoteByContent.add(result.getString("content") + " " + result.getString("date"));
				} while (result.next());
				st.close();

			}
			return findNoteByContent;
		} catch (SQLException e) {
			e.getStackTrace();
			return null;
		} catch (InterruptedException e) {

			e.printStackTrace();
			return null;
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

	// поиск записей в БД по Дате
	@Override
	public List<String> findNotesByDate(String dateKey, int userID) throws DAOException {

		List<String> findNotesByDateLsit = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st.executeQuery(
					"SELECT * FROM note where user_id='" + userID + "' and date having('" + dateKey + "');");
			if (result.next() == false) {
				throw new DAOException("Nothing was found!");
			} else {
				do {
					findNotesByDateLsit.add(result.getString("content") + " " + result.getString("date"));
				} while (result.next());
				st.close();

			}
			return findNotesByDateLsit;
		} catch (SQLException | InterruptedException e) {

			throw new DAOException(e.getMessage());

		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			try {
				ConnectionPool.getInstance().returnConnection(con);
			} catch (SQLException | InterruptedException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}

	// показать все записи пользователя в БД
	@Override
	public List<String> showAllNotes(int userID) throws DAOException {

		Connection con = null;
		Statement st = null;

		List<String> showNotesList = new ArrayList<>();
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM note WHERE user_id='" + userID + "';");
			if (rs.next() == false) {
				st.close();
				throw new DAOException("Notes does not added yet");
			} else {
				do {
					showNotesList.add(rs.getString("content") + " " + rs.getString("date"));
				} while (rs.next());
				st.close();
			}
			return showNotesList;
		} catch (SQLException | InterruptedException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (con != null)
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (SQLException | InterruptedException e) {
					throw new DAOException(e.getMessage());
				}
		}

	}

	// сохранить все записи пользователя из БД в текстовый файл
	@Override
	public boolean saveNoteBook(int userID) throws DAOException {

		Connection con = null;
		Statement st = null;
		File file = new File("NoteBookUser" + userID + ".txt");
		PrintWriter pr;

		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM note WHERE user_id='" + userID + "';");
			if (rs.next() == false) {
				st.close();
				throw new DAOException("You didn't wrote any note yet!!!");
			} else {
				pr = new PrintWriter(new BufferedWriter(new FileWriter(file)));
				do {
					pr.println(rs.getString("content") + " " + rs.getString("date"));
				} while (rs.next());
				pr.close();
				st.close();
			}
			return true;
		} catch (SQLException | InterruptedException | IOException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (con != null)
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (SQLException | InterruptedException e) {
					throw new DAOException(e.getMessage());
				}
		}
	}

	// записать содержимое текстового файла в БД пользователя
	@Override
	public boolean loadNotesFromFile(List<Note> note) throws DAOException {

		int userID = SQLUser.getUserID();
		for (Note someNote : note)
			addNote(someNote, userID);

		return true;
	}

}
