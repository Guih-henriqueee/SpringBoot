package Learning.Resources.Queryes.Routes.Roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.dao.DataAccessException;

@Component("specificUpdateRole")
public class UpdateRole {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String tableName = "roles";

    public boolean updateRole(int id, String roleName, String roleDescription, int roleBuget) {
        String sql = "UPDATE " + tableName + " SET role_name = ?, description = ?, role_buget = ? WHERE id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, roleName, roleDescription, roleBuget, id);
            return rowsAffected > 0; 
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
