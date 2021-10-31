package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static String url = "jdbc:sqlite:TierraMediaII.db";
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection(url);
		} 
		return connection;
	}
	
	public static void closeConexion() throws SQLException {
		if(!connection.isClosed()) {
			connection.close();
		}
	}
}
