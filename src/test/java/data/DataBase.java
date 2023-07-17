package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    private static QueryRunner runner = new QueryRunner();

    private DataBase() {
    }

    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static String getAmount() {
        var connection = getConn();
        var amount = runner.execute(connection, "SELECT amount FROM app.payment_entity ORDER BY created DESC LIMIT 1");
        return String.valueOf(amount);
//        runner.execute(connection, "DELETE FROM card_transactions");
//        runner.execute(connection, "DELETE FROM cards");
//        runner.execute(connection, "DELETE FROM users");
    }

//    public static DataHelper.AmountSum getInfo() {
//        var codeSQL = "SELECT amount, status, created FROM app.payment_entity ORDER BY created DESC LIMIT 1";
//        try (var conn = getConn()) {
//            var amount = runner.query(conn, codeSQL, new ScalarHandler<String>());
//            return new DataHelper.AmountSum(amount);
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }
//        return null;
//    }



}
