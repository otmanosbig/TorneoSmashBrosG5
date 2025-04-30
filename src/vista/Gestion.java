package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.DaoImplementacionMySql;
import java.awt.Color;

public class Gestion extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected static VentanaPestañas ventana = null;
	private final JPanel contentPanel = new JPanel();

	private JButton btnAltaJ;
	private JButton btnAltaA;
	private JButton btnGestionarTorneos;
	private JButton btnBajaJ;
	private JButton btnModJ;
	private JButton btnBajaA, btnSalir;

	public Gestion(Frame parent) {
		super(parent, "Gestion", true);
	    setBounds(100, 100, 688, 466);
	    getContentPane().setLayout(new BorderLayout());
	    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPanel.setBackground(new Color(30, 0, 50));
	    getContentPane().add(contentPanel, BorderLayout.CENTER);
	    contentPanel.setLayout(null);
		
	    
		ventana = new VentanaPestañas(this, 'J');

		btnAltaJ = new JButton("Alta Jugadores");
		btnAltaJ.setForeground(Color.BLUE);
		btnAltaJ.setBackground(Color.GREEN);
		btnAltaJ.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAltaJ.setBounds(29, 149, 199, 21);
		contentPanel.add(btnAltaJ);
		btnAltaJ.addActionListener(this);

		btnAltaA = new JButton("Gestionar Árbitros");
		btnAltaA.setForeground(Color.BLUE);
		btnAltaA.setBackground(Color.GREEN);
		btnAltaA.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAltaA.setBounds(251, 149, 199, 21);
		contentPanel.add(btnAltaA);
		btnAltaA.addActionListener(this);

		btnGestionarTorneos = new JButton("Gestionar Torneos");
		btnGestionarTorneos.setForeground(Color.BLUE);
		btnGestionarTorneos.setBackground(Color.GREEN);
		btnGestionarTorneos.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGestionarTorneos.setBounds(465, 149, 199, 21);
		contentPanel.add(btnGestionarTorneos);
		btnGestionarTorneos.addActionListener(this);

		btnBajaJ = new JButton("Baja Jugadores");
		btnBajaJ.setForeground(Color.BLUE);
		btnBajaJ.setBackground(Color.GREEN);
		btnBajaJ.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBajaJ.setBounds(29, 180, 199, 21);
		contentPanel.add(btnBajaJ);
		btnBajaJ.addActionListener(this);

		btnModJ = new JButton("Modificar Jugadores");
		btnModJ.setForeground(Color.BLUE);
		btnModJ.setBackground(Color.GREEN);
		btnModJ.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModJ.setBounds(29, 211, 199, 21);
		contentPanel.add(btnModJ);
		btnModJ.addActionListener(this);

		btnBajaA = new JButton("Baja Árbitros");
		btnBajaA.setForeground(Color.BLUE);
		btnBajaA.setBackground(Color.GREEN);
		btnBajaA.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBajaA.setBounds(251, 180, 199, 21);
		contentPanel.add(btnBajaA);
		btnBajaA.addActionListener(this);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBackground(Color.BLACK);
		btnSalir.setForeground(new Color(255, 0, 0));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalir.setBounds(282, 318, 102, 37);
		contentPanel.add(btnSalir);
		btnSalir.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAltaJ)) {
			darDeAltaJ();
		} else if (e.getSource().equals(btnBajaJ)) {
			darDeBajaJ();
		} else if (e.getSource().equals(btnAltaA)) {
			rarDeAltaA();
		} else if (e.getSource().equals(btnBajaA)) {
			darBajaA();
		} else if (e.getSource().equals(btnGestionarTorneos)) {
			gestionarTorneo();
		} else if (e.getSource().equals(btnModJ)) {
			modJ();
		}else if (e.getSource().equals(btnSalir)) {
			salirS();
		}
	}

	private void salirS() {
		dispose();
	}

	private void modJ() {
		VentanaPestañas ventana = new VentanaPestañas(this, 'J');
		ventana.getLblNombreJ().setVisible(false);
	    ventana.getLblNicknameJ().setVisible(false);
	    ventana.getLblFechNacJ().setVisible(false);
	    ventana.getLblProvinciaJ().setVisible(false);
	    ventana.getTextNombreJ().setVisible(false);
	    ventana.getTextNicknameJ().setVisible(false);
	    ventana.getTextFachNacJ().setVisible(false);
	    ventana.getBtnAltaJ().setVisible(false);
	    ventana.getBtnBajaJ().setVisible(false);
	    ventana.getBtnModificarJ().setVisible(false);
	    ventana.getComboBoxProvincia().setVisible(false);
	    
		ventana.getTabbedPane().setSelectedIndex(0);
		ventana.setVisible(true);
	}

	private void gestionarTorneo() {
		VentanaPestañas ventana = new VentanaPestañas(this, 'T');
		ventana.getTabbedPane().setSelectedIndex(2);
		ventana.setVisible(true);
	}

	private void darBajaA() {
		VentanaPestañas ventana = new VentanaPestañas(this, 'A');
		ventana.getLblNombreA().setVisible(false);
		ventana.getTextNombreA().setVisible(false);
		ventana.getBtnAltaA().setVisible(false);
		
		ventana.getTabbedPane().setSelectedIndex(1);
		ventana.setVisible(true);
	}

	private void darDeBajaJ() {
		VentanaPestañas ventana = new VentanaPestañas(this, 'J');
		ventana.getLblNombreJ().setVisible(false);
	    ventana.getLblNicknameJ().setVisible(false);
	    ventana.getLblFechNacJ().setVisible(false);
	    ventana.getLblProvinciaJ().setVisible(false);
	    ventana.getTextNombreJ().setVisible(false);
	    ventana.getTextNicknameJ().setVisible(false);
	    ventana.getTextFachNacJ().setVisible(false);
	    ventana.getBtnAltaJ().setVisible(false);
	    ventana.getBtnSeleccionar().setVisible(false);
	    ventana.getBtnModificarJ().setVisible(false);
	    ventana.getComboBoxProvincia().setVisible(false);
	    
		ventana.getTabbedPane().setSelectedIndex(0);
		ventana.setVisible(true);
	}

	private void darDeAltaJ() {
		VentanaPestañas ventana = new VentanaPestañas(this, 'J');
		ventana.getBtnBajaJ().setVisible(false);
		ventana.getBtnModificarJ().setVisible(false);
		ventana.getBtnSeleccionar().setVisible(false);
		ventana.getComboBoxJugadores().setVisible(false);
		ventana.getLblSeleccionarJ().setVisible(false);
		
		ventana.getTabbedPane().setSelectedIndex(0);
		ventana.setVisible(true);
	}

	private void rarDeAltaA() {
		VentanaPestañas ventana = new VentanaPestañas(this, 'A');
		ventana.getLblCodigoA().setVisible(false);
		ventana.getComboBoxCodA().setVisible(false);
		ventana.getBtnBajaA().setVisible(false);
		
		ventana.getTabbedPane().setSelectedIndex(1);
		ventana.setVisible(true);
	}
	
}
