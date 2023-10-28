package Model;

import Interfaces.IProfesor;

import java.util.ArrayList;

public class ProfesorPartTime extends Persona implements IProfesor {

    private int horasSemanales;


    public ProfesorPartTime(String name, int age, int id,int horasSemanales) {
        super(name, age, id);
        this.horasSemanales = horasSemanales;
    }

    @Override
    public double calcularSalario() {
        return salarioBase*horasSemanales;
    }

    @Override
    public void agregarMateria(Clase clase) {
        this.materias.add(clase);
        clase.agregarProfesor(this);
    }
}
