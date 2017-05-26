package util;

/**
 * @author Michał Nowiński
 * @sience 15.05.2017
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public static final String USER = "";
    public static final String PASSWORD = "";
    public static final String DB_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";

    public static Connection getConnection() {
        Connection connection = null;

        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASSWORD);
        properties.setProperty("useSSL", "false");
        properties.setProperty("autoReconnect", "true");

        try {
            Class.forName(DB_DRIVER_CLASS);
            connection = DriverManager.getConnection(URL, properties);

//            System.out.println("AutoComit: " + connection.getAutoCommit());

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Bład połączenia!");
            e.printStackTrace();
        }
        return connection;
    }
}