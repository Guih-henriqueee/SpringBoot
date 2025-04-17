package Learning.Resources.Queryes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("specificGetUser")
public class GetUser {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String tableName = "users";

    public Map<String, Object> getUserById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        try {
            return jdbcTemplate.queryForMap(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Map<String, Object>> getUsers() {
        String sql = "SELECT * FROM " + tableName;
        try {
            return jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); 
        }
    }
}
