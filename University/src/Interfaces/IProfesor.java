package Interfaces;

import java.util.ArrayList;
import Model.Clase;

public interface IProfesor {

    double salarioBase = 450.70;
     ArrayList<Clase> materias = new ArrayList<>();


    double calcularSalario();
    void agregarMateria(Clase clase);
    String getName();
    int getAge();

}
