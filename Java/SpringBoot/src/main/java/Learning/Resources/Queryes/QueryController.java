package Learning.Resources.Queryes;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@EnableAsync
public class QueryController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String tableName = "users";

    private final Map<String, String> requiredColumns = Map.of(
            "id", "SERIAL PRIMARY KEY",
            "u_name", "VARCHAR(255)",
            "u_function", "VARCHAR(255)",
            "u_age", "INTEGER",
            "u_cpf", "VARCHAR(11)",
            "u_createat", "TIMESTAMP DEFAULT CURRENT_TIMESTAMP");

    @PostConstruct
    public void verifyOrCreateTable() {
        try {
            if (!tableExists()) {
                createTable();
            } else {
                verifyColumns();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error checking or creating table: " + e.getMessage());
        }
    }

    private boolean tableExists() {
        String sql = "SELECT EXISTS (" +
                "SELECT FROM information_schema.tables " +
                "WHERE table_schema = 'public' AND table_name = ?" +
                ")";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, tableName));
    }

    private void createTable() {
        StringBuilder sql = new StringBuilder("CREATE TABLE " + tableName + " (");

        requiredColumns.forEach((column, type) -> sql.append(column).append(" ").append(type).append(", "));
        sql.delete(sql.length() - 2, sql.length()); // remove trailing comma
        sql.append(");");

        try {
            jdbcTemplate.execute(sql.toString());
            System.out.println("✅ Tabela criada: " + tableName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error creating table: " + e.getMessage());
        }
    }

    private void verifyColumns() {
        String sql = "SELECT column_name FROM information_schema.columns WHERE table_name = ?";
        List<String> existingColumns = jdbcTemplate.queryForList(sql, String.class, tableName);

        requiredColumns.forEach((column, type) -> {
            if (!existingColumns.contains(column)) {
                String alterSql = "ALTER TABLE " + tableName + " ADD COLUMN " + column + " " + type + ";";
                try {
                    jdbcTemplate.execute(alterSql);
                    System.out.println("🛠️  Coluna adicionada: " + column);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("❌ Error adding column: " + column + " - " + e.getMessage());
                }
            }
        });
    }
}
