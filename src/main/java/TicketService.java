import java.sql.Timestamp;
import java.util.List;

class TicketService {
    public static void main(String[] args) {
        DAO dao = new DAO();

        // Saving user to the database
        User user = new User("Christina Agile", new Timestamp(System.currentTimeMillis()));
        dao.saveUser(user);

        // Saving ticket to the database
        Ticket ticket = new Ticket(2, "YEAR", new Timestamp(System.currentTimeMillis()));
        dao.saveTicket(ticket);

        // Fetching user by ID
        User isUser = dao.getUserById(5);
        if (isUser != null) {
            System.out.println("User found: " + isUser);
        } else {
            System.out.println("User not found.");
        }

        // Fetching ticket by ID
        Ticket ticketById = dao.getTicketById(1);
        if (ticketById != null) {
            System.out.println("Ticket found by ID: " + ticketById);
        } else {
            System.out.println("Ticket not found by ID.");
        }

        // Fetching tickets by user_ID
        List<Ticket> ticketsByUserId = dao.getTicketsByUserId(1);
        if (!ticketsByUserId.isEmpty()) {
            System.out.println("Tickets found by user ID:");
            for (Ticket eachTicket : ticketsByUserId) {
                System.out.println(eachTicket);
            }
        } else {
            System.out.println("No tickets found for the user ID.");
        }

        // Updating ticket type
        boolean isUpdated = dao.updateTicketType(3, "MONTH");
        if (isUpdated) {
            System.out.println("Ticket type updated successfully.");
        } else {
            System.out.println("Ticket not found or update failed.");
        }

        // Deleting user and his/her tickets
        boolean isDeleted = dao.deleteUserById(1);
        if (isDeleted) {
            System.out.println("User and his/her tickets deleted successfully.");
        } else {
            System.out.println("User not found or deletion failed.");
        }
    }
}
