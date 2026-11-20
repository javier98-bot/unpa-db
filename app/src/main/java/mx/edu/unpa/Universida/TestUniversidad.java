
package mx.edu.unpa.Universida;

import java.util.List;
import mx.edu.unpa.Universidad.bll.AlumnoService;
import mx.edu.unpa.Universidad.el.Alumno;



public class TestUniversidad {

    public static void main(String[] args) {

        AlumnoService servicio = new AlumnoService();

        // 1. INSERTAR ALUMNO
        Alumno nuevo = new Alumno(
            0,
            "Carlos",
            "Hernandez",
            20,
            "carlos@gmail.com"
        );

        boolean insertado = servicio.insertarAlumno(nuevo);

        if (insertado) {
            System.out.println("Alumno insertado correctamente.");
        } else {
            System.out.println("No se pudo insertar el alumno.");
        }

        // 2. LISTAR ALUMNOS
        System.out.println("\n--- LISTA DE ALUMNOS ---");

        List<Alumno> lista = servicio.listarAlumnos();

        for (Alumno alumno : lista) {
            System.out.println(alumno);
        }

        // 3. BUSCAR ALUMNO POR ID
        System.out.println("\n--- BUSCAR ALUMNO ---");

        Alumno encontrado = servicio.buscarAlumno(1);

        if (encontrado != null) {
            System.out.println("Alumno encontrado:");
            System.out.println(encontrado);
        } else {
            System.out.println("No se encontró el alumno.");
        }
    }
}
