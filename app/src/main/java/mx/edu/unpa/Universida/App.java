
package mx.edu.unpa.Universida;

import javax.swing.SwingUtilities;
import mx.edu.unpa.Universidad.pl.FrmAlumnos;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmAlumnos frame = new FrmAlumnos();
            frame.setVisible(true);
        });
    }
}
