package es.cifpcarlos3.actividad2_4.dao.impl;

import es.cifpcarlos3.actividad2_4.dao.CuentaDAO;
import es.cifpcarlos3.actividad2_4.model.Cliente;
import es.cifpcarlos3.actividad2_4.model.Cuenta;
import es.cifpcarlos3.actividad2_4.util.DatabaseConnection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CuentaDAOImplMariaDB implements CuentaDAO {
    private final DatabaseConnection db;


    @Override
    public List<Cuenta> obtenerCuentas() {
        List<Cuenta> cuentas = new ArrayList<>();

        String consulta = "SELECT id_cuenta, numero_cuenta, id_cliente, saldo " +
                "FROM t_cuenta";
        try(var conn = db.getConn();
            var sentencia = conn.createStatement();
            var result =  sentencia.executeQuery(consulta)) {

            while(result.next()) {

                ClienteDAOImplMariaDB clienteDAO = new ClienteDAOImplMariaDB(db);
                Cliente cliente = clienteDAO.obtenerClientePorId(result.getInt("id_cliente"));

                cuentas.add(new Cuenta(result.getInt("id_cuenta"),
                        result.getString("numero_cuenta"),
                        cliente,
                        BigDecimal.valueOf(result.getLong("saldo"))));
            }

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
        }

        return cuentas;
    }
}
