package Learning.Resources.Queryes.Tables;

import java.util.Map;

public class StructureMappingTable {

    public static final String TABLE_USERS = "users";
    public static final Map<String, String> COLUMNS_USERS = Map.of(
        "id", "SERIAL PRIMARY KEY",
        "u_name", "VARCHAR(255)",
        "u_function", "VARCHAR(255)",
        "u_age", "INTEGER",
        "u_cpf", "VARCHAR(11)",
        "u_createat", "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    );

    public static final String TABLE_ROLES = "roles";
    public static final Map<String, String> COLUMNS_ROLES = Map.of(
        "id", "SERIAL PRIMARY KEY",
        "role_name", "VARCHAR(255)",
        "description", "TEXT",
        "budget_role", "DECIMAL(10,2)",
        "created_at", "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    );

    public static final String TABLE_PRODUCTS = "products";
    public static final Map<String, String> COLUMNS_PRODUCTS = Map.of(
        "id", "SERIAL PRIMARY KEY",
        "product_name", "VARCHAR(255)",
        "price", "DECIMAL(10,2)",
        "stock", "INTEGER",
        "created_at", "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    );


     public static final Map<String, Map<String, String>> TABLE_DEFINITIONS = Map.of(
        TABLE_USERS, COLUMNS_USERS,
        TABLE_ROLES, COLUMNS_ROLES,
        TABLE_PRODUCTS, COLUMNS_PRODUCTS
    );


}
