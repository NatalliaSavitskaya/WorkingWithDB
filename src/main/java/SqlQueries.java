public class SqlQueries {

    // User queries
    public static final String SAVE_USER = "INSERT INTO public.\"User\" (name, creation_date) VALUES (?, ?)";
    public static final String GET_USER_BY_ID = "SELECT id, name, creation_date FROM \"User\" WHERE id = ?";
    public static final String DELETE_USER_BY_ID = "DELETE FROM \"User\" WHERE id = ?";

    // Ticket queries
    public static final String SAVE_TICKET = "INSERT INTO public.\"Ticket\" (user_id, ticket_type, creation_date) VALUES (?, ?, ?)";
    public static final String GET_TICKET_BY_ID = "SELECT id, user_id, ticket_type, creation_date FROM \"Ticket\" WHERE id = ?";
    public static final String GET_TICKET_BY_USER_ID = "SELECT id, user_id, ticket_type, creation_date FROM \"Ticket\" WHERE user_id = ?";
    public static final String UPDATE_TICKET_TYPE = "UPDATE \"Ticket\" SET ticket_type = ? WHERE id = ?";
    public static final String DELETE_TICKETS_BY_USER_ID = "DELETE FROM \"Ticket\" WHERE user_id = ?";
}