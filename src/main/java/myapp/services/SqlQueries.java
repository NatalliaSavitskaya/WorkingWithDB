package myapp.services;

public class SqlQueries {
    public static final String GET_TICKETS_BY_USER_ID = "SELECT * FROM public.tickets WHERE user_id = :userId";
    public static final String DELETE_TICKETS_BY_USER_ID = "DELETE FROM public.tickets WHERE user_id = :userId";
    public static final String SAVE_TICKET = "INSERT INTO public.tickets (user_id, ticket_type, creation_date) VALUES (?, ?, ?)";
    public static final String GET_TICKET_BY_ID = "SELECT * FROM public.tickets WHERE id = ?";
    public static final String UPDATE_TICKET_TYPE = "UPDATE public.tickets SET ticket_type = ? WHERE id = ?";
    public static final String UPDATE_TICKET_TYPE_BY_USER_ID = "UPDATE public.tickets SET ticket_type = ? WHERE user_id = ?";
    public static final String GET_BY_USER_ID = "SELECT * FROM public.users WHERE id = ?";
    public static final String SAVE_USER = "INSERT INTO public.users (name, creation_date) VALUES (?, ?)";
    public static final String DELETE_USER = "DELETE FROM public.users WHERE id = ?";
    public static final String UPDATE_USER_NAME_BY_ID = "UPDATE public.users SET name = ? WHERE id = ?";


}