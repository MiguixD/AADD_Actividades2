package es.cifpcarlos3.actividad2_4.dao.impl;

import es.cifpcarlos3.actividad2_4.App;
import es.cifpcarlos3.actividad2_4.dao.ClienteDAO;
import es.cifpcarlos3.actividad2_4.model.Cliente;
import es.cifpcarlos3.actividad2_4.util.DatabaseConnection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@AllArgsConstructor
public class ClienteDAOImplMariaDB implements ClienteDAO {
    private final DatabaseConnection db;

    @Override
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String consulta = "SELECT id_cliente, dni, nombre, telefono, email " +
                "FROM t_cliente";
        try(var conn = db.getConn();
            var sentencia = conn.createStatement();
            var result =  sentencia.executeQuery(consulta)) {

            while(result.next()) {
                clientes.add(new Cliente(result.getInt("id_cliente"),
                        result.getString("dni"),
                        result.getString("nombre"),
                        result.getString("telefono"),
                        result.getString("email")));
            }

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
        }

        return clientes;
    }

    @Override
    public Cliente obtenerClientePorId(int id) {
        Cliente cliente = null;
        String consulta = "SELECT id_cliente, dni, nombre, telefono, email " +
                "FROM t_cliente " +
                "WHERE id_cliente = ?";
        try(var conn = db.getConn();
            var sentencia = conn.prepareStatement(consulta)) {

            sentencia.setInt(1, id);
            var result = sentencia.executeQuery();

            while(result.next()) {
                cliente = new Cliente(result.getInt("id_cliente"),
                        result.getString("dni"),
                        result.getString("nombre"),
                        result.getString("telefono"),
                        result.getString("email"));
            }

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
        }

        return cliente;
    }

    @Override
    public int obtenerMaxId() {
        int id = 0;
        String consulta = "SELECT MAX(id_cliente) AS 'max_id' " +
                "FROM t_cliente";
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
    public void insertarCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Telefono: ");
        String telefono = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        int id = obtenerMaxId() + 1;

        String consulta = "INSERT INTO t_cliente (id_cliente, dni, nombre, telefono, email) " +
                "VALUES (?, ?, ?, ?, ?)";

        try(var conn = db.getConn();
            var sentencia = conn.prepareStatement(consulta)) {

            sentencia.setInt(1, id);
            sentencia.setString(2, dni);
            sentencia.setString(3, nombre);
            sentencia.setString(4, telefono);
            sentencia.setString(5, email);
            int filas = sentencia.executeUpdate();

            System.out.println("Cliente insertado correctamente. Filas afectadas: " + filas);

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
        }
    }

}
