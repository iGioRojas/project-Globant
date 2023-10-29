package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Estudiante extends Persona{

    private ArrayList<Clase> materias = new ArrayList<>();
    public Estudiante(String name, int age, int id) {
        super(name, age, id);
    }

    public void agregarClase(Clase clase){
        this.materias.add(clase);
    }

    public ArrayList<Clase> getClases() {
        return this.materias;
    }
}
