package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {

    private static QueryRunner runner = new QueryRunner();

    private DataBase() {
    }

    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static String getAmountForPayment() {
        var connection = getConn();
        var amount = runner.execute(connection, "SELECT amount FROM payment_entity ORDER BY created DESC LIMIT 1", new ScalarHandler<String>());
        return String.valueOf(amount);
    }

    @SneakyThrows
    public static String getStatusForPayment() {
        var connection = getConn();
        var status = runner.execute(connection, "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1", new ScalarHandler<String>());
        return String.valueOf(status);
    }

    @SneakyThrows
    public static String getStatusForCredit() {
        var connection = getConn();
        var status = runner.execute(connection, "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1", new ScalarHandler<String>());
        return String.valueOf(status);
    }
}
