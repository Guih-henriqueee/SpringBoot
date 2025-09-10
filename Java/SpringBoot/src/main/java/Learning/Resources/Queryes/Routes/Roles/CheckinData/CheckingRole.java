package Learning.Resources.Queryes.Routes.Roles.CheckinData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.dao.DataAccessException;

import java.sql.PreparedStatement;

@Component("specificCheckingRole")
public class CheckingRole {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String tableName = "roles";

    public Long checkingRole(String roleName, String description) {
            String verify = "SELECT COUNT(*) FROM " + tableName + 
                " WHERE LOWER(REPLACE(role_name, ' ', '')) LIKE LOWER(REPLACE(?, ' ', ''))" +
                " OR LOWER(REPLACE(description, ' ', '')) LIKE LOWER(REPLACE(?, ' ', ''))";

            try {
                Long count = jdbcTemplate.queryForObject(
                    verify,
                    new Object[]{roleName, description},
                    Long.class
                );
                return count != null ? count : 0L;
            } catch (DataAccessException e) {
                e.printStackTrace();
                return 0L;
            }
        }
}
