package myapp.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static myapp.services.SqlQueries.*;

@Repository
public class TicketDAO {

    private final JdbcTemplate jdbcTemplate;

    public TicketDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void saveTicket(Ticket ticket) {
        jdbcTemplate.update(SAVE_TICKET, ticket.getUserId(), ticket.getTicketType(), ticket.getCreationDate());
    }

    public Ticket getTicketById(int id) {
        return jdbcTemplate.queryForObject(GET_TICKET_BY_ID, new TicketRowMapper(), id);
    }

    @Transactional
    public void updateTicketType(int ticketId, String newTicketType) {
        jdbcTemplate.update(UPDATE_TICKET_TYPE, newTicketType, ticketId);
    }

    public List<Ticket> getTicketsByUserId(int userId) {
        return jdbcTemplate.query(GET_TICKETS_BY_USER_ID, new TicketRowMapper(), userId);
    }

    @Transactional
    public void deleteTicketsByUserId(int userId) {
        jdbcTemplate.update(DELETE_TICKETS_BY_USER_ID, userId);
    }

    @Transactional
    public void updateTicketsTypeByUserID(int userId, String newTicketType) {
        jdbcTemplate.update(UPDATE_TICKET_TYPE_BY_USER_ID, newTicketType, userId);
        }

    @Transactional
    public void updateUserAndTickets(int userId, String newUserName, String newTicketType) {
        jdbcTemplate.update(UPDATE_USER_NAME_BY_ID, newUserName, userId);
        jdbcTemplate.update(UPDATE_TICKET_TYPE, newTicketType, userId);
    }

    @Transactional
    public void deleteUserAndTicketsByID(int userId) {
        jdbcTemplate.update(DELETE_USER, userId);
        jdbcTemplate.update(DELETE_TICKETS_BY_USER_ID, userId);
    }

    private static class TicketRowMapper implements RowMapper<Ticket> {
        @Override
        public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setUserId(rs.getInt("user_id"));
            ticket.setTicketType(rs.getString("ticket_type"));
            ticket.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
            return ticket;
        }
    }
}