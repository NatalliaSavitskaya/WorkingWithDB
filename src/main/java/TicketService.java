import java.util.List;

public class TicketService {

    private TicketDAO ticketDAO = new TicketDAO();
    private UserDAO userDAO = new UserDAO();

    public TicketService() {}

    public void createTicket(Ticket ticket) { ticketDAO.saveTicket(ticket); }

    public Ticket findTicket(String id) { return ticketDAO.getTicketById(id); }

    public void updateTicket(Ticket ticket, String new_ticket_type) { ticketDAO.updateTicketType(ticket, new_ticket_type); }

    public void updateTicketTypeByUserID(int userID, String ticket_type) { ticketDAO.updateTicketTypeByUserID(userID, ticket_type);}

    public List<Ticket> findTicketsByUserId(int userID) {
        return ticketDAO.getTicketsByUserId(userID);
    }

    public void deleteTicketsByUserID(int userID) { ticketDAO.deleteTicketsByUserId(userID); }

    public void deleteUserAndTicketsByID(int userID) { ticketDAO.deleteUserAndTicketsByID(userID); }

    public void createUser(User user) {
        userDAO.saveUser(user);
    }

    public User findUser(String id) {
        return userDAO.getUserById(id);
    }

    public void updateUserNameByID(User user, String new_name) {userDAO.updateUserNameByID(user, new_name);}

    public void updateUserAndTickets(int userId, String user_name, String ticket_type) { ticketDAO.updateUserAndTickets(userId, user_name, ticket_type);}

    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }
}