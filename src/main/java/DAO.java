import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/ticket_service_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345";

    // Get a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Save a user to the database
    public void saveUser(User user) {
        String sql = "INSERT INTO public.\"User\" (name, creation_date) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getName());
            pstmt.setTimestamp(2, user.getCreationDate());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Save a ticket to the database
    public void saveTicket(Ticket ticket) {
        String sql = "INSERT INTO public.\"Ticket\" (user_id, ticket_type, creation_date) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ticket.getUserId());
            pstmt.setString(2, ticket.getTicketType());
            pstmt.setTimestamp(3, ticket.getCreationDate());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch user by ID
    public User getUserById(int id) {
        User user = null;
        String sql = "SELECT id, name, creation_date FROM \"User\" WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                java.sql.Timestamp creationDate = resultSet.getTimestamp("creation_date");
                user = new User(userId, name, creationDate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // Fetch a ticket by ID
    public Ticket getTicketById(int id) {
        Ticket ticket = null;
        String sql = "SELECT id, user_id, ticket_type, creation_date FROM \"Ticket\" WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                int ticketId = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                String ticketType = resultSet.getString("ticket_type");
                java.sql.Timestamp creationDate = resultSet.getTimestamp("creation_date");
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
        String sql = "SELECT id, user_id, ticket_type, creation_date FROM \"Ticket\" WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ticketType = resultSet.getString("ticket_type");
                java.sql.Timestamp creationDate = resultSet.getTimestamp("creation_date");
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
        String sql = "UPDATE \"Ticket\" SET ticket_type = ? WHERE id = ?";
        boolean updated = false;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newTicketType);
            pstmt.setInt(2, id);

            int rowsAffected = pstmt.executeUpdate();
            updated = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    // Method to delete a user and their tickets
    public boolean deleteUserById(int userId) {
        boolean success = false;
        String deleteTicketsQuery = "DELETE FROM \"Ticket\" WHERE user_id = ?";
        String deleteUserQuery = "DELETE FROM \"User\" WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement deleteTicketsStmt = conn.prepareStatement(deleteTicketsQuery);
             PreparedStatement deleteUserStmt = conn.prepareStatement(deleteUserQuery)) {

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