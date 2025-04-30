package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;
import controlador.DaoImplementacionMySql;
import controlador.Main;
import excepciones.GlobalException;
import modelo.Juega;
import modelo.Torneo;
import javax.swing.JTextField;
import java.awt.Color;

public class VAnadirJ extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBoxJugador;
	private JComboBox<String> comboBoxTorneo;
	private JComboBox<String> comboBoxPersonaje;
	private JButton btnVolver, btnAnadir, btnBuscar;

	/**
	 * Crear la ventana de diálogo.
	 */
	public VAnadirJ() {
		setTitle("Añadir Jugador a Torneo");
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPanel.setBackground(new Color(30, 0, 50)); 
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblTitulo = new JLabel("Añadir jugador al torneo");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		lblTitulo.setBounds(200, 20, 250, 25);
		contentPanel.add(lblTitulo);

		JLabel lblCodigoT = new JLabel("Codigo Torneo:");
		lblCodigoT.setForeground(Color.WHITE);
		lblCodigoT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigoT.setBounds(105, 78, 120, 25);
		contentPanel.add(lblCodigoT);

		comboBoxTorneo = new JComboBox<>();
		comboBoxTorneo.setBounds(230, 78, 200, 25);
		contentPanel.add(comboBoxTorneo);

		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setForeground(Color.WHITE);
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNickname.setBounds(133, 111, 100, 25);
		contentPanel.add(lblNickname);

		comboBoxJugador = new JComboBox<>();
		comboBoxJugador.setBounds(230, 113, 200, 25);
		contentPanel.add(comboBoxJugador);

		JLabel lblPersonaje = new JLabel("Personaje:");
		lblPersonaje.setForeground(Color.WHITE);
		lblPersonaje.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPersonaje.setBounds(133, 148, 87, 25);
		contentPanel.add(lblPersonaje);

		comboBoxPersonaje = new JComboBox<>();
		comboBoxPersonaje.setBounds(230, 148, 200, 24);
		comboBoxPersonaje.addItem("Mario");
		comboBoxPersonaje.addItem("Link");
		comboBoxPersonaje.addItem("Samus");
		comboBoxPersonaje.addItem("Kirby");
		comboBoxPersonaje.addItem("Pikachu");
		comboBoxPersonaje.addItem("Fox");
		comboBoxPersonaje.addItem("Donkey Kong");
		comboBoxPersonaje.addItem("Captain Falcon");
		comboBoxPersonaje.addItem("Ness");
		comboBoxPersonaje.addItem("Yoshi");
		contentPanel.add(comboBoxPersonaje);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(Color.BLUE);
		btnBuscar.setBackground(Color.BLACK);
		btnBuscar.setBounds(440, 82, 80, 21);
		btnBuscar.addActionListener(this);
		contentPanel.add(btnBuscar);

		btnAnadir = new JButton("Añadir");
		btnAnadir.setForeground(Color.BLUE);
		btnAnadir.setBackground(new Color(0, 0, 0));
		btnAnadir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAnadir.setBounds(391, 250, 129, 30);
		btnAnadir.addActionListener(this);
		contentPanel.add(btnAnadir);

		btnVolver = new JButton("Volver");
		btnVolver.setForeground(Color.RED);
		btnVolver.setBackground(new Color(0, 0, 0));
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVolver.setBounds(50, 250, 129, 30);
		btnVolver.addActionListener(this);
		contentPanel.add(btnVolver);

		cargarDatos();
	}

	

	



	/**
	 * Carga los datos de los jugadores y torneos desde la base de datos.
	 */
	private void cargarDatos() {
		try {
			comboBoxJugador.setEnabled(false);
			List<Torneo> torneos = Main.obtenerTorneo();
			for (Torneo torneo : torneos) {
				comboBoxTorneo.addItem(torneo.getCodigoT());
			}
		} catch (GlobalException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnVolver)) {
			volverGestion();
		} else if (e.getSource().equals(btnAnadir)) {
			anadirJugadorATorneo();
		} else if (e.getSource().equals(btnBuscar)) {
			buscarJugadores();
		}
	}

	private void anadirJugadorATorneo() {
		try {
			// Obtener datos del formulario
			String nickname = (String) comboBoxJugador.getSelectedItem();
			String codigoTorneo = (String) comboBoxTorneo.getSelectedItem();
			String personaje = (String) comboBoxPersonaje.getSelectedItem();

			if (nickname == null || codigoTorneo == null || personaje == null || personaje.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Crear el objeto Juega
			Juega juega = new Juega();
			juega.setNickname(nickname);
			juega.setCodigoT(codigoTorneo);
			juega.setPersonaje(personaje);
			

			Main.anadirJugadorATorneo(juega);

			JOptionPane.showMessageDialog(this, "Jugador añadido correctamente al torneo", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);

			// Limpiar campos
			comboBoxJugador.setSelectedIndex(-1);
			comboBoxJugador.setEnabled(false);
			comboBoxPersonaje.setSelectedIndex(-1);
			

		} catch (GlobalException ex) {
			JOptionPane.showMessageDialog(this, "Error al añadir jugador: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void buscarJugadores() {
		try {
			comboBoxJugador.removeAllItems();
			List<String> nombreJugadores = Main.obtenerJugadoresSinTorneo(comboBoxTorneo.getSelectedItem().toString());
			for (String nickname : nombreJugadores) {
				comboBoxJugador.addItem(nickname);
			}
			comboBoxJugador.setEnabled(true);
		} catch (GlobalException e) {
			e.printStackTrace();
		}
	}

	private void volverGestion() {
		dispose();
	}
}
