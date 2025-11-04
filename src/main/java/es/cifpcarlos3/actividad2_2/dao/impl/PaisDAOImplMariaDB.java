package es.cifpcarlos3.actividad2_2.dao.impl;

import es.cifpcarlos3.actividad2_2.dao.DatabaseConnection;
import es.cifpcarlos3.actividad2_2.dao.PaisDAO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.*;

@Data
@AllArgsConstructor
public class PaisDAOImplMariaDB implements PaisDAO {
    private final DatabaseConnection db;

    @Override
    public void mostrarPaisesSinCapital() {
        String consulta = "SELECT nombre_pais FROM t_pais WHERE capital IS NULL";
        int lineas = 0;
        try(Connection conn = db.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(consulta)) {

            System.out.println("--------------------------------------------------");
            while(rs.next()) {
                System.out.println(rs.getString("nombre_pais"));
                lineas++;
            }
            System.out.println("Cantidad de lineas leidas: " + lineas);
            System.out.println("--------------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void mostrarPaisesDeEuropa() {
        String consulta = "SELECT nombre_pais " +
                "FROM t_pais " +
                "WHERE cod_continente = (SELECT codigo " +
                "FROM t_continente " +
                "WHERE nombre_continente = 'Europa')";
        int lineas = 0;
        try(Connection conn = db.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(consulta)) {

            System.out.println("--------------------------------------------------");
            while(rs.next()) {
                System.out.println(rs.getString("nombre_pais"));
                lineas++;
            }
            System.out.println("Cantidad de lineas leidas: " + lineas);
            System.out.println("--------------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void mostrarCapitalesComenzandoPorSan() {
        String consulta = "SELECT capital FROM t_pais WHERE capital LIKE 'San%'";
        int lineas = 0;
        try(Connection conn = db.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(consulta)) {

            System.out.println("--------------------------------------------------");
            while(rs.next()) {
                System.out.println(rs.getString("capital"));
                lineas++;
            }
            System.out.println("Cantidad de lineas leidas: " + lineas);
            System.out.println("--------------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
