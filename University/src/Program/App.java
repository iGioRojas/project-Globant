package Program;

import Interfaces.IProfesor;
import Model.Clase;
import Model.Estudiante;
import Model.ProfesorFullTime;
import Model.ProfesorPartTime;

import java.util.ArrayList;
import java.util.HashMap;
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

    public int menu(){
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("****** MENU *****");
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
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("\n#### Información de las clases y sus datos ####");
        System.out.println("Total materias: "+this.universidad.getClases().size());
        presentarClases();
        System.out.println("Escriba el nombre de la materia o el número asignado:");
        String opcion = sc.nextLine();
        Clase clase = obtenerClase(opcion);
        if(clase !=null){
            System.out.println("-- Profesor: "+clase.getProfesor().getName());
            System.out.println("-- Horario: "+clase.getHorario());
            System.out.println("-- Estudiantes");
            for (Estudiante est:
                 clase.getEstudiantes()) {
                System.out.println("\tNombre: "+est.getName());
                System.out.println("\tEdad: "+est.getAge());
                System.out.print("\n");
            }
        }
    }

    public void crearEstudiante() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("\n#### Registrar un nuevo estudiante ####");
        System.out.print("Ingrese su nombre: ");
        String nombre = sc.next();
        System.out.print("Ingrese su edad: ");
        int edad = sc.nextInt();
        int id = Universidad.identificador++;
        Estudiante nuevoEstudiante = new Estudiante(nombre,edad,id);

        System.out.println("-- SE HA CREADO A "+nombre+" con el id "+id);

        System.out.println("## Por favor agregarlo a una clase ##");
        presentarClases();
        System.out.println("Escriba el nombre de la materia o el número asignado:");
        String option = sc.next();
        Clase claseAsignada = obtenerClase(option);
        if(claseAsignada != null){
            this.universidad.agregarEstudiante(nuevoEstudiante);
            claseAsignada.getEstudiantes().add(nuevoEstudiante);
            System.out.println("-- ESTUDIANTE REGISTRADO");
        }else{
            System.out.println("-- NO SE HA PODIDO REGISTRAR, ERROR");
        }
    }

    public void crearClase() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("\n#### Registrar una nueva materia ####");
        System.out.print("Ingrese nombre de la materia: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese el paralelo: ");
        int paralelo = sc.nextInt();
        System.out.print("Ingrese el horario: ");
        String horario = sc.nextLine();
        System.out.println("Asigne un profesor:");
        presentarProfes();
        int id = sc.nextInt();
        IProfesor profe = this.universidad.getProfes().get(id-1);
        ArrayList<Estudiante> estudiantesClase = this.universidad.estudiantesPorClase(this.universidad.getEstudiantes().size());

        Clase clase = new Clase(nombre,paralelo,estudiantesClase,profe,horario);
        profe.agregarMateria(clase);
        this.universidad.agregarClase(clase);
        this.universidad.notificarEstudiantes(estudiantesClase,clase);
    }

    public void buscarEstudiante() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        HashMap<Integer,Estudiante> mapaEstudiantes = this.universidad.getMapa();
        System.out.println("\n#### Buscar Estudiante ####");
        System.out.print("Ingrese el id del estudiante: ");
        int id = sc.nextInt();
        Estudiante est = mapaEstudiantes.get(id);
        if (est != null) {
            System.out.println("Nombre estudiante: "+est.getName());
            System.out.println("Materias registradas:");
            for (Clase clase:
                    est.getClases()) {
                System.out.println("- "+clase.getName() +"\tParalelo: "+clase.getParalelo());
            }
        }else{
            System.out.println("-- ID NO REGISTRADO");
                mostrarEstudiantes();
        }

    }

    private void mostrarEstudiantes() {
        for (Estudiante est:
             this.universidad.getEstudiantes()) {
            System.out.println("ID: "+est.getId());
            System.out.println("Nombre: "+est.getName());
        }
    }

    private void presentarClases(){
        for(int i = 0; i < this.universidad.getClases().size();i++){
            Clase clase = this.universidad.getClases().get(i);
            System.out.println((i+1)+". "+clase.getName());
        }
    }

    private Clase obtenerClase(String opcion){
        if(esEntero(opcion)){
            int index = Integer.parseInt(opcion)-1;
            if(index < this.universidad.getClases().size()){
                return this.universidad.getClases().get(index);
            }
        }else{
            for(Clase clase: this.universidad.getClases()){
                if(opcion.equals(clase.getName())){
                    return clase;
                }
            }
        }

        return null;
    }

    private boolean esEntero(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void presentarProfes(){
        for (int i = 0; i < this.universidad.getProfes().size();i++) {
            IProfesor profe = this.universidad.getProfes().get(i);
            System.out.println((i+1)+". "+profe.getName());
        }
    }

}
