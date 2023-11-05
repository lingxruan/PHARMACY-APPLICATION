// Importing the Swing library for GUI components
import javax.swing.*;
// Importing the Connection class to manage the database connection
import java.sql.Connection;
// Importing the DriverManager class to establish a connection to a database
import java.sql.DriverManager;
// Importing the PreparedStatement class to execute precompiled SQL statements
import java.sql.PreparedStatement;
// Importing the ResultSet class to retrieve data from a database query
import java.sql.ResultSet;
// Importing the SQLException class to handle database-related exceptions
import java.sql.SQLException;


public class DatabaseHandler {
    private Connection connection;

    public DatabaseHandler() {
        // Define the MySQL database connection parameters
        String url = "jdbc:mysql://localhost/pharma?serverTimezone=UTC";
        String username = "root";
        String password = "";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection to the MySQL database
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUsernameTaken(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void registerUser(String username, String firstName, String lastName, String gender, String address, String email, String password) {
        if (isUsernameTaken(username)) {
            JOptionPane.showMessageDialog(null, "Username is already taken. Please choose another.");
            return;
        }

        String query = "INSERT INTO users(username, first_name, last_name, gender, address, email, password) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, password);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registration successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getMedicineInfo(String medicineName) {
        String query = "SELECT * FROM medicines WHERE name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, medicineName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String dose = resultSet.getString("dose");
                String sideEffects = resultSet.getString("side_effects");
                String symptoms = resultSet.getString("symptoms");
                String usageGuidelines = resultSet.getString("usage_guidelines");
                return "Medicine Name: " + medicineName + "\nDose: " + dose +
                    "\nSide Effects: " + sideEffects + "\nSymptoms: " + symptoms +
                    "\nUsage Guidelines: " + usageGuidelines;
            } else {
                return "Medicine not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error fetching medicine information.";
        }
    }


    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
