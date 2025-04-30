package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VListar extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JButton btnListarTorneo;
    private JButton btnRanking;
    private JButton btnListarJugadores;
    private JButton btnVolver;

    public VListar() {
        setBounds(100, 100, 693, 442);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(30, 0, 50)); 
        
        btnListarTorneo = new JButton("Listar Torneo");
        btnListarTorneo.setForeground(Color.BLUE);
        btnListarTorneo.setBackground(Color.GREEN);
        btnListarTorneo.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnListarTorneo.setBounds(64, 196, 142, 21);
        btnListarTorneo.addActionListener(this);
        contentPanel.add(btnListarTorneo);
        
        btnRanking = new JButton("Ranking");
        btnRanking.setForeground(Color.BLUE);
        btnRanking.setBackground(Color.GREEN);
        btnRanking.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnRanking.setBounds(425, 196, 142, 21);
        btnRanking.addActionListener(this);
        contentPanel.add(btnRanking);
        
        btnListarJugadores = new JButton("Listar Jugador");
        btnListarJugadores.setForeground(Color.BLUE);
        btnListarJugadores.setBackground(Color.GREEN);
        btnListarJugadores.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnListarJugadores.setBounds(250, 196, 142, 21);
        btnListarJugadores.addActionListener(this);
        contentPanel.add(btnListarJugadores);
        
        btnVolver = new JButton("Volver");
        btnVolver.setForeground(Color.RED);
        btnVolver.setBackground(Color.BLACK);
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBounds(272, 326, 97, 21);
        btnVolver.addActionListener(this);
        contentPanel.add(btnVolver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnListarTorneo)) {
            VListaTorneo torneo = new VListaTorneo();
            torneo.setVisible(true);
            dispose();  // Cerrar la ventana actual
        } else if (e.getSource().equals(btnRanking)) {
            VRanking ranking = new VRanking();
            ranking.setVisible(true);
            dispose();  // Cerrar la ventana actual
        } else if (e.getSource().equals(btnListarJugadores)) {
            VListadoJugador jugadores = new VListadoJugador();
            jugadores.setVisible(true);
            dispose();  // Cerrar la ventana actual
        } else if (e.getSource().equals(btnVolver)) {
            Login login = new Login();
            login.setVisible(true);
            dispose();  // Cerrar la ventana actual
        }
    }
}

