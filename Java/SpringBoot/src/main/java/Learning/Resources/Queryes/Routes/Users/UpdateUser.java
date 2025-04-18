package Learning.Resources.Queryes.Routes.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.dao.DataAccessException;

@Component("specificUpdateUser")
public class UpdateUser {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String tableName = "Users";

    public boolean updateUser(int id, String name, String function, int age) {
        String sql = "UPDATE " + tableName + " SET U_Name = ?, U_Function = ?, U_Age = ? WHERE id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, name, function, age, id);
            return rowsAffected > 0; 
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
