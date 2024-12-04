import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        TicketService ticketService = new TicketService();

        // Create a new user
        User newUser = new User("Christina VeryNew", LocalDateTime.now());
        ticketService.createUser(newUser);
        System.out.println("User " + newUser + " saved successfully.");

        // Fetch User by ID
        User foundUser = ticketService.findUser("1");
        if (foundUser != null) {
            System.out.println(foundUser);
        } else {
            System.out.println("User not found.");
        }

        // Delete User by ID
        User deleteUser = ticketService.findUser("1");
        ticketService.deleteUser(deleteUser);
        if (deleteUser != null) {
            System.out.println("User " + deleteUser + " was deleted.");
        } else {
            System.out.println("User not found.");
        }

        // Create a new ticket
        Ticket newTicket = new Ticket(5, "MONTH", LocalDateTime.now());
        ticketService.createTicket(newTicket);
        System.out.println("Ticket " + newTicket + " saved successfully.");

        // Fetch Ticket by ID
        Ticket foundTicket = ticketService.findTicket("1");
        if (foundTicket != null) {
            System.out.println(foundTicket);
        } else {
            System.out.println("Ticket not found.");
        }

        // Update Ticket_type
        Ticket updateTicket = ticketService.findTicket("1");
        if (updateTicket != null) {
            ticketService.updateTicket(updateTicket, "DAY");
            System.out.println("Ticket " + updateTicket + " updated successfully.");
        } else {
            System.out.println("Ticket not found.");
        }

        // Fetch Tickets by User_ID
        List<Ticket> tickets = ticketService.findTicketsByUserId(1);
        if (tickets != null && !tickets.isEmpty()) {
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        } else {
            System.out.println("No tickets found for user ID.");
        }

        // Delete Tickets by User ID
        ticketService.deleteTicketsByUserID(4);

        // Delete User and his/her Tickets by User ID
        ticketService.deleteUserAndTicketsByID(4);

        // Update User_name by ID
        User updateUser = ticketService.findUser("5");
        if (updateUser != null) {
            ticketService.updateUserNameByID(updateUser, "New name");
            System.out.println("User " + updateUser + " updated successfully.");
        } else {
            System.out.println("User not found.");
        }

        // Update TicketType by UserID
        ticketService.updateTicketTypeByUserID(5,"YEAR");

        // Update User and all his/her Tickets
        ticketService.updateUserAndTickets(5,"Again_New_Name","MONTH");
    }
}