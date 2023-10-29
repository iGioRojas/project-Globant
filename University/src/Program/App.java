package Program;

import Interfaces.IProfesor;
import Model.Clase;
import Model.Estudiante;
import Model.ProfesorFullTime;
import Model.ProfesorPartTime;

import java.util.Scanner;

public class App {
    private Universidad universidad;
    private Scanner sc;

    public App(Universidad universidad){
        this.universidad = universidad;
    }
    public void bienvenida(){
        System.out.println("######################");
        System.out.println("* "+Universidad.nombre+" *");
        System.out.println("######################");
    }

    public int menu(Scanner sc){
        this.sc = sc;
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
        presentarClases();
        System.out.println("Escriba el nombre de la materia o el número asignado:");
        String opcion = this.sc.nextLine();
        Clase clase = obtenerClase(opcion);
        if(clase !=null){
            System.out.println("-- Profesor: "+clase.getProfesor().getName());
            System.out.println("--Horario: "+clase.getHorario());
            System.out.println("-- Estudiantes");
            for (Estudiante est:
                 clase.getEstudiantes()) {
                System.out.println("Nombre: "+est.getName());
                System.out.println("Edad: "+est.getAge());
            }
        }
    }

    public void crearEstudiante() {
        System.out.println("\n#### Registrar un nuevo estudiante ####");
        System.out.print("Ingrese su nombre: ");
        String nombre = this.sc.nextLine();
        System.out.print("Ingrese su edad: ");
        int edad = this.sc.nextInt();
        int id = Universidad.identificador+1;
        Estudiante nuevoEstudiante = new Estudiante(nombre,edad,id);

        System.out.println("-- SE HA CREADO A "+nombre+" con el id "+id);

        System.out.println("## Por favor agregarlo a una clase ##");
        presentarClases();
        System.out.println("Escriba el nombre de la materia o el número asignado:");
        String opcion = this.sc.nextLine();
        Clase claseAsignada = obtenerClase(opcion);
        if(claseAsignada != null){
            this.universidad.agregarEstudiante(nuevoEstudiante);
            claseAsignada.getEstudiantes().add(nuevoEstudiante);
            System.out.println("-- ESTUDIANTE REGISTRADO");
        }else{
            System.out.println("-- NO SE HA PODIDO REGISTRAR, ERROR");
        }
    }

    public void crearClase() {
        System.out.println("\n#### Registrar una nueva materia ####");
        System.out.print("Ingrese nombre de la materia: ");
        String nombre = this.sc.nextLine();
        System.out.print("Ingrese el paralelo: ");
        int paralelo = this.sc.nextInt();
        System.out.print("Ingrese el horario: ");
        String horario = this.sc.nextLine();
        System.out.println("Asigne un profesor:");
        presentarProfes();
        int id = sc.nextInt();
        IProfesor profe = this.universidad.getProfes().get(id-1);

        Clase clase = new Clase(nombre,paralelo,this.universidad.estudiantesPorClase(this.universidad.getEstudiantes().size()),profe,horario);
        profe.agregarMateria(clase);
        clase.notificarEstudiantes();
    }

    public void buscarEstudiante() {
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
