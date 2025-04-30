package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Torneo;
import controlador.Main;
import excepciones.GlobalException;

public class VListaTorneo extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTable tablaTorneos;
    private JButton btnVolver;

    public VListaTorneo() {
        setTitle("Lista de Torneos");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        tablaTorneos = cargarTablaTorneos();
        JScrollPane scroll = new JScrollPane(tablaTorneos);
        add(scroll, BorderLayout.CENTER);

        // Crear panel para el botón volver
        JPanel panelBoton = new JPanel();
        btnVolver = new JButton("Volver");
        btnVolver.setForeground(Color.RED);
        btnVolver.setBackground(Color.BLACK);
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.addActionListener(this);
        panelBoton.add(btnVolver);

        add(panelBoton, BorderLayout.SOUTH); // Agrega el panel abajo
    }

    private JTable cargarTablaTorneos() {
        String[] columnasNombre = { "Código", "Nombre", "Fecha", "Plazas", "Árbitro", "Activo" };
        DefaultTableModel model = new DefaultTableModel(null, columnasNombre);

        try {
            List<Torneo> lista = Main.obtenerTorneo();

            for (Torneo t : lista) {
                Object[] fila = new Object[] {
                    t.getCodigoT(),
                    t.getNombreT(),
                    t.getFecha(),
                    t.getPlazas(),
                    t.getcodigoA(),
                    t.isEstaActivo() ? "Sí" : "No"
                };
                model.addRow(fila);
            }

        } catch (GlobalException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar torneos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        return new JTable(model);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            VListar listar = new VListar(); 
            listar.setVisible(true);  
            dispose(); 
        }
    }
}
