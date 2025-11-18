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
import java.util.Scanner;

import static es.cifpcarlos3.actividad2_4.App.leerBigDecimal;
import static es.cifpcarlos3.actividad2_4.App.leerInt;

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

    @Override
    public int obtenerMaxIdCuenta() {
        int id = 0;
        String consulta = "SELECT MAX(id_cuenta) AS 'max_id' " +
                "FROM t_cuenta";
        try(var conn = db.getConn();
            var sentencia = conn.createStatement();
            var result =  sentencia.executeQuery(consulta)) {

            while(result.next()) {
                id = result.getInt("max_id");
            }

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
        }

        return id;
    }

    @Override
    public void insertarCuenta() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID del cliente: ");
        int idCliente = leerInt(sc);
        System.out.print("Número de cuenta: ");
        String numCuenta = sc.nextLine();
        System.out.print("Saldo inicial: ");
        BigDecimal saldoInicial = leerBigDecimal(sc);

        int id = obtenerMaxIdCuenta() + 1;

        String consulta = "INSERT INTO t_cuenta (id_cuenta, numero_cuenta, id_cliente, saldo) " +
                "VALUES (?, ?, ?, ?)";

        try(var conn = db.getConn();
            var sentencia = conn.prepareStatement(consulta)) {

            sentencia.setInt(1, id);
            sentencia.setString(2, numCuenta);
            sentencia.setInt(3, idCliente);
            sentencia.setBigDecimal(4, saldoInicial);
            int filas = sentencia.executeUpdate();

            System.out.println("Cuenta insertada correctamente. Filas afectadas: " + filas);

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
        }
    }
}
