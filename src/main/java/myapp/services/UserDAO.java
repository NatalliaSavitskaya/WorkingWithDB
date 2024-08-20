package myapp.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

import static myapp.services.SqlQueries.*;

@Repository
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUserById(int id) {
        return jdbcTemplate.queryForObject(GET_BY_USER_ID, new UserRowMapper(), id);
    }

    @Transactional
    public void saveUser(User user) {
        jdbcTemplate.update(SAVE_USER, user.getName(), user.getCreationDate());
    }

    @Transactional
    public void deleteUser(int userId) {
        jdbcTemplate.update(DELETE_USER, userId);
    }

    @Transactional
    public void updateUserNameByID(int userId, String newName) {
        jdbcTemplate.update(UPDATE_USER_NAME_BY_ID, newName, userId);
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
            return user;
        }
    }
}