package Learning.Resources.Queryes.Routes.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.dao.DataAccessException;
import java.sql.PreparedStatement;

@Component("specificInsertUser")
public class InsertUser {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String tableName = "Users";

    public long insertUser(String name, String function, int age, String cpf) {
        String sql = "INSERT INTO " + tableName + " (U_Name, U_Function, U_Age, U_Cpf) VALUES (?, ?, ?, ?)";
        
      
        KeyHolder keyHolder = new GeneratedKeyHolder();
    
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"}); 
                ps.setString(1, name);
                ps.setString(2, function);
                ps.setInt(3, age);
                ps.setString(4, cpf);
                return ps;
            }, keyHolder);
    
            return keyHolder.getKey() != null ? keyHolder.getKey().longValue() : null;
    
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;  
        }
    }
    
}