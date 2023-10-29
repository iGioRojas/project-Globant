import Program.App;
import Program.Universidad;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Universidad universidad = new Universidad();
        App app = new App(universidad);
        app.bienvenida();
        int opcion = app.menu();
        while (opcion != 6) {
            switch (opcion) {
                case 1:
                    app.infoProfes();
                    break;
                case 2:
                    app.infoClases();
                    break;
                case 3:
                    app.crearEstudiante();
                    break;
                case 4:
                    app.crearClase();
                    break;
                case 5:
                    app.buscarEstudiante();
                    break;
                default:
                    System.out.println("***Ingrese una opcion valida\n\n");
                    opcion = app.menu();
                    break;
            }
            System.out.println("---------------------------------------------");
            opcion = app.menu();

        }
            app.salir();
    }
}