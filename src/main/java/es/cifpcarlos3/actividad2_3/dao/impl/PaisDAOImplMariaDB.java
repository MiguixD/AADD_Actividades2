package es.cifpcarlos3.actividad2_3.dao.impl;


import es.cifpcarlos3.actividad2_3.dao.ContinenteDAO;
import es.cifpcarlos3.actividad2_3.dao.DatabaseConnection;
import es.cifpcarlos3.actividad2_3.dao.PaisDAO;
import es.cifpcarlos3.actividad2_3.model.Continente;
import es.cifpcarlos3.actividad2_3.model.Pais;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PaisDAOImplMariaDB implements PaisDAO {
    private final DatabaseConnection db;

    @Override
    public List<Pais> obtenerPaisesPorContinenteConCapitalComenzandoPor(Continente continente, String capital) {
        List<Pais> paises = new ArrayList<>();
        String consulta = "SELECT " +
                "p.identificador, " +
                "p.nombre_pais, " +
                "p.capital, " +
                "c.codigo " +
                "AS cod_continente, " +
                "c.nombre_continente AS nombre_continente " +
                "FROM t_pais p " +
                "JOIN t_continente c ON p.cod_continente = c.codigo " +
                "WHERE c.nombre_continente = ? " +
                "AND p.capital LIKE ? " +
                "ORDER BY p.nombre_pais";
        try(var conn = db.getConn();
            var sentencia = conn.prepareStatement(consulta)) {
            sentencia.setString(1, continente.getNombre());
            sentencia.setString(2, capital + "%");
            var result = sentencia.executeQuery();

            while (result.next()) {
                Pais pais = new Pais(result.getInt("p.identificador"),
                                     result.getString("p.nombre_pais"),
                                     result.getString("p.capital"),
                                     continente);
                paises.add(pais);
            }

        } catch(SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
            e.printStackTrace();
        }
        return paises;
    }

    @Override
    public void actualizarCapitalPorId(int id, String capital) {
        String consulta = "UPDATE t_pais " +
                "SET capital = ? " +
                "WHERE identificador = ?";
        try(var conn = db.getConn();
            var sentencia = conn.prepareStatement(consulta)) {
            sentencia.setString(1, capital);
            sentencia.setInt(2, id);
            int filas = sentencia.executeUpdate();
            System.out.println("Actualizado país id = " + id + ". Filas afectadas: " + filas);
        } catch(SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarPorCodigoContinente(String codigoContinente) {
        String consulta = "DELETE FROM t_pais WHERE cod_continente = ?";
        try(var conn = db.getConn();
            var sentencia = conn.prepareStatement(consulta)) {
            sentencia.setString(1, codigoContinente);
            int filas = sentencia.executeUpdate();
            System.out.println("Borrados paises con continente " + codigoContinente + ". Filas afectadas: " + filas);
        } catch(SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
            e.printStackTrace();
        }
    }
}