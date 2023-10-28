package Program;

import Interfaces.IProfesor;
import Model.Clase;
import Model.ProfesorFullTime;
import Model.ProfesorPartTime;

import java.util.Scanner;

public class App {
    private Universidad universidad;

    public App(Universidad universidad){
        this.universidad = universidad;
    }
    public void bienvenida(){
        System.out.println("######################");
        System.out.println("* "+Universidad.nombre+" *");
        System.out.println("######################");
    }

    public int menu(Scanner sc){
        System.out.println("1. Informacion de profesores\n" +
                "2. Informacion de clases\n" +
                "3. Crear un nuevo estudiante\n" +
                "4. Crear una nueva clase\n" +
                "5. Buscar clase por estudiante\n" +
                "6. Salir");
        System.out.print("Ingrese una opcion: ");
        int opcion = sc.nextInt();
        return opcion;
    }

    public void salir(){
        System.out.println("Saliste de la aplicacion de "+Universidad.nombre);
    }


    public void infoProfes() {
        System.out.println("\n#### Información de los profes y sus datos ####");
        System.out.println("Profesores registrados: "+this.universidad.getProfes().size());
        for (int i = 0; i < this.universidad.getProfes().size();i++){
            IProfesor profe = this.universidad.getProfes().get(i);
            System.out.println("\nProfesor "+(i+1));
            System.out.println("Nombre: "+profe.getName());
            System.out.println("\tEdad: "+profe.getAge());
            System.out.println("\tSalario: $"+profe.calcularSalario()+"\n");
        }
    }

    public void infoClases() {
        System.out.println("\n#### Información de las clases y sus datos ####");
        System.out.println("Total materias: "+this.universidad.getClases().size());
        for(int i = 0; i < this.universidad.getClases().size();i++){
            Clase clase = this.universidad.getClases().get(i);
            System.out.println((i+1)+". "+clase.getName());
        }

        System.out.println("Escriba el nombre de la materia o el número asignado:");
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        String opcion = sc.nextLine();
        //TO DO HACER LA BUSQUEDA POR MATERIA O NUMERO
    }

    public void crearEstudiante() {
    }

    public void crearClase() {
    }

    public void buscarEstudiante() {
    }
}
