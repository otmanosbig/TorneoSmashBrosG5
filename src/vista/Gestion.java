package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Gestion extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    private JButton btnAltaJ;
    private JButton btnAltaA;
    private JButton btnGestionarTorneos;
    private JButton btnBajaJ;
    private JButton btnModJ;
    private JButton btnBajaA;

    public Gestion(Frame parent) {
        super(parent, "Gestion", true); // Modal
        setBounds(100, 100, 688, 466);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        btnAltaJ = new JButton("Alta Jugadores");
        btnAltaJ.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAltaJ.setBounds(29, 149, 199, 21);
        contentPanel.add(btnAltaJ);
        btnAltaJ.addActionListener(this);

        btnAltaA = new JButton("Gestionar Árbitros");
        btnAltaA.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAltaA.setBounds(251, 149, 199, 21);
        contentPanel.add(btnAltaA);
        btnAltaA.addActionListener(this);

        btnGestionarTorneos = new JButton("Gestionar Torneos");
        btnGestionarTorneos.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnGestionarTorneos.setBounds(465, 149, 199, 21);
        contentPanel.add(btnGestionarTorneos);
        btnGestionarTorneos.addActionListener(this);

        btnBajaJ = new JButton("Baja Jugadores");
        btnBajaJ.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnBajaJ.setBounds(29, 180, 199, 21);
        contentPanel.add(btnBajaJ);
        btnBajaJ.addActionListener(this);

        btnModJ = new JButton("Modificar Jugadores");
        btnModJ.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnModJ.setBounds(29, 211, 199, 21);
        contentPanel.add(btnModJ);
        btnModJ.addActionListener(this);

        btnBajaA = new JButton("Baja Árbitros");
        btnBajaA.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnBajaA.setBounds(251, 180, 199, 21);
        contentPanel.add(btnBajaA);
        btnBajaA.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnAltaJ)) {
            DarDeAltaJ();
        } else if (e.getSource().equals(btnBajaJ)) {
        	DarDeBajaJ();
        } else if (e.getSource().equals(btnAltaA)) {
        	DarDeAltaA();
        } else if (e.getSource().equals(btnBajaA)) {
        	DarBajaA();
        } else if (e.getSource().equals(btnGestionarTorneos)) {
        	GestionarTorneo();
        } else if (e.getSource().equals(btnModJ)) {
        	ModificarJugador();
        }
    }

    private void ModificarJugador() {
    	VentanaPestañas ventana = new VentanaPestañas(this);
        ventana.getTabbedPane().setSelectedIndex(0);
        ventana.getComboBoxJugadores().setEnabled(true);
        ventana.getBtnSeleccionar().setEnabled(true);
        ventana.getBtnVolverJ().setEnabled(true);;
        ventana.setVisible(true);
	}

	private void GestionarTorneo() {
    	VentanaPestañas ventana = new VentanaPestañas(this);
        ventana.getTabbedPane().setSelectedIndex(2);
        ventana.getTextNombreT().setEnabled(true);
        ventana.getTextCodigoT().setEnabled(true);
        ventana.getTextFechaT().setEnabled(true);
        ventana.getBtnVolverT().setEnabled(true);
        ventana.getBtnClasificarJ().setEnabled(true);
        ventana.getBtnAñadirJ().setEnabled(true);
        ventana.getBtnAltaT().setEnabled(true);
        ventana.setVisible(true);
	}

	private void DarBajaA() {
    	VentanaPestañas ventana = new VentanaPestañas(this);
        ventana.getTabbedPane().setSelectedIndex(1);
        ventana.getTextCodA().setEnabled(true);
        ventana.getBtnBajaA().setEnabled(true);
        ventana.getBtnVolverA().setEnabled(true);
        ventana.setVisible(true);
	}

	private void DarDeBajaJ() {
    	VentanaPestañas ventana = new VentanaPestañas(this);
        ventana.getTabbedPane().setSelectedIndex(0);
        ventana.getComboBoxJugadores().setEnabled(true);
        ventana.getBtnBajaJ().setEnabled(true);
        ventana.getBtnVolverJ().setEnabled(true);
        ventana.setVisible(true);
	}

	private void DarDeAltaJ() {
        VentanaPestañas ventana = new VentanaPestañas(this);
        ventana.getTabbedPane().setSelectedIndex(0);
        ventana.getTextNombreJ().setEnabled(true);
        ventana.getTextNicknameJ().setEnabled(true);
        ventana.getTextFachNacJ().setEnabled(true);
        ventana.getComboBoxProvincia().setEnabled(true);
        ventana.getBtnAltaJ().setEnabled(true);
        ventana.getBtnVolverJ().setEnabled(true);
        ventana.setVisible(true);
    }
	
	private void DarDeAltaA() {
        VentanaPestañas ventana = new VentanaPestañas(this);
        ventana.getTabbedPane().setSelectedIndex(1);
        ventana.getTextNombreA().setEnabled(true);
        ventana.getBtnAltaA().setEnabled(true);
        ventana.getBtnVolverA().setEnabled(true);
        ventana.setVisible(true);
    }
}
