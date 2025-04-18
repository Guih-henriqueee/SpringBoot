package Learning.Resources.Queryes;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static Learning.Resources.Queryes.Tables.StructureMappingTable.TABLE_DEFINITIONS;

@Component
@EnableAsync
public class QueryController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void verifyOrCreateAllTables() {
        TABLE_DEFINITIONS.forEach((tableName, columns) -> {
            try {
                if (!tableExists(tableName)) {
                    createTable(tableName, columns);
                } else {
                    verifyColumns(tableName, columns);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("❌ Erro na tabela '" + tableName + "': " + e.getMessage());
            }
        });
    }

    private boolean tableExists(String tableName) {
        String sql = "SELECT EXISTS (" +
                "SELECT FROM information_schema.tables " +
                "WHERE table_schema = 'public' AND table_name = ?" +
                ")";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, tableName));
    }

    private void createTable(String tableName, Map<String, String> columns) {
        StringBuilder sql = new StringBuilder("CREATE TABLE " + tableName + " (");
        columns.forEach((column, type) -> sql.append(column).append(" ").append(type).append(", "));
        sql.delete(sql.length() - 2, sql.length());
        sql.append(");");

        jdbcTemplate.execute(sql.toString());
        System.out.println("✅ Tabela criada: " + tableName);
    }

    private void verifyColumns(String tableName, Map<String, String> columns) {
        String sql = "SELECT column_name FROM information_schema.columns WHERE table_name = ?";
        List<String> existingColumns = jdbcTemplate.queryForList(sql, String.class, tableName);

        columns.forEach((column, type) -> {
            if (!existingColumns.contains(column)) {
                String alterSql = "ALTER TABLE " + tableName + " ADD COLUMN " + column + " " + type + ";";
                try {
                    jdbcTemplate.execute(alterSql);
                    System.out.println("🛠️  Coluna adicionada: " + column + " na tabela: " + tableName);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("❌ Erro ao adicionar coluna '" + column + "' na tabela '" + tableName + "': " + e.getMessage());
                }
            }
        });
    }
}
