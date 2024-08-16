import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAO {

    // Fetch User by ID
    public User getUserById(String id) {
        return SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .get(User.class, id);
    }

    // Save User to the database
    public void saveUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    // Delete User by ID
    public void deleteUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    // Update User name by ID
    public void updateUserNameByID(User user, String  newName) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        user.setName(newName);
        session.update(user);
        transaction.commit();
        session.close();
    }
}