package Model;

import Interfaces.IProfesor;

import java.util.ArrayList;

public class ProfesorFullTime extends Persona implements IProfesor {
    private int aniosExperiencia;

    public ProfesorFullTime(String name, int age, int id,int aniosExperiencia) {
        super(name, age, id);
        this.aniosExperiencia = aniosExperiencia;
    }

    @Override
    public double calcularSalario() {
        return salarioBase * (aniosExperiencia*1.10);
    }

    @Override
    public void agregarMateria(Clase clase) {
        this.materias.add(clase);
        clase.agregarProfesor(this);
    }
}
