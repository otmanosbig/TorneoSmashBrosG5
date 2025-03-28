package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VSinIniciar extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTable tableTorneo;
    private JTable tableJugador;
    private DefaultTableModel modelTorneo;
    private DefaultTableModel modelJugador;
    private DefaultTableModel modelJuega;  // Definir el modelo para la tabla juega

    public static void main(String[] args) {
        try {
            VSinIniciar dialog = new VSinIniciar();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public VSinIniciar() {
        setTitle("Lista de Torneos y Jugadores");
        setBounds(100, 100, 900, 600);
        getContentPane().setLayout(new BorderLayout());

        // Panel principal
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new GridLayout(3, 1, 10, 10)); // Tres filas para las tres tablas
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // ------------------ TABLA TORNEO ------------------
        String[] columnNamesTorneo = {"Código", "Nombre", "Fecha", "Plazas", "Código Árbitro", "Activo"};
        modelTorneo = new DefaultTableModel(columnNamesTorneo, 0);
        tableTorneo = new JTable(modelTorneo);
        JScrollPane scrollTorneo = new JScrollPane(tableTorneo);
        contentPanel.add(scrollTorneo); // Agregar tabla de torneo

        // ------------------ TABLA JUGADOR ------------------
        String[] columnNamesJugador = {"Nickname", "Nombre", "Provincia", "Fecha Nacimiento"};
        modelJugador = new DefaultTableModel(columnNamesJugador, 0);
        tableJugador = new JTable(modelJugador);
        JScrollPane scrollJugador = new JScrollPane(tableJugador);
        contentPanel.add(scrollJugador); // Agregar tabla de jugadores

        // ------------------ TABLA JUEGA ------------------
        String[] columnNamesJuega = {"Nickname", "Código Torneo", "Personaje", "Puntos"};
        modelJuega = new DefaultTableModel(columnNamesJuega, 0);  // Inicializa el modelo para la tabla juega
        JTable tableJuega = new JTable(modelJuega);  // Crea la tabla de juega
        JScrollPane scrollJuega = new JScrollPane(tableJuega);
        contentPanel.add(scrollJuega);  // Agregar tabla de juega

        // Cargar datos de la base de datos
        cargarDatosTorneo();
        cargarDatosJugador();
        cargarDatosJuega();

        // ------------------ PANEL DE BOTONES ------------------
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> volverAVPrincipal());
        buttonPanel.add(btnVolver);

        // Botón Iniciar Sesión
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.addActionListener(e -> iniciarSesion());
        buttonPanel.add(btnLogin);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH); // Agregar los botones al fondo
    }

    private void volverAVPrincipal() {
        // Aquí debes crear y mostrar la ventana principal VPrincipal
        VPrincipal vPrincipal = new VPrincipal();
        vPrincipal.setVisible(true);
        dispose();  // Cerrar la ventana actual
    }

    private void iniciarSesion() {
        // Aquí debes crear y mostrar la ventana de login
        Login login = new Login();
        login.setVisible(true);
        dispose();  // Cerrar la ventana actual
    }

    private void cargarDatosJuega() {
        String url = "jdbc:mysql://localhost:3306/torneosmashbros?allowPublicKeyRetrieval=true&serverTimezone=Europe/Madrid&useSSL=false";
        String user = "root";
        String password = "abcd*1234";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM juega";
            try (PreparedStatement stmt = con.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                modelJuega.setRowCount(0); // Limpiar datos previos

                while (rs.next()) {
                    String nickname = rs.getString("nickname");
                    String codigoT = rs.getString("codigoT");
                    String personaje = rs.getString("personaje");
                    int puntos = rs.getInt("puntos");

                    // Agregar una fila a la tabla de juega
                    modelJuega.addRow(new Object[]{nickname, codigoT, personaje, puntos});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos de la tabla juega: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatosTorneo() {
        String url = "jdbc:mysql://localhost:3306/torneosmashbros?allowPublicKeyRetrieval=true&serverTimezone=Europe/Madrid&useSSL=false";
        String user = "root";  // Cambia según tu configuración
        String password = "abcd*1234";  // Cambia según tu configuración

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM torneo";
            try (PreparedStatement stmt = con.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                modelTorneo.setRowCount(0);

                //obtener fecha actual
                LocalDate currentDate = LocalDate.now();

                while (rs.next()) {
                    String codigoT = rs.getString("codigoT");
                    String nombre = rs.getString("nombre");
                    String fecha = rs.getString("fecha");
                    int plazas = rs.getInt("plazas");
                    int codigoA = rs.getInt("codigoA");
                    boolean estaActivo = rs.getBoolean("estaActivo");

                    // Parsear
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate tournamentDate = LocalDate.parse(fecha, formatter);

                    
                    if (tournamentDate.isBefore(currentDate)) {
                        estaActivo = false; 
                    }

                    //fila
                    modelTorneo.addRow(new Object[]{codigoT, nombre, fecha, plazas, codigoA, estaActivo});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar torneos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatosJugador() {
        String url = "jdbc:mysql://localhost:3306/torneosmashbros?allowPublicKeyRetrieval=true&serverTimezone=Europe/Madrid&useSSL=false";
        String user = "root";  // Cambia según tu configuración
        String password = "abcd*1234";  // Cambia según tu configuración

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM jugador";
            try (PreparedStatement stmt = con.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                modelJugador.setRowCount(0); // Limpiar datos previos

                while (rs.next()) {
                    String nickname = rs.getString("nickname");
                    String nombre = rs.getString("nombre");
                    String nombreP = rs.getString("nombreP");
                    Date fechaNac = rs.getDate("fechaNac");

                    modelJugador.addRow(new Object[]{nickname, nombre, nombreP, fechaNac});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar jugadores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
