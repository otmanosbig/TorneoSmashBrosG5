package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import controlador.Main; 
import modelo.Jugador; 
import excepciones.GlobalException;

public class VListadoJugador extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel panelContenido = new JPanel();
    private JTable tabla;
    private JButton btnVolver;
    private DefaultTableModel modeloTabla; 

    public VListadoJugador() {
        setTitle("Listado de Jugadores");
        setBounds(100, 100, 607, 467);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(panelContenido, BorderLayout.CENTER);
        panelContenido.setLayout(null);
        panelContenido.setBackground(new Color(30, 0, 50)); 

        // Cargar la tabla de jugadores
        tabla = cargarTablaJugadores();

        JScrollPane panelDesplazamiento = new JScrollPane(tabla);
        panelDesplazamiento.setBounds(37, 130, 510, 200); 
        panelContenido.add(panelDesplazamiento);

        // Botón Volver
        btnVolver = new JButton("Volver");
        btnVolver.setForeground(Color.RED);
        btnVolver.setBackground(Color.BLACK);
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.addActionListener(this);
        btnVolver.setBounds(37, 382, 103, 21);
        panelContenido.add(btnVolver);
    }

    private JTable cargarTablaJugadores() {
    	String[] columnas = {"Nickname", "Nombre", "Fecha de Nacimiento"};
        modeloTabla = new DefaultTableModel(null, columnas); 

        try {
            List<Jugador> lista = Main.cargarJugadores(); 
            if (lista != null) {
                for (Jugador j : lista) {
                    modeloTabla.addRow(new Object[]{
                        j.getNickname(),
                        j.getNombre(),
                        j.getFechaNac()
                    });
                }
            } else {
                System.out.println("Datos no recibidos.");
            }

        } catch (GlobalException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar jugadores: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        return new JTable(modeloTabla);
    }

  
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            VListar ventanaListar = new VListar();
            ventanaListar.setVisible(true); 
            dispose(); 
        }
    }
}

