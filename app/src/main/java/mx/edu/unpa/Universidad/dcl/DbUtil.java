

package mx.edu.unpa.Universidad.dcl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class DbUtil {

    private static final DbUtil instance = new DbUtil();
    private Connection conexion;

    private DbUtil() {
        conexion = null;
    }

    public static DbUtil getInstance() {
        return instance;
    }

    public Connection getConnection() {

        try {
            if (conexion == null || conexion.isClosed()) {

                Properties propiedades = new Properties();

                // Buscar db.properties dentro de resources
                InputStream archivo =
                    DbUtil.class.getResourceAsStream("/db.properties");

                if (archivo == null) {
                    throw new IOException(
                        "No se encontro db.properties en resources"
                    );
                }

                try (InputStream entrada = archivo) {
                    propiedades.load(entrada);
                }

                String url = propiedades.getProperty("url");
                String usuario = propiedades.getProperty("user");
                String password = propiedades.getProperty("password");
                String driver = propiedades.getProperty("driver");

                // Comprobar que existan los datos
                if (url == null || usuario == null ||
                    password == null || driver == null) {
                    throw new IOException(
                        "Faltan propiedades en db.properties"
                    );
                }

                // Cargar el controlador de MySQL
                Class.forName(driver);

                // Conectar con MySQL
                conexion = DriverManager.getConnection(
                    url, usuario, password
                );

                System.out.println("Conexion exitosa a MySQL");
            }

        } catch (IOException | ClassNotFoundException |
                 SQLException e) {

            System.out.println(
                "Error de conexion: " + e.getMessage()
            );

            return null;
        }

        return conexion;
    }
}
