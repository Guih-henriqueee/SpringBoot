package Learning.Resources.Queryes.Routes.Roles;

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

    private final String tableName = "roles";

    public long insertRole(String roleName, String roleDescription, int roleBuget) {
        String sql = "INSERT INTO " + tableName + " (U_Name, U_Function, U_Age, U_Cpf) VALUES (?, ?, ?)";
        
    
        KeyHolder keyHolder = new GeneratedKeyHolder();
    
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"}); 
                ps.setString(1, roleName);
                ps.setString(2, roleDescription);
                ps.setInt(3, roleBuget);
                return ps;
            }, keyHolder);
    
            return keyHolder.getKey() != null ? keyHolder.getKey().longValue() : null;
    
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;  
        }
    }
    
}