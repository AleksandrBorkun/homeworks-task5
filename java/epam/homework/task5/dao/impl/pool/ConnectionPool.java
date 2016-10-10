package epam.homework.task5.dao.impl.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
	private static final ConnectionPool instance = new ConnectionPool();
	
	private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(5);
	
	private ConnectionPool(){
	
	try {
			// load driver
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// for()
			for(int index = 0; index<pool.remainingCapacity(); index++ ){
				pool.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/NOTEBOOK?useSSL=false", "root", "root"));
						}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
		
		
		
	public Connection getConnection() throws InterruptedException{
		return pool.take();
	}
	
	public void returnConnection(Connection connection) throws SQLException, InterruptedException{
		if(connection == null){
			return;
		}
		connection.setAutoCommit(true);
		connection.setReadOnly(true);
		
		pool.put(connection);
	}
	
	
	public void closePool(){
		
		for(Connection con : pool){
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		
	}
	
	
	public static ConnectionPool getInstance(){
		return instance;
	}
	

}
