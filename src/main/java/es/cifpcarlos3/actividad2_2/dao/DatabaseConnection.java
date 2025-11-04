package es.cifpcarlos3.actividad2_2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL  = "jdbc:mariadb://localhost:3306/mapa_mundi";
    private static final String USER = "root";
    private static final String PWD  = "";
    public Connection getConn () throws SQLException {
        return DriverManager.getConnection(URL, USER, PWD);
    }
}
