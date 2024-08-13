import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TicketDAO {
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

    // Save a ticket to the database
    public void saveTicket(Ticket ticket) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SqlQueries.SAVE_TICKET)) {
            pstmt.setInt(1, ticket.getUserId());
            pstmt.setString(2, ticket.getTicketType());
            LocalDateTime localDateTime = ticket.getCreationDate();
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            pstmt.setTimestamp(3, timestamp);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch a ticket by ID
    public Ticket getTicketById(int id) {
        Ticket ticket = null;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SqlQueries.GET_TICKET_BY_ID)) {

            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                int ticketId = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                String ticketType = resultSet.getString("ticket_type");
                LocalDateTime creationDate = resultSet.getTimestamp("creation_date").toLocalDateTime();
                ticket = new Ticket(ticketId, userId, ticketType, creationDate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticket;
    }

    // Fetch tickets by user ID
    public List<Ticket> getTicketsByUserId(int userId) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SqlQueries.GET_TICKET_BY_USER_ID)) {

            pstmt.setInt(1, userId);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ticketType = resultSet.getString("ticket_type");
                LocalDateTime creationDate = resultSet.getTimestamp("creation_date").toLocalDateTime();
                Ticket ticket = new Ticket(id, userId, ticketType, creationDate);
                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    // Method to update ticket type
    public boolean updateTicketType(int id, String newTicketType) {
        boolean updated = false;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SqlQueries.UPDATE_TICKET_TYPE)) {

            pstmt.setString(1, newTicketType);
            pstmt.setInt(2, id);

            int rowsAffected = pstmt.executeUpdate();
            updated = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }
}
