
package mx.edu.unpa.Universidad.dal;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.edu.unpa.Universidad.dcl.DbUtil;
import mx.edu.unpa.Universidad.el.Alumno;

public class AlumnoDAO {

    // INSERTAR ALUMNO
    public boolean insertar(Alumno alumno) {

        String sql = "{CALL sp_insertar_alumno(?,?,?,?)}";

        try {
            Connection con = DbUtil.getInstance().getConnection();

            if (con == null) {
                return false;
            }

            try (CallableStatement cs = con.prepareCall(sql)) {

                cs.setString(1, alumno.getNombre());
                cs.setString(2, alumno.getApellido());
                cs.setInt(3, alumno.getEdad());
                cs.setString(4, alumno.getCorreo());

                cs.execute();
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar alumno: " + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS LOS ALUMNOS
    public List<Alumno> listar() {

        List<Alumno> lista = new ArrayList<>();
        String sql = "{CALL sp_listar_alumnos()}";

        try {
            Connection con = DbUtil.getInstance().getConnection();

            if (con == null) {
                return lista;
            }

            try (CallableStatement cs = con.prepareCall(sql);
                 ResultSet rs = cs.executeQuery()) {

                while (rs.next()) {

                    Alumno alumno = new Alumno(
                        rs.getInt("id_alumno"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad"),
                        rs.getString("correo")
                    );

                    lista.add(alumno);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar alumnos: " + e.getMessage());
        }

        return lista;
    }

    // BUSCAR ALUMNO POR ID
    public Alumno buscarPorId(int id) {

        String sql = "{CALL sp_buscar_alumno(?)}";

        try {
            Connection con = DbUtil.getInstance().getConnection();

            if (con == null) {
                return null;
            }

            try (CallableStatement cs = con.prepareCall(sql)) {

                cs.setInt(1, id);

                try (ResultSet rs = cs.executeQuery()) {

                    if (rs.next()) {

                        return new Alumno(
                            rs.getInt("id_alumno"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getInt("edad"),
                            rs.getString("correo")
                        );
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar alumno: " + e.getMessage());
        }

        return null;
    }

    // ACTUALIZAR ALUMNO
    public boolean actualizar(Alumno alumno) {

        String sql = "{CALL sp_actualizar_alumno(?,?,?,?,?)}";

        try {
            Connection con = DbUtil.getInstance().getConnection();

            if (con == null) {
                return false;
            }

            try (CallableStatement cs = con.prepareCall(sql)) {

                cs.setInt(1, alumno.getId());
                cs.setString(2, alumno.getNombre());
                cs.setString(3, alumno.getApellido());
                cs.setInt(4, alumno.getEdad());
                cs.setString(5, alumno.getCorreo());

                cs.execute();
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar alumno: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR ALUMNO POR ID
    public boolean eliminar(int id) {

        String sql = "{CALL sp_eliminar_alumno(?)}";

        try {
            Connection con = DbUtil.getInstance().getConnection();

            if (con == null) {
                return false;
            }

            try (CallableStatement cs = con.prepareCall(sql)) {

                cs.setInt(1, id);

                cs.execute();
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar alumno: " + e.getMessage());
            return false;
        }
    }
}
