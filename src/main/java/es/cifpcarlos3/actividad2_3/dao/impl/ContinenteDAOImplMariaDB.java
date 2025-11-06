package es.cifpcarlos3.actividad2_3.dao.impl;


import es.cifpcarlos3.actividad2_3.dao.ContinenteDAO;
import es.cifpcarlos3.actividad2_3.dao.DatabaseConnection;
import es.cifpcarlos3.actividad2_3.model.Continente;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.SQLException;

@Data
@AllArgsConstructor
public class ContinenteDAOImplMariaDB implements ContinenteDAO {
    private final DatabaseConnection db;

    @Override
    public Continente obtenerContinentePorNombre(String nombre) {
        String consulta = "SELECT codigo, nombre_continente " +
                "FROM t_continente " +
                "WHERE nombre_continente = ?";
        Continente continente = null;
        try(var conn = db.getConn();
            var sentencia = conn.prepareStatement(consulta)) {
            sentencia.setString(1, nombre);
            var result = sentencia.executeQuery();
            while(result.next()) {
                continente = new Continente(result.getString("codigo"), result.getString("nombre_continente"));
            }
        } catch(SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
            e.printStackTrace();
        }
        return continente;
    }

    @Override
    public void insertarContinente(Continente continente) {
        String consulta = "INSERT INTO t_continente (codigo, nombre_continente) " +
                "VALUES (?, ?)";
        try(var conn = db.getConn();
            var sentencia = conn.prepareStatement(consulta)) {
            sentencia.setString(1, continente.getId());
            sentencia.setString(2, continente.getNombre());
            int filas = sentencia.executeUpdate();
            System.out.println("Insertado continente " + continente.getNombre() + " (" +  continente.getId() + "). Filas afectadas: " + filas);
        } catch(SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarPorCodigo(String codigo) {
        String consulta = "DELETE FROM t_continente WHERE codigo = ?";
        try(var conn = db.getConn();
            var sentencia = conn.prepareStatement(consulta)) {
            sentencia.setString(1, codigo);

            PaisDAOImplMariaDB pDAO = new PaisDAOImplMariaDB(db);
            pDAO.eliminarPorCodigoContinente(codigo);

            int filas = sentencia.executeUpdate();
            System.out.println("Borrados continente de codigo " + codigo + ". Filas afectadas: " + filas);
        } catch(SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
            e.printStackTrace();
        }
    }
}
