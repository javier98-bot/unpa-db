
package mx.edu.unpa.Universidad.bll;

import java.util.List;
import mx.edu.unpa.Universidad.dal.AlumnoDAO;
import mx.edu.unpa.Universidad.el.Alumno;

public class AlumnoService {

    private final AlumnoDAO alumnoDAO;

    public AlumnoService() {
        alumnoDAO = new AlumnoDAO();
    }

    // INSERTAR ALUMNO
    public boolean insertarAlumno(Alumno alumno) {

        if (alumno == null) {
            System.out.println("El alumno no puede estar vacío.");
            return false;
        }

        if (alumno.getNombre() == null ||
            alumno.getNombre().trim().isEmpty()) {
            System.out.println("El nombre es obligatorio.");
            return false;
        }

        if (alumno.getApellido() == null ||
            alumno.getApellido().trim().isEmpty()) {
            System.out.println("El apellido es obligatorio.");
            return false;
        }

        if (alumno.getEdad() < 0) {
            System.out.println("La edad no puede ser negativa.");
            return false;
        }

        if (alumno.getCorreo() == null ||
            !alumno.getCorreo().contains("@")) {
            System.out.println("El correo no es válido.");
            return false;
        }

        return alumnoDAO.insertar(alumno);
    }

    // LISTAR ALUMNOS
    public List<Alumno> listarAlumnos() {
        return alumnoDAO.listar();
    }

    // BUSCAR ALUMNO POR ID
    public Alumno buscarAlumno(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return null;
        }

        return alumnoDAO.buscarPorId(id);
    }

    // ACTUALIZAR ALUMNO
    public boolean actualizarAlumno(Alumno alumno) {

        if (alumno == null) {
            System.out.println("El alumno no puede estar vacío.");
            return false;
        }

        if (alumno.getId() <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return false;
        }

        if (alumno.getNombre() == null ||
            alumno.getNombre().trim().isEmpty()) {
            System.out.println("El nombre es obligatorio.");
            return false;
        }

        if (alumno.getApellido() == null ||
            alumno.getApellido().trim().isEmpty()) {
            System.out.println("El apellido es obligatorio.");
            return false;
        }

        if (alumno.getEdad() < 0) {
            System.out.println("La edad no puede ser negativa.");
            return false;
        }

        if (alumno.getCorreo() == null ||
            !alumno.getCorreo().contains("@")) {
            System.out.println("El correo no es válido.");
            return false;
        }

        return alumnoDAO.actualizar(alumno);
    }

    // ELIMINAR ALUMNO POR ID
    public boolean eliminarAlumno(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return false;
        }

        return alumnoDAO.eliminar(id);
    }
}
