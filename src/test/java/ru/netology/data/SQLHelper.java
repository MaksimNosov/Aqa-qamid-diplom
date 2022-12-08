package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    static String url = System.getProperty("db.url");
    static String user = System.getProperty("db.user");
    static String password = System.getProperty("db.password");

//    private final static String dbUrlDefault = "jdbc:mysql://localhost:3306/db/DEFAULT";
//
//    public static String getDbUrl() {
//        String dbUrl = System.getProperty("spring.datasource.url");
//        if (dbUrl.isEmpty()) {
//            dbUrl = dbUrlDefault;
//        }
//        return dbUrl;
//    }

    @SneakyThrows
    private static Connection getConnSQL() {
//        return DriverManager.getConnection(System.getProperty("spring.datasource.url"), System.getProperty("spring.datasource.username"), System.getProperty("spring.datasource.password"));
//        return DriverManager.getConnection(System.getProperty("datasource"), "app", "pass");
        return DriverManager.getConnection(url, user, password);
    }

    @SneakyThrows
    public static String getOperationStatusOfPayment() {
        var codeSQL = "SELECT status FROM payment_entity";
        try (var conn = getConnSQL()) {
            var result = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return (result);
        }
    }

    @SneakyThrows
    public static String getOperationStatusOfCredit() {
        var codeSQL = "SELECT status FROM credit_request_entity";
        try (var conn = getConnSQL()) {
            var result = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return (result);
        }
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConnSQL();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }
}
