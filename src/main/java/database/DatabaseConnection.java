package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

	static String dbName;
	static String port;
	static ResultSet rs;
	static String userName;
	static String Password;
	static Connection con;
	static Statement stmt;

	// declare constructor
	public DatabaseConnection(String dbName, String port, String userName, String Password) {
		DatabaseConnection.dbName = dbName;
		DatabaseConnection.userName = userName;
		DatabaseConnection.Password = Password;
		DatabaseConnection.port = port;
	}

	// start connect to db
	public static void createDBConnection() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/" + dbName, userName,
				Password);
		DatabaseConnection.con = con;
	}

	// create statement
	public static void createStatement() throws SQLException {
		Statement stmt = con.createStatement();
		DatabaseConnection.stmt = stmt;
	}

	// get resultset from database
	public static ResultSet dbResultSet(String query) throws SQLException {

		rs = stmt.executeQuery(query);
//			while (rs.next())
//				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
//			con.close();
		return rs;
	}

	// close db connection
	public static void closedbConnection() throws SQLException {
		con.close();
	}
}
