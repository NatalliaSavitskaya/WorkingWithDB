package myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private UserDAO userDAO;

    public void createTicket(Ticket ticket) {ticketDAO.saveTicket(ticket);}

    public Ticket findTicket(String id) {return ticketDAO.getTicketById(id);}

    public void updateTicket(Ticket ticket, String newTicketType) {ticketDAO.updateTicketType(ticket, newTicketType);}

    public List<Ticket> findTicketsByUserId(int userId) {return ticketDAO.getTicketsByUserId(userId);}

    public void deleteTicketsByUserID(int userId) {ticketDAO.deleteTicketsByUserId(userId);}

    public void updateTicketsTypeByUserID(int userId, String newTicketType) {
        ticketDAO.updateTicketsTypeByUserID(userId, newTicketType);
    }

    public void deleteUserAndTicketsByID(int userId) {ticketDAO.deleteUserAndTicketsByID(userId);}

    public void createUser(User user) {userDAO.saveUser(user);}

    public User findUser(String id) {return userDAO.getUserById(id);}

    public void updateUserNameByID(User user, String newName) {userDAO.updateUserNameByID(user, newName);}

    public void updateUserAndTickets(int userId, String userName, String newTicketType) {
        ticketDAO.updateUserAndTickets(userId, userName, newTicketType);
    }

    public void deleteUser(User user) {userDAO.deleteUser(user);}
}