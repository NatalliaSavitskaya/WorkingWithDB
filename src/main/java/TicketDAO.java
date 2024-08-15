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
    public void updateTicketType(Ticket ticket, String new_ticket_type) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ticket.setTicketType(new_ticket_type);
        session.update(ticket);
        transaction.commit();
        session.close();
    }

    // Fetch Tickets by User_ID
    public List<Ticket> getTicketsByUserId(int userID) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        String sql = "SELECT * FROM public.tickets WHERE user_id = :userId";
        Query<Ticket> query = session.createNativeQuery(sql, Ticket.class);
        query.setParameter("userId", userID);
        return query.list();
    }

    // Delete Tickets by User_ID
    public void deleteTicketsByUserId(int userID) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String sql = "DELETE FROM public.tickets WHERE user_id = :userId";
            Query<?> query = session.createNativeQuery(sql);
            query.setParameter("userId", userID);
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
    public void updateTicketTypeByUserID(int userId, String ticket_type) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Ticket> tickets = getTicketsByUserId(userId);
        for (Ticket ticket:tickets) {
            ticket.setTicketType(ticket_type);
            session.update(ticket);
        }
        transaction.commit();
        session.close();
    }

    // Update User and his/her tickets by ID
    public void updateUserAndTickets(int userId, String user_name, String ticket_type) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, userId);
        user.setName(user_name);
        session.update(user);
        List<Ticket> tickets = getTicketsByUserId(userId);
        for (Ticket ticket:tickets) {
            ticket.setTicketType(ticket_type);
            session.update(ticket);
        }
        transaction.commit();
        session.close();
    }
}