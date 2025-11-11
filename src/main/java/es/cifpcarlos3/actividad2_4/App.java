package es.cifpcarlos3.actividad2_4;



import es.cifpcarlos3.actividad2_4.dao.impl.ClienteDAOImplMariaDB;
import es.cifpcarlos3.actividad2_4.dao.impl.CuentaDAOImplMariaDB;
import es.cifpcarlos3.actividad2_4.model.Cliente;
import es.cifpcarlos3.actividad2_4.model.Cuenta;
import es.cifpcarlos3.actividad2_4.util.DatabaseConnection;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        ClienteDAOImplMariaDB clienteDAO = new ClienteDAOImplMariaDB(db);
        CuentaDAOImplMariaDB cuentaDAO = new CuentaDAOImplMariaDB(db);


        int opciones = 999;
        Scanner sc = new Scanner(System.in);
        while (opciones != 0) {
            mostrarMenu();
            opciones = leerInt(sc);
            switch (opciones) {
                case 1:
                    List<Cliente> clientes = clienteDAO.obtenerClientes();
                    for(Cliente cliente:clientes) {
                        System.out.println(cliente.toString());
                    }
                    System.out.println("(" + clientes.size() + " Clientes)");
                    break;
                case 2:
                    List<Cuenta> cuentas = cuentaDAO.obtenerCuentas();
                    for(Cuenta cuenta:cuentas) {
                        System.out.println(cuenta.toString());
                    }
                    System.out.println("(" + cuentas.size() + " Cuentas)");
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println("==================================================================================================");
        System.out.println("Elige opción: ");
        System.out.println("1) Listar todos los clientes");
        System.out.println("2) Listar todas las cuentas con su titular");
        System.out.println("3) Insertar nuevo cliente");
        System.out.println("4) Insertar nueva cuenta para un cliente");
        System.out.println("5) Actualizar saldo de una cuenta");
        System.out.println("6) Transferir saldo entre dos cuentas");
        System.out.println("7) Eliminar cliente (si no tiene cuentas)");
        System.out.println("0) Salir");
        System.out.print("Opción: ");
    }

    public static int leerInt(Scanner sc) {
        int num = 999;
        try {
            num = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Introduce int");
        }
        return num;
    }

    public static BigDecimal leerBigDecimal(Scanner sc) {
        BigDecimal numDecimal = null;
        try {
            numDecimal = sc.nextBigDecimal();
        } catch (NumberFormatException e) {
            System.out.println("Introduce decimal");
        }
        return numDecimal;
    }
}
