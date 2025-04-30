package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import excepciones.GlobalException;
import controlador.Main;
import modelo.Jugador;

public class VRanking extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JButton btnVolver;

    public VRanking() {
        setTitle("Ranking General");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(30, 0, 50)); 

        JLabel lblRanking = new JLabel("Ranking de jugadores:");
        lblRanking.setForeground(Color.WHITE);
        lblRanking.setBackground(Color.BLACK);
        lblRanking.setFont(new Font("STSong", Font.BOLD, 14));
        lblRanking.setBounds(30, 20, 400, 20);
        contentPanel.add(lblRanking);

        table = cargarTablaRanking();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 60, 520, 250); 
        contentPanel.add(scrollPane);

        btnVolver = new JButton("Volver");
        btnVolver.setForeground(Color.RED);
        btnVolver.setBackground(Color.BLACK);
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.addActionListener(this);
        btnVolver.setBounds(30, 330, 112, 25);
        contentPanel.add(btnVolver);
    }

    private JTable cargarTablaRanking() {
        String[] columnas = { "Nickname", "Nombre", "Puntos Totales" };
        DefaultTableModel model = new DefaultTableModel(null, columnas);

        try {
            List<Jugador> lista = Main.obtenerRanking();

            if (lista != null) {
                for (Jugador j : lista) {
                    Object[] fila = {
                        j.getNickname(),
                        j.getNombre(),
                        j.getPuntos()
                    };
                    model.addRow(fila);
                }
            } else {
                System.out.println("Datos no recibidos.");
            }

        } catch (GlobalException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar ranking: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return new JTable(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            VListar listar = new VListar(); 
            listar.setVisible(true);  
            dispose(); 
        }
    }
}
