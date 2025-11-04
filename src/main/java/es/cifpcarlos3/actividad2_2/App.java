package es.cifpcarlos3.actividad2_2;

import es.cifpcarlos3.actividad2_2.dao.DatabaseConnection;
import es.cifpcarlos3.actividad2_2.dao.impl.ContinenteDAOImplMariaDB;
import es.cifpcarlos3.actividad2_2.dao.impl.PaisDAOImplMariaDB;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        PaisDAOImplMariaDB pDAO = new PaisDAOImplMariaDB(db);
        ContinenteDAOImplMariaDB cDAO = new ContinenteDAOImplMariaDB(db);

        String opciones = "";
        Scanner sc = new Scanner(System.in);
        while (!opciones.equals("5")) {
            System.out.println("Elige opción: ");
            System.out.println("1. Listar países sin capital");
            System.out.println("2. Nº de países por continente");
            System.out.println("3. Países de Europa");
            System.out.println("4. Capitales que empiezan por 'San'");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            opciones = sc.nextLine();
            switch (opciones) {
                case "1":
                    pDAO.mostrarPaisesSinCapital();
                    break;
                case "2":
                    cDAO.mostrarPaisesPorContinente();
                    break;
                case "3":
                    pDAO.mostrarPaisesDeEuropa();
                    break;
                case "4":
                    pDAO.mostrarCapitalesComenzandoPorSan();
                    break;
            }
        }
    }
}
