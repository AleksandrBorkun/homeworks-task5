package epam.homework.task5.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import epam.homework.task5.bean.entity.Note;
import epam.homework.task5.dao.NoteBookDAO;
import epam.homework.task5.dao.impl.pool.ConnectionPool;

public class NoteBookDAOImpl implements NoteBookDAO {

	@Override
	public void addNote(Note note, int userID) {

		Connection con = null;
		Statement st = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			int result = st.executeUpdate("INSERT INTO note(user_id, content, date) VALUES(" + userID + ",'"
					+ note.getNote() + "','" + note.getDate() + "');");
			st.close();
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

	@Override
	public void createNewNoteBook(int userID) {

		Connection con = null;
		Statement st = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			int result = st.executeUpdate("delete FROM note where user_id='" + userID + "';");
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
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

	@Override
	public void findNoteByContent(String content, int userID) {

		Connection con = null;
		Statement st = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st
					.executeQuery("SELECT * FROM note where(user_id, content)=('" + userID + "','" + content + "');");
			if (result.next() == false) {
				System.out.println("Nothing was found by that CONTAINS");
				st.close();
			} else {
				do {
					System.out.println(result.getString("content") + " " + result.getString("date"));
				} while (result.next());
				st.close();

			}
		} catch (SQLException e) {
			e.getStackTrace();
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

}
