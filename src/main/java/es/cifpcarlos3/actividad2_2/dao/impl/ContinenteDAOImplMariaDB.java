package es.cifpcarlos3.actividad2_2.dao.impl;

import es.cifpcarlos3.actividad2_2.dao.ContinenteDAO;
import es.cifpcarlos3.actividad2_2.dao.DatabaseConnection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.*;

@Data
@AllArgsConstructor
public class ContinenteDAOImplMariaDB implements ContinenteDAO {
    private final DatabaseConnection db;

    @Override
    public void mostrarPaisesPorContinente() {
        String consulta = "SELECT count(*) AS 'cantidad', nombre_continente " +
                "FROM t_pais p JOIN t_continente c ON c.codigo = p.cod_continente " +
                "GROUP BY nombre_continente";
        int lineas = 0;
        try(Connection conn = db.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(consulta)) {

            System.out.println("--------------------------------------------------");
            while(rs.next()) {
                System.out.println(rs.getString("nombre_continente") + ": " +  rs.getInt("cantidad"));
                lineas++;
            }
            System.out.println("Cantidad de lineas devueltas: " + lineas);
            System.out.println("--------------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
