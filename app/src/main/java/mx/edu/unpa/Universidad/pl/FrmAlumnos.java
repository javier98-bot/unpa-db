
package mx.edu.unpa.Universidad.pl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import mx.edu.unpa.Universidad.bll.AlumnoService;
import mx.edu.unpa.Universidad.el.Alumno;

public class FrmAlumnos extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEdad;
    private JTextField txtCorreo;

    private JTable tabla;
    private DefaultTableModel modelo;

    private final AlumnoService servicio = new AlumnoService();

    // COLORES DEL SISTEMA
    private final Color AZUL = new Color(23, 54, 93);
    private final Color AZUL_CLARO = new Color(37, 99, 180);
    private final Color FONDO = new Color(242, 246, 251);
    private final Color BLANCO = Color.WHITE;
    private final Color VERDE = new Color(22, 128, 76);
    private final Color ROJO = new Color(190, 45, 45);
    private final Color GRIS = new Color(85, 98, 115);

    public FrmAlumnos() {

        setTitle("UNPA | Sistema de Control Escolar");
        setSize(1050, 700);
        setMinimumSize(new Dimension(850, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // PANEL PRINCIPAL
        JPanel principal = new JPanel(new BorderLayout(0, 18));
        principal.setBackground(FONDO);
        principal.setBorder(
            BorderFactory.createEmptyBorder(20, 22, 20, 22)
        );

        // ENCABEZADO
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(AZUL);
        encabezado.setBorder(
            BorderFactory.createEmptyBorder(18, 22, 18, 22)
        );

        JLabel titulo = new JLabel("UNPA | CONTROL ESCOLAR");
        titulo.setForeground(BLANCO);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 25));

        JLabel subtitulo = new JLabel(
            "Sistema de gestión y registro de alumnos"
        );
        subtitulo.setForeground(new Color(220, 230, 243));
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel textos = new JPanel(new BorderLayout(0, 7));
        textos.setBackground(AZUL);
        textos.add(titulo, BorderLayout.NORTH);
        textos.add(subtitulo, BorderLayout.SOUTH);

        encabezado.add(textos, BorderLayout.CENTER);

        // FORMULARIO
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(BLANCO);
        formulario.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(220, 226, 235)
                ),
                BorderFactory.createEmptyBorder(16, 18, 16, 18)
            )
        );

        txtId = crearCampo();
        txtNombre = crearCampo();
        txtApellido = crearCampo();
        txtEdad = crearCampo();
        txtCorreo = crearCampo();

        agregarCampo(formulario, "ID del alumno", txtId, 0, 0);
        agregarCampo(formulario, "Nombre", txtNombre, 1, 0);
        agregarCampo(formulario, "Apellido", txtApellido, 0, 1);
        agregarCampo(formulario, "Edad", txtEdad, 1, 1);
        agregarCampo(formulario, "Correo electrónico", txtCorreo, 0, 2);

        // TABLA
        modelo = new DefaultTableModel(
            new String[]{
                "ID", "Nombre", "Apellido", "Edad", "Correo electrónico"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabla.setRowHeight(30);
        tabla.setForeground(new Color(45, 55, 72));
        tabla.setBackground(BLANCO);
        tabla.setSelectionBackground(new Color(215, 230, 250));
        tabla.setSelectionForeground(AZUL);
        tabla.setShowVerticalLines(false);
        tabla.setGridColor(new Color(232, 236, 242));
        tabla.setFillsViewportHeight(true);
        tabla.setAutoCreateRowSorter(true);

        JTableHeader cabecera = tabla.getTableHeader();
        cabecera.setFont(new Font("SansSerif", Font.BOLD, 13));
        cabecera.setBackground(AZUL);
        cabecera.setForeground(BLANCO);
        cabecera.setPreferredSize(new Dimension(0, 36));
        cabecera.setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(
            BorderFactory.createLineBorder(
                new Color(220, 226, 235)
            )
        );
        scroll.getViewport().setBackground(BLANCO);

        JPanel panelTabla = new JPanel(new BorderLayout(0, 10));
        panelTabla.setBackground(FONDO);

        JLabel tituloTabla = new JLabel("LISTA DE ALUMNOS");
        tituloTabla.setFont(new Font("SansSerif", Font.BOLD, 17));
        tituloTabla.setForeground(AZUL);

        panelTabla.add(tituloTabla, BorderLayout.NORTH);
        panelTabla.add(scroll, BorderLayout.CENTER);

        // ZONA CENTRAL: FORMULARIO Y TABLA
        JPanel contenido = new JPanel(new BorderLayout(0, 16));
        contenido.setBackground(FONDO);
        contenido.add(formulario, BorderLayout.NORTH);
        contenido.add(panelTabla, BorderLayout.CENTER);

        // BOTONES
        JButton btnAgregar = crearBoton("Agregar", VERDE);
        JButton btnMostrar = crearBoton("Mostrar", AZUL_CLARO);
        JButton btnBuscar = crearBoton("Buscar", AZUL);
        JButton btnActualizar = crearBoton(
            "Actualizar", new Color(117, 79, 177)
        );
        JButton btnEliminar = crearBoton("Eliminar", ROJO);
        JButton btnLimpiar = crearBoton("Limpiar", GRIS);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(
            new java.awt.FlowLayout(
                java.awt.FlowLayout.CENTER, 10, 5
            )
        );
        panelBotones.setBackground(FONDO);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        // ORGANIZAR VENTANA
        principal.add(encabezado, BorderLayout.NORTH);
        principal.add(contenido, BorderLayout.CENTER);
        principal.add(panelBotones, BorderLayout.SOUTH);

        setContentPane(principal);

        // EVENTOS DE LOS BOTONES
        btnAgregar.addActionListener(e -> agregarAlumno());
        btnMostrar.addActionListener(e -> cargarAlumnos());
        btnBuscar.addActionListener(e -> buscarAlumno());
        btnActualizar.addActionListener(e -> actualizarAlumno());
        btnEliminar.addActionListener(e -> eliminarAlumno());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // SELECCIONAR ALUMNO DESDE LA TABLA
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()
                    && tabla.getSelectedRow() != -1) {

                int filaVista = tabla.getSelectedRow();
                int fila = tabla.convertRowIndexToModel(filaVista);

                txtId.setText(
                    modelo.getValueAt(fila, 0).toString()
                );
                txtNombre.setText(
                    modelo.getValueAt(fila, 1).toString()
                );
                txtApellido.setText(
                    modelo.getValueAt(fila, 2).toString()
                );
                txtEdad.setText(
                    modelo.getValueAt(fila, 3).toString()
                );
                txtCorreo.setText(
                    modelo.getValueAt(fila, 4).toString()
                );
            }
        });

        cargarAlumnos();
    }

    // CREAR CAMPOS
    private JTextField crearCampo() {
        JTextField campo = new JTextField(18);
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setPreferredSize(new Dimension(200, 34));
        campo.setBackground(BLANCO);
        campo.setForeground(new Color(40, 50, 65));
        campo.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(205, 215, 228)
                ),
                BorderFactory.createEmptyBorder(5, 9, 5, 9)
            )
        );
        return campo;
    }

    // AGREGAR ETIQUETAS Y CAMPOS AL FORMULARIO
    private void agregarCampo(
            JPanel panel,
            String texto,
            JTextField campo,
            int columna,
            int fila) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 12);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = columna * 2;
        gbc.gridy = fila;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;

        JLabel etiqueta = new JLabel(texto + ":");
        etiqueta.setFont(new Font("SansSerif", Font.BOLD, 13));
        etiqueta.setForeground(AZUL);
        panel.add(etiqueta, gbc);

        gbc.gridx = columna * 2 + 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(campo, gbc);
    }

    // CREAR BOTONES
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("SansSerif", Font.BOLD, 12));
        boton.setForeground(BLANCO);
        boton.setBackground(color);
        boton.setFocusPainted(false);
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        boton.setCursor(
            new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)
        );
        boton.setPreferredSize(new Dimension(115, 38));
        return boton;
    }

    // AGREGAR ALUMNO
    private void agregarAlumno() {
        try {
            Alumno alumno = new Alumno(
                0,
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                Integer.parseInt(txtEdad.getText().trim()),
                txtCorreo.getText().trim()
            );

            if (servicio.insertarAlumno(alumno)) {
                JOptionPane.showMessageDialog(
                    this, "Alumno registrado correctamente."
                );
                cargarAlumnos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(
                    this, "No se pudo registrar el alumno."
                );
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "La edad debe ser un número entero."
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this, "Error: " + ex.getMessage()
            );
        }
    }

    // MOSTRAR TODOS LOS ALUMNOS
    private void cargarAlumnos() {
        modelo.setRowCount(0);

        try {
            List<Alumno> lista = servicio.listarAlumnos();

            if (lista == null) {
                JOptionPane.showMessageDialog(
                    this, "No se pudieron cargar los alumnos."
                );
                return;
            }

            for (Alumno alumno : lista) {
                modelo.addRow(new Object[]{
                    alumno.getId(),
                    alumno.getNombre(),
                    alumno.getApellido(),
                    alumno.getEdad(),
                    alumno.getCorreo()
                });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this, "Error al listar alumnos: " + ex.getMessage()
            );
        }
    }

    // BUSCAR POR ID
    private void buscarAlumno() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());

            Alumno alumno = servicio.buscarAlumno(id);

            if (alumno != null) {
                txtNombre.setText(alumno.getNombre());
                txtApellido.setText(alumno.getApellido());
                txtEdad.setText(String.valueOf(alumno.getEdad()));
                txtCorreo.setText(alumno.getCorreo());

                JOptionPane.showMessageDialog(
                    this, "Alumno encontrado."
                );
            } else {
                JOptionPane.showMessageDialog(
                    this, "No se encontró el alumno con ese ID."
                );
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "Escribe un ID numérico válido."
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this, "Error al buscar: " + ex.getMessage()
            );
        }
    }

    // ACTUALIZAR ALUMNO
    private void actualizarAlumno() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());

            Alumno alumno = new Alumno(
                id,
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                Integer.parseInt(txtEdad.getText().trim()),
                txtCorreo.getText().trim()
            );

            int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas actualizar este alumno?",
                "Confirmar actualización",
                JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                if (servicio.actualizarAlumno(alumno)) {
                    JOptionPane.showMessageDialog(
                        this, "Alumno actualizado correctamente."
                    );
                    cargarAlumnos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(
                        this, "No se pudo actualizar el alumno."
                    );
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "Verifica que el ID y la edad sean números enteros."
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this, "Error al actualizar: " + ex.getMessage()
            );
        }
    }

    // ELIMINAR ALUMNO
    private void eliminarAlumno() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());

            int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que deseas eliminar al alumno con ID " + id + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                if (servicio.eliminarAlumno(id)) {
                    JOptionPane.showMessageDialog(
                        this, "Alumno eliminado correctamente."
                    );
                    cargarAlumnos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(
                        this, "No se pudo eliminar el alumno."
                    );
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "Selecciona un alumno o escribe un ID válido."
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this, "Error al eliminar: " + ex.getMessage()
            );
        }
    }

    // LIMPIAR CAMPOS
    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtCorreo.setText("");

        tabla.clearSelection();
        txtNombre.requestFocusInWindow();
    }
}
