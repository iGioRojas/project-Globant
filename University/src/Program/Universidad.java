package Program;

import Interfaces.IProfesor;
import Model.Clase;
import Model.Estudiante;
import Model.ProfesorFullTime;
import Model.ProfesorPartTime;

import java.util.ArrayList;
import java.util.HashMap;

public class Universidad {

    public static String nombre = "Globant University";
    public static int identificador = 0;

    private ArrayList<IProfesor> listaProfesores;
    private ArrayList<Clase> listaClases;
    private ArrayList<Estudiante> listaEstudiantes;

    public Universidad(){
        this.listaProfesores = new ArrayList<>();
        this.listaClases = new ArrayList<>();
        this.listaEstudiantes = new ArrayList<>();
        llenarProfes();
        llenarEstudiantes();
        llenarClases();
    }


    private void llenarProfes(){
        ProfesorFullTime profe1 = new ProfesorFullTime("Profe1",25,Universidad.identificador++,3);
        ProfesorFullTime profe2 = new ProfesorFullTime("Profe2",30,Universidad.identificador++,5);
        ProfesorPartTime profe3 = new ProfesorPartTime("Profe3",20,Universidad.identificador++,15);
        ProfesorPartTime profe4 = new ProfesorPartTime("Profe4",22,Universidad.identificador++,20);
        listaProfesores.add(profe1);
        listaProfesores.add(profe2);
        listaProfesores.add(profe3);
        listaProfesores.add(profe4);
    }

    private void llenarEstudiantes(){
        Estudiante estudiante1 = new Estudiante("Estudiante1",18,Universidad.identificador++);
        Estudiante estudiante2 = new Estudiante("Estudiante2",19,Universidad.identificador++);
        Estudiante estudiante3 = new Estudiante("Estudiante3",18,Universidad.identificador++);
        Estudiante estudiante4 = new Estudiante("Estudiante4",17,Universidad.identificador++);
        Estudiante estudiante5 = new Estudiante("Estudiante5",20,Universidad.identificador++);
        Estudiante estudiante6 = new Estudiante("Estudiante6",18,Universidad.identificador++);
        listaEstudiantes.add(estudiante1);
        listaEstudiantes.add(estudiante2);
        listaEstudiantes.add(estudiante3);
        listaEstudiantes.add(estudiante4);
        listaEstudiantes.add(estudiante5);
        listaEstudiantes.add(estudiante6);
    }

    private void llenarClases(){
        IProfesor profeClase1 = listaProfesores.get(0);
        IProfesor profeClase2 = listaProfesores.get(2);
        IProfesor profeClase3 = listaProfesores.get(3);
        IProfesor profeClase4 = listaProfesores.get(1);
        ArrayList<Estudiante> estudiantesClase1 = estudiantesPorClase(3);
        ArrayList<Estudiante> estudiantesClase2 = estudiantesPorClase(4);
        ArrayList<Estudiante> estudiantesClase3 = estudiantesPorClase(2);
        ArrayList<Estudiante> estudiantesClase4 = estudiantesPorClase(6);
        Clase clase1 = new Clase("Fundamentos de programacion",1,estudiantesClase1,profeClase1,"Martes - Jueves     10h00 - 12h00");
        Clase clase2 = new Clase("Algebra lineal",2,estudiantesClase2,profeClase2,"Lunes - Miercoles     14h00 - 16h00");
        Clase clase3 = new Clase("Matematicas Discretas",3,estudiantesClase3,profeClase3,"Martes - Jueves     14h00 - 16h00");
        Clase clase4 = new Clase("Base de datos",4,estudiantesClase4,profeClase4,"Lunes - Miercoles     10h00 - 12h00");
        listaClases.add(clase1);
        listaClases.add(clase2);
        listaClases.add(clase4);
        listaClases.add(clase3);
        notificarEstudiantes(estudiantesClase1,clase1);
        notificarEstudiantes(estudiantesClase2,clase2);
        notificarEstudiantes(estudiantesClase3,clase3);
        notificarEstudiantes(estudiantesClase4,clase4);
    }

    public ArrayList<Estudiante> estudiantesPorClase(int cantidad){

        if(cantidad == this.listaEstudiantes.size()){
            return this.listaEstudiantes;
        }

        ArrayList<Estudiante> listaEstudiante = new ArrayList<>();

        int indiceRandom = (int)(Math.random()*(this.listaEstudiantes.size()-1));

        while (cantidad!=listaEstudiante.size()){

            Estudiante est = this.listaEstudiantes.get(indiceRandom);
            if(!listaEstudiante.contains(est)){
                listaEstudiante.add(est);
            }
            indiceRandom = (int)(Math.random()*this.listaEstudiantes.size()-1);
        }

        return listaEstudiante;
    }

    public ArrayList<IProfesor> getProfes(){
        return this.listaProfesores;
    }

    public ArrayList<Estudiante> getEstudiantes(){
        return this.listaEstudiantes;
    }

    public ArrayList<Clase> getClases(){
        return this.listaClases;
    }

    public void agregarClase(Clase clase){
        this.listaClases.add(clase);
    }

    public void agregarEstudiante(Estudiante estudiante){
        this.listaEstudiantes.add(estudiante);
    }

    public HashMap<Integer, Estudiante> getMapa(){
        HashMap<Integer,Estudiante> mapa = new HashMap<>();
        for (Estudiante est:this.listaEstudiantes) {
            mapa.put(est.getId(),est);
        }

        return mapa;
    }

    public void notificarEstudiantes(ArrayList<Estudiante> estudiantesClase, Clase clase) {
        for(Estudiante est:
                estudiantesClase){
            est.agregarClase(clase);
        }
    }
}
