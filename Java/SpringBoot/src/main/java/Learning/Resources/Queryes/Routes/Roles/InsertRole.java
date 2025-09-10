package Learning.Resources.Queryes.Routes.Roles;

import Learning.Resources.Queryes.Routes.Roles.CheckinData.CheckingRole;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.dao.DataAccessException;

import java.sql.PreparedStatement;

@Component("specificInsertRole")
public class InsertRole {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CheckingRole checkingRole;

    private final String tableName = "roles";

    public Long insertRole(String roleName, String description, Double roleBudget) {
        String sql = "INSERT INTO " + tableName + " (role_name, description, budget_role) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        if (checkingRole.checkingRole(roleName, description) > 0) {
            return 1L; 
        }

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, roleName);
                ps.setString(2, description);
                ps.setDouble(3, roleBudget);
                return ps;
            }, keyHolder);

            return keyHolder.getKey() != null ? keyHolder.getKey().longValue() : null;

        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0L;
        }
    }
}
