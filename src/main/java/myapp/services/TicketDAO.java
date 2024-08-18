package myapp.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TicketDAO {

    private final SessionFactory sessionFactory;

    public TicketDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void saveTicket(Ticket ticket) {
        sessionFactory.getCurrentSession().save(ticket);
    }

    @Transactional(readOnly = true)
    public Ticket getTicketById(String id) {
        return sessionFactory.getCurrentSession().get(Ticket.class, id);
    }

    @Transactional
    public void updateTicketType(Ticket ticket, String newTicketType) {
        ticket.setTicketType(newTicketType);
        sessionFactory.getCurrentSession().update(ticket);
    }

    @Transactional(readOnly = true)
    public List<Ticket> getTicketsByUserId(int userId) {
        return sessionFactory.getCurrentSession()
                .createNativeQuery(SqlQueries.GET_TICKET_BY_USER_ID, Ticket.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Transactional
    public void deleteTicketsByUserId(int userId) {
        sessionFactory.getCurrentSession()
                .createNativeQuery(SqlQueries.DELETE_TICKETS_BY_USER_ID)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Transactional
    public void updateTicketsTypeByUserID(int userId, String newTicketType) {
        Session session = sessionFactory.getCurrentSession();
        List<Ticket> tickets = getTicketsByUserId(userId);
        for (Ticket ticket : tickets) {
            ticket.setTicketType(newTicketType);
            session.update(ticket);
        }
    }

    @Transactional
    public void updateUserAndTickets(int userId, String newUserName, String newTicketType) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        if (user != null) {
            user.setName(newUserName);
            session.update(user);
        }
        List<Ticket> tickets = getTicketsByUserId(userId);
        for (Ticket ticket : tickets) {
            ticket.setTicketType(newTicketType);
            session.update(ticket);
        }
    }

    @Transactional
    public void deleteUserAndTicketsByID(int userId) {
        Session session = sessionFactory.getCurrentSession();
        deleteTicketsByUserId(userId);
        User user = session.get(User.class, userId);
        if (user != null) {
            session.delete(user);
        }
    }
}