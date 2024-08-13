import java.time.LocalDateTime;
import java.util.List;

class TicketService {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        TicketDAO ticketDAO = new TicketDAO();

        // Saving user to the database
        User user = new User("Christina Agile", LocalDateTime.now());
        userDAO.saveUser(user);

        // Saving ticket to the database
        Ticket ticket = new Ticket(14, "DAY", LocalDateTime.now());
        ticketDAO.saveTicket(ticket);

        // Fetching user by ID
        User isUser = userDAO.getUserById(14);
        if (isUser != null) {
            System.out.println("User found: " + isUser);
        } else {
            System.out.println("User not found.");
        }

        // Fetching ticket by ID
        Ticket ticketById = ticketDAO.getTicketById(15);
        if (ticketById != null) {
            System.out.println("Ticket found by ID: " + ticketById);
        } else {
            System.out.println("Ticket not found by ID.");
        }

        // Fetching tickets by user_ID
        List<Ticket> ticketsByUserId = ticketDAO.getTicketsByUserId(14);
        if (!ticketsByUserId.isEmpty()) {
            System.out.println("Tickets found by user ID:");
            for (Ticket eachTicket : ticketsByUserId) {
                System.out.println(eachTicket);
            }
        } else {
            System.out.println("No tickets found for the user ID.");
        }

        // Updating ticket type
        boolean isUpdated = ticketDAO.updateTicketType(14, "MONTH");
        if (isUpdated) {
            System.out.println("Ticket type updated successfully.");
        } else {
            System.out.println("Ticket not found or update failed.");
        }

        // Deleting user and his/her tickets
        boolean isDeleted = userDAO.deleteUserById(14);
        if (isDeleted) {
            System.out.println("User and his/her tickets deleted successfully.");
        } else {
            System.out.println("User not found or deletion failed.");
        }
    }
}