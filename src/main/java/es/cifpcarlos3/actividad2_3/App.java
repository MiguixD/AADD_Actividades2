package es.cifpcarlos3.actividad2_3;

import es.cifpcarlos3.actividad2_3.dao.DatabaseConnection;
import es.cifpcarlos3.actividad2_3.dao.impl.ContinenteDAOImplMariaDB;
import es.cifpcarlos3.actividad2_3.dao.impl.PaisDAOImplMariaDB;
import es.cifpcarlos3.actividad2_3.model.Continente;
import es.cifpcarlos3.actividad2_3.model.Pais;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        PaisDAOImplMariaDB pDAO = new PaisDAOImplMariaDB(db);
        ContinenteDAOImplMariaDB cDAO = new ContinenteDAOImplMariaDB(db);

        String opciones = "";
        Scanner sc = new Scanner(System.in);
        while (!opciones.equals("5")) {
            System.out.println("==================================================================================================");
            System.out.println("Elige opción: ");
            System.out.println("1. Listar países del continente América con capital que empiece por 'Sa'");
            System.out.println("2. Insertar nuevo continente 'Antártida'");
            System.out.println("3. Actualizar capital del país con id 107 -> 'Capital City'");
            System.out.println("4. Eliminar continente con código '06'");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            opciones = sc.nextLine();
            switch (opciones) {
                case "1":
                    Continente continente = cDAO.obtenerContinentePorNombre("América");
                    List<Pais> paises = pDAO.obtenerPaisesPorContinenteConCapitalComenzandoPor(continente, "Sa");
                    for(Pais p : paises) {
                        System.out.println(p.getCapital() + " capital de " + p.getNombre() + " (" + p.getId() + ") pertenece al continente " +
                                p.getContinente().getNombre() + " (" + p.getContinente().getId() + ")");
                    }
                    break;
                case "2":
                    cDAO.insertarContinente(new Continente("06", "Antártida"));
                    break;
                case "3":
                    pDAO.actualizarCapitalPorId(107, "Capital City");
                    break;
                case "4":
                    cDAO.eliminarPorCodigo("06");
                    break;
            }
        }
    }
}
