package myapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {

    private final TicketDAO ticketDAO;
    private final UserDAO userDAO;

    public TicketService(TicketDAO ticketDAO, UserDAO userDAO) {
        this.ticketDAO = ticketDAO;
        this.userDAO = userDAO;
    }

    public void createTicket(Ticket ticket) {ticketDAO.saveTicket(ticket);}

    public Ticket findTicket(int id) {return ticketDAO.getTicketById(id);}

    public void updateTicket(int ticketId, String newTicketType) {ticketDAO.updateTicketType(ticketId, newTicketType);}

    public List<Ticket> findTicketsByUserId(int userId) {return ticketDAO.getTicketsByUserId(userId);}

    public void deleteTicketsByUserID(int userId) {ticketDAO.deleteTicketsByUserId(userId);}

    public void updateTicketsTypeByUserID(int userId, String newTicketType) {
        ticketDAO.updateTicketsTypeByUserID(userId, newTicketType);
    }

    public void deleteUserAndTicketsByID(int userId) {ticketDAO.deleteUserAndTicketsByID(userId);}

    public void createUser(User user) {userDAO.saveUser(user);}

    public User findUser(int id) {return userDAO.getUserById(id);}

    public void updateUserNameByID(int userId, String newName) {userDAO.updateUserNameByID(userId, newName);}

    public void updateUserAndTickets(int userId, String userName, String newTicketType) {
        ticketDAO.updateUserAndTickets(userId, userName, newTicketType);
    }

    public void deleteUser(int userId) {userDAO.deleteUser(userId);}
}