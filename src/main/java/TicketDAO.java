import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class TicketDAO {

    // Save a ticket to the database
    public void saveTicket(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
    }

    // Fetch Ticket by ID
    public Ticket getTicketById(String id) {
        return SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .get(Ticket.class, id);
    }

    // Update Ticket type
    public void updateTicketType(Ticket ticket, String newTicketType) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ticket.setTicketType(newTicketType);
        session.update(ticket);
        transaction.commit();
        session.close();
    }

    // Fetch Tickets by User_ID
    public List<Ticket> getTicketsByUserId(int userId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Query<Ticket> query = session.createNativeQuery(SqlQueries.GET_TICKET_BY_USER_ID, Ticket.class);
        query.setParameter("userId", userId);
        return query.list();
    }

    // Delete Tickets by User_ID
    public void deleteTicketsByUserId(int userId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<?> query = session.createNativeQuery(SqlQueries.DELETE_TICKETS_BY_USER_ID);
            query.setParameter("userId", userId);
            int result = query.executeUpdate();
            System.out.println("Deleted tickets count: " + result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Delete User and his/her tickets by ID
    public void deleteUserAndTicketsByID(int userId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        deleteTicketsByUserId(userId);  // Delete associated tickets first
        User user = session.get(User.class, userId);
        if (user != null) {
            session.delete(user);
        }
        transaction.commit();
        session.close();
    }

    // Update Tickets by user ID
    public void updateTicketTypeByUserID(int userId, String newTicketType) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Ticket> tickets = getTicketsByUserId(userId);
        for (Ticket ticket:tickets) {
            ticket.setTicketType(newTicketType);
            session.update(ticket);
        }
        transaction.commit();
        session.close();
    }

    // Update User and his/her tickets by ID
    public void updateUserAndTickets(int userId, String newUserName, String newTicketType) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, userId);
        user.setName(newUserName);
        session.update(user);
        List<Ticket> tickets = getTicketsByUserId(userId);
        for (Ticket ticket:tickets) {
            ticket.setTicketType(newTicketType);
            session.update(ticket);
        }
        transaction.commit();
        session.close();
    }
}