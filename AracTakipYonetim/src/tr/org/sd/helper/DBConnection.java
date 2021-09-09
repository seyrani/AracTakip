package tr.org.sd.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	static Statement statement;

	/*
	 * private final String userName = "root"; private final String password = "";
	 * private final String url = "jdbc:mysql://localhost:3306/dbname"; private
	 * static String driver = "com.mysql.jdbc.Driver";
	 */

	private final String url = "jdbc:sqlite:db/arac_takip.db";
	private static String driver = "org.sqlite.JDBC";

	static {

		try {
			Class.forName(driver);
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected Connection getConnection() {

		Connection connection = null;
		try {
			/*
			 * connection = (Connection) DriverManager.getConnection(userName,password,url);
			 */

			connection = DriverManager.getConnection(url);
			// JOptionPane.showMessageDialog(null, "Connection Established");
			// System.out.println("Connection Established");
		} catch (final SQLException e) {
			e.printStackTrace();
		}

		return connection;

	}

}
