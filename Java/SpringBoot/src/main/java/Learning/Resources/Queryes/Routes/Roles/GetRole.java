package Learning.Resources.Queryes.Routes.Roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("specificGetRole")
public class GetRole {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String tableName = "roles";

    public Map<String, Object> getRoleById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        try {
            return jdbcTemplate.queryForMap(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Map<String, Object>> getRoles() {
        String sql = "SELECT * FROM " + tableName;
        try {
            return jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); 
        }
    }
}
