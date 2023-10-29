package Model;

import java.util.ArrayList;
import Interfaces.IProfesor;


public class Clase {

    private String name;
    private int paralelo;
    private ArrayList<Estudiante> estudiantes;
    private IProfesor profesor;
    private String horario;

    public Clase(String name, int paralelo, ArrayList<Estudiante> estudiantes, IProfesor profesor, String horario) {
        this.name = name;
        this.paralelo = paralelo;
        this.estudiantes = estudiantes;
        this.profesor = profesor;
        this.horario = horario;
    }

    public String getName() {
        return name;
    }

    public int getParalelo() {
        return paralelo;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public IProfesor getProfesor() {
        return profesor;
    }

    public String getHorario() {
        return horario;
    }

    public void agregarProfesor(IProfesor profesor) {
        this.profesor = profesor;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        this.estudiantes.add(estudiante);
    }

    public void notificarEstudiantes() {
        for (Estudiante est:
             this.estudiantes) {
            est.agregarClase(this);
        }
    }
}

