package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection getConnection() throws Exception {
		Connection conn = null;
		
		String driver = "org.mariadb.jdbc.Driver";
		String dbUrl = "jdbc:mariadb://13.124.224.9:3306/shop";
		String dbUser = "root";
		String dbPw = "java1234";
		
		Class.forName(driver);
		conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
		conn.setAutoCommit(false);
		
		return conn;
	}
}
