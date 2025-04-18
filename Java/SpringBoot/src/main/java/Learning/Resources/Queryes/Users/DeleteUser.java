package Learning.Resources.Queryes.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.dao.DataAccessException;

@Component("specificDeleteUser")
public class DeleteUser {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String tableName = "Users";

    public boolean deleteUser(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            return rowsAffected > 0; 
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
