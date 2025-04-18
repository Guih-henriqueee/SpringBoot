package Learning.Resources.Queryes.Users;

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
        
        // Criar o KeyHolder para armazenar a chave gerada
        KeyHolder keyHolder = new GeneratedKeyHolder();
    
        // Usando o JdbcTemplate para inserir os dados e capturar a chave gerada
        try {
            // Usando o JdbcTemplate para inserir os dados e capturar a chave gerada
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"}); // 'id' é o nome da chave primária
                ps.setString(1, name);
                ps.setString(2, function);
                ps.setInt(3, age);
                ps.setString(4, cpf);
                return ps;
            }, keyHolder);
    
            // Retornando o ID gerado (Long) ou null em caso de falha
            return keyHolder.getKey() != null ? keyHolder.getKey().longValue() : null;
    
        } catch (DataAccessException e) {
            // Lidar com exceções, como problemas ao executar a query
            e.printStackTrace();
            return 0;  // Retorna null em caso de falha na execução
        }
    }
    
}