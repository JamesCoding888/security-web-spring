package main.sqlinjection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class UserSQLInjectionSample {
	// JDBC URL, username, and password of MySQL server
	private static final String URL = "jdbc:mysql://localhost:3306/springbatch";
	private static final String USER = "User1";
	private static final String PASSWORD = "1234";
	// JDBC variables for opening, closing, and managing connection
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	
	public static void main(String[] args) {
		try {
			// Register MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Open connection
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			// SQL query to insert data into the user table
			String sqlQuery = "INSERT INTO user (username, password, age) VALUES (?, ?, ?)";

			// Create PreparedStatement object
			preparedStatement = connection.prepareStatement(sqlQuery);

			// Set values for parameters
			preparedStatement.setString(1, "exampleUser");
			preparedStatement.setString(2, "7777");
			preparedStatement.setInt(3, 33);

			// Execute the query
			preparedStatement.executeUpdate();
			System.out.println("User inserted successfully!");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			// Close PreparedStatement and Connection
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}