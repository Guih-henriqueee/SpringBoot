package Learning.Resources.Queryes.Routes.Roles;


import Learning.Model.Role;
import Learning.Model.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.dao.DataAccessException;

@Component("specificDeleteRole")
public class DeleteRole {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String tableName = "roles";

    public boolean deleteRole(int id) {
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
