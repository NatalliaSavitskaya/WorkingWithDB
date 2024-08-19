package myapp.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        TicketService ticketService = context.getBean(TicketService.class);

        // Create a new user
        User newUser = new User("Christina VeryNew", LocalDateTime.now());
        ticketService.createUser(newUser);
        System.out.println("User " + newUser + " saved successfully.");

        // Create a new ticket
        Ticket newTicket = new Ticket(5, "MONTH", LocalDateTime.now());
        ticketService.createTicket(newTicket);
        System.out.println("Ticket " + newTicket + " saved successfully.");

        // Update TicketType by UserID
        ticketService.updateTicketsTypeByUserID(3,"MONTH");

        // Update User and all his/her Tickets
        ticketService.updateUserAndTickets(5,"My_New_Name","YEAR");

        // Fetch User by ID
        User foundUser = ticketService.findUser(1);
        if (foundUser != null) {
            System.out.println(foundUser);
        } else {
            System.out.println("User not found.");
        }

        // Delete User by ID
        ticketService.deleteUser(1);

        // Fetch Ticket by ID
        Ticket foundTicket = ticketService.findTicket(1);
        if (foundTicket != null) {
            System.out.println(foundTicket);
        } else {
            System.out.println("Ticket not found.");
        }

        // Update Ticket_type
        Ticket updateTicket = ticketService.findTicket(1);
        if (updateTicket != null) {
            ticketService.updateTicket(1, "DAY");
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

        // Update User_name by ID
        User updateUser = ticketService.findUser(5);
        if (updateUser != null) {
            ticketService.updateUserNameByID(5, "New name");
            System.out.println("User " + updateUser + " updated successfully.");
        } else {
            System.out.println("User not found.");
        }

        // Delete Tickets by User ID
        ticketService.deleteTicketsByUserID(4);

        // Delete User and his/her Tickets by User ID
        ticketService.deleteUserAndTicketsByID(5);
    }
}