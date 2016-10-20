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
import java.util.List;

import epam.homework.task5.bean.entity.Note;
import epam.homework.task5.bean.entity.SQLUser;
import epam.homework.task5.dao.NoteBookDAO;
import epam.homework.task5.dao.impl.pool.ConnectionPool;

public class NoteBookDAOImpl implements NoteBookDAO {

	// добавление записи в БД
	@Override
	public boolean addNote(Note note, int userID) {

		Connection con = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			try (Statement st = con.createStatement()) {

				int result = st.executeUpdate("INSERT INTO note(user_id, content, date) VALUES(" + userID + ",'"
						+ note.getNote() + "','" + note.getDate() + "');");
				st.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (con != null) {
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// создание нового noteBook'a сопровождаеться чисткой всех записей в БД
	@Override
	public boolean createNewNoteBook(int userID) {

		Connection con = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			try (Statement st = con.createStatement()) {
				int result = st.executeUpdate("delete FROM note where user_id='" + userID + "';");
				st.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (con != null) {
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	// поиск записей в БД по содержимому
	@Override
	public boolean findNoteByContent(String content, int userID) {

		Connection con = null;
		Statement st = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st.executeQuery(
					"SELECT * FROM note where user_id='" + userID + "' and content like('%" + content + "%');");
			if (result.next() == false) {
				System.out.println("Nothing was found by that CONTAINS");
				st.close();
			} else {
				do {
					System.out.println(result.getString("content") + " " + result.getString("date"));
				} while (result.next());
				st.close();

			}
			return true;
		} catch (SQLException e) {
			e.getStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
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
	public void findNotesByDate(String dateKey, int userID) {

		Connection con = null;
		Statement st = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st.executeQuery(
					"SELECT * FROM note where user_id='" + userID + "' and date having('" + dateKey + "');");
			if (result.next() == false) {
				System.out.println("Nothing was found by that Date");
				st.close();
			} else {
				do {
					System.out.println(result.getString("content") + " " + result.getString("date"));
				} while (result.next());
				st.close();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (

		InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	// показать все записи пользователя в БД
	@Override
	public void showAllNotes(int userID) {
		Connection con = null;
		Statement st = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM note WHERE user_id='" + userID + "';");
			if (rs.next() == false) {
				System.out.println("You didn't wrote any note yet!!!");
				st.close();
			} else {
				do {
					System.out.println(rs.getString("content") + " " + rs.getString("date"));
				} while (rs.next());
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	// сохранить все записи пользователя из БД в текстовый файл
	@Override
	public void saveNoteBook(int userID) {
		Connection con = null;
		Statement st = null;
		File file = new File("NoteBookUser" + userID + ".txt");
		PrintWriter pr;

		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM note WHERE user_id='" + userID + "';");
			if (rs.next() == false) {
				System.out.println("You didn't wrote any note yet!!!");
				st.close();
			} else {
				pr = new PrintWriter(new BufferedWriter(new FileWriter(file)));
				do {
					pr.println(rs.getString("content") + " " + rs.getString("date"));
				} while (rs.next());
				pr.close();
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	// записать содержимое текстового файла в БД пользователя
	@Override
	public void loadNotesFromFile(List<Note> note) {

		int userID = SQLUser.getUserID();
		for (Note someNote : note)
			addNote(someNote, userID);

	}

}
