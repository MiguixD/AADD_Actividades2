package es.cifpcarlos3.actividad2_4.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL  = "jdbc:mariadb://localhost:3306/banco";
    private static final String USER = "root";
    private static final String PWD  = "";
    public Connection getConn () throws SQLException {
        return DriverManager.getConnection(URL, USER, PWD);
    }
}
