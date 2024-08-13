import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class UserDAO {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        // Load properties from a file
        try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            URL = prop.getProperty("db.url");
            USER = prop.getProperty("db.user");
            PASSWORD = prop.getProperty("db.password");
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle exception as needed
        }
    }
    // Get a database connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Save a user to the database
    public void saveUser(User user) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SqlQueries.SAVE_USER)) {

            pstmt.setString(1, user.getName());
            LocalDateTime localDateTime = user.getCreationDate();
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            pstmt.setTimestamp(2, timestamp);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch user by ID
    public User getUserById(int id) {
        User user = null;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SqlQueries.GET_USER_BY_ID)) {

            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                java.sql.Timestamp creationDate = resultSet.getTimestamp("creation_date");
                Timestamp timestamp = resultSet.getTimestamp("creation_date");
                LocalDateTime creationDate1 = timestamp.toLocalDateTime();
                user = new User(userId, name, creationDate1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // Method to delete a user and their tickets
    public boolean deleteUserById(int userId) {
        boolean success = false;

        try (Connection conn = getConnection();
             PreparedStatement deleteTicketsStmt = conn.prepareStatement(SqlQueries.DELETE_TICKETS_BY_USER_ID);
             PreparedStatement deleteUserStmt = conn.prepareStatement(SqlQueries.DELETE_USER_BY_ID)) {

            conn.setAutoCommit(false);  // Disable auto-commit to start the transaction

            deleteTicketsStmt.setInt(1, userId);  // Delete tickets associated with the user
            int ticketsDeleted = deleteTicketsStmt.executeUpdate();

            deleteUserStmt.setInt(1, userId); // Delete the user
            int userDeleted = deleteUserStmt.executeUpdate();

            if (ticketsDeleted > 0 || userDeleted > 0) {
                conn.commit(); // Commit the transaction if both operations succeed
                success = true;
            } else {
                conn.rollback();  // Rollback if no rows were affected
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
}