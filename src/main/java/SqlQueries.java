public class SqlQueries {
    public static final String GET_TICKET_BY_USER_ID = "SELECT * FROM public.tickets WHERE user_id = :userId";
    public static final String DELETE_TICKETS_BY_USER_ID = "DELETE FROM public.tickets WHERE user_id = :userId";
}