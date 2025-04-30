package vista;

import javax.swing.*;

import controlador.DaoImplementacionMySql;
import controlador.Main;
import excepciones.GlobalException;
import modelo.Arbitro;
import modelo.Torneo;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class VentanaPestañas extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textNombreJ;
	private JTextField textNicknameJ;
	private JTextField textFachNacJ;
	private JTabbedPane tabbedPane;
	private JButton btnAltaJ;
	private JButton btnVolverT;
	private JButton btnVolverJ;
	private JButton btnVolverA;
	private JButton btnBajaJ;
	private JButton btnAltaA;
	private JButton btnBajaA;
	private JButton btnAltaT;
	private JButton btnAñadirJ;
	private JButton btnClasificarJ;
	private JButton btnSeleccionar;
	private JButton btnModificarJ;
	private JComboBox<String> comboBoxJugadores;
	private JComboBox<String> comboBoxProvincia;
	private JComboBox<Integer> comboBoxCodA;
	private JTextField textNombreA;
	private JTextField textNombreT;
	private JTextField textFechaT;
	private JTextField textCodigoT;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel lblNombreJ;
	private JLabel lblNicknameJ;
	private JLabel lblFechNacJ;
	private JLabel lblProvinciaJ;
	private JLabel lblSeleccionarJ;
	private JLabel lblCodigoA;
	private JLabel lblNombreA;
	private JCheckBox checkboxActivo;
	private JComboBox comboBoxCodigoA;

	private List<String> jugadoresActivos = new ArrayList<>();
	private JTextField textFecha;
	private JTextField textPlazas;
	private Main main;

	public VentanaPestañas(JDialog parent, char persona) {
		super(parent, "SmashBros Gestionar", true);
		setSize(674, 511);
		setLocationRelativeTo(parent);
		setModal(true);

		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, 674, 511);
		getContentPane().setLayout(null);
		getContentPane().add(tabbedPane);

		panel1 = new JPanel();
		panel1.setLayout(null);

		JLabel label1 = new JLabel("Panel de Jugadores");
		label1.setForeground(Color.WHITE);
		label1.setBounds(10, 10, 200, 25);
		panel1.add(label1);
		panel1.setBackground(new Color(30, 0, 50));

		tabbedPane.addTab("Gestionar Jugador", panel1);

		lblNombreJ = new JLabel("Nombre:");
		lblNombreJ.setForeground(Color.WHITE);
		lblNombreJ.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreJ.setBounds(179, 54, 70, 13);
		panel1.add(lblNombreJ);

		lblNicknameJ = new JLabel("Nickname:");
		lblNicknameJ.setForeground(Color.WHITE);
		lblNicknameJ.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNicknameJ.setBounds(172, 89, 86, 13);
		panel1.add(lblNicknameJ);

		lblFechNacJ = new JLabel("Fecha de nacimiento:");
		lblFechNacJ.setForeground(Color.WHITE);
		lblFechNacJ.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFechNacJ.setBounds(85, 128, 173, 13);
		panel1.add(lblFechNacJ);

		lblProvinciaJ = new JLabel("Provincia:");
		lblProvinciaJ.setForeground(Color.WHITE);
		lblProvinciaJ.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProvinciaJ.setBounds(172, 169, 86, 13);
		panel1.add(lblProvinciaJ);

		textNombreJ = new JTextField();
		textNombreJ.setBounds(269, 53, 173, 19);
		panel1.add(textNombreJ);

		textNicknameJ = new JTextField();
		textNicknameJ.setBounds(268, 88, 173, 19);
		panel1.add(textNicknameJ);

		textFachNacJ = new JTextField();
		textFachNacJ.setBounds(268, 127, 173, 19);
		panel1.add(textFachNacJ);

		btnAltaJ = new JButton("Alta");
		btnAltaJ.setForeground(Color.BLUE);
		btnAltaJ.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAltaJ.addActionListener(this);
		btnAltaJ.setBounds(461, 52, 150, 21);
		panel1.add(btnAltaJ);

		btnBajaJ = new JButton("Baja");
		btnBajaJ.setForeground(Color.RED);
		btnBajaJ.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBajaJ.addActionListener(this);
		btnBajaJ.setBounds(461, 88, 150, 21);
		panel1.add(btnBajaJ);

		btnModificarJ = new JButton("Modificar");
		btnModificarJ.setForeground(Color.BLUE);
		btnModificarJ.addActionListener(this);
		btnModificarJ.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnModificarJ.setBounds(461, 127, 150, 21);
		panel1.add(btnModificarJ);

		btnVolverJ = new JButton("Volver");
		btnVolverJ.setForeground(Color.RED);
		btnVolverJ.setBackground(Color.BLACK);
		btnVolverJ.addActionListener(this);
		btnVolverJ.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolverJ.setBounds(280, 267, 112, 21);
		panel1.add(btnVolverJ);

		lblSeleccionarJ = new JLabel("Seleccionar:");
		lblSeleccionarJ.setForeground(Color.WHITE);
		lblSeleccionarJ.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSeleccionarJ.setBounds(153, 213, 122, 13);
		panel1.add(lblSeleccionarJ);

		comboBoxJugadores = new JComboBox<String>();
		comboBoxJugadores.setBounds(269, 211, 173, 21);
		panel1.add(comboBoxJugadores);

		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setForeground(Color.BLUE);
		btnSeleccionar.addActionListener(this);
		btnSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSeleccionar.setBounds(461, 167, 150, 21);
		panel1.add(btnSeleccionar);

		comboBoxProvincia = new JComboBox<String>();
		comboBoxProvincia.setBounds(268, 167, 174, 21);
		panel1.add(comboBoxProvincia);

		DaoImplementacionMySql dao = new DaoImplementacionMySql();
		try {
			List<String> nicknames = dao.obtenerNicknames();

			for (String nickname : nicknames) {
				comboBoxJugadores.addItem(nickname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al cargar los jugadores: " + e.getMessage());
		}
		inicializarComboBoxProvincias();

		panel2 = new JPanel();
		panel2.setLayout(null);
		tabbedPane.addTab("Gestionar Arbitro", panel2);
		panel2.setBackground(new Color(30, 0, 50));

		JLabel label2 = new JLabel("Panel de Arbitros");
		label2.setForeground(Color.WHITE);
		label2.setBounds(10, 10, 200, 25);
		panel2.add(label2);

		lblNombreA = new JLabel("Nombre:");
		lblNombreA.setForeground(Color.WHITE);
		lblNombreA.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreA.setBounds(135, 94, 83, 13);
		panel2.add(lblNombreA);

		lblCodigoA = new JLabel("Codigo:");
		lblCodigoA.setForeground(Color.WHITE);
		lblCodigoA.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCodigoA.setBounds(135, 134, 72, 19);
		panel2.add(lblCodigoA);

		btnAltaA = new JButton("Alta");
		btnAltaA.setForeground(Color.BLUE);
		btnAltaA.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAltaA.addActionListener(this);
		btnAltaA.setBounds(410, 92, 150, 21);
		panel2.add(btnAltaA);

		btnBajaA = new JButton("Baja");
		btnBajaA.setForeground(Color.RED);
		btnBajaA.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBajaA.addActionListener(this);
		btnBajaA.setBounds(410, 135, 150, 21);
		panel2.add(btnBajaA);

		btnVolverA = new JButton("Volver");
		btnVolverA.setForeground(Color.RED);
		btnVolverA.setBackground(Color.BLACK);
		btnVolverA.addActionListener(this);
		btnVolverA.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolverA.setBounds(280, 264, 100, 21);
		panel2.add(btnVolverA);

		textNombreA = new JTextField();
		textNombreA.setBounds(214, 93, 173, 19);
		panel2.add(textNombreA);

		comboBoxCodA = new JComboBox<>();
		comboBoxCodA.setBounds(214, 136, 173, 19);
		panel2.add(comboBoxCodA);

		panel2.setEnabled(false);
		panel2.revalidate();
		panel2.repaint();

		inicializarComboBoxCodA();

		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		tabbedPane.addTab("Gestionar Torneos", panel3);

		JLabel label3 = new JLabel("Panel de Torneos");
		label3.setForeground(Color.WHITE);
		label3.setBounds(10, 10, 200, 25);
		panel3.add(label3);
		panel3.setBackground(new Color(30, 0, 50));

		JLabel lblNewLabel_1_2 = new JLabel("Nombre:");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setBounds(168, 89, 70, 20);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel3.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha:");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(178, 138, 60, 19);
		panel3.add(lblNewLabel_1_1_1);

		textNombreT = new JTextField();
		textNombreT.setBounds(248, 92, 173, 19);
		panel3.add(textNombreT);

		btnAltaT = new JButton("Alta");
		btnAltaT.setForeground(Color.BLUE);
		btnAltaT.addActionListener(this);
		btnAltaT.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAltaT.setBounds(465, 91, 150, 21);
		panel3.add(btnAltaT);

		btnAñadirJ = new JButton("AñadirJ");
		btnAñadirJ.setForeground(Color.BLUE);
		btnAñadirJ.setBackground(Color.BLACK);
		btnAñadirJ.addActionListener(this);
		btnAñadirJ.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAñadirJ.setBounds(10, 397, 173, 21);
		panel3.add(btnAñadirJ);

		btnClasificarJ = new JButton("ClasificarJ");
		btnClasificarJ.setForeground(Color.BLUE);
		btnClasificarJ.setBackground(Color.BLACK);
		btnClasificarJ.addActionListener(this);
		btnClasificarJ.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClasificarJ.setBounds(442, 397, 173, 21);
		panel3.add(btnClasificarJ);

		JLabel lblNewLabel_1_2_1 = new JLabel("Codigo:");
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2_1.setBounds(168, 181, 70, 20);
		panel3.add(lblNewLabel_1_2_1);

		textCodigoT = new JTextField();
		textCodigoT.setBounds(248, 184, 173, 19);
		panel3.add(textCodigoT);

		btnVolverT = new JButton("Volver");
		btnVolverT.setForeground(Color.RED);
		btnVolverT.setBackground(Color.BLACK);
		btnVolverT.addActionListener(this);
		btnVolverT.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolverT.setBounds(248, 397, 122, 21);
		panel3.add(btnVolverT);

		textFecha = new JTextField();
		textFechaT = new JTextField();
		textFechaT.setBounds(248, 140, 169, 19);
		panel3.add(textFechaT);
		textFechaT.setColumns(10);

		JLabel lblplazas = new JLabel("Plazas:");
		lblplazas.setForeground(Color.WHITE);
		lblplazas.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblplazas.setBounds(168, 223, 70, 25);
		panel3.add(lblplazas);

		JLabel lblboolean = new JLabel("Activo:");
		lblboolean.setForeground(Color.WHITE);
		lblboolean.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblboolean.setBounds(178, 314, 70, 22);
		panel3.add(lblboolean);

		textPlazas = new JTextField();
		textPlazas.setBounds(248, 228, 83, 19);
		panel3.add(textPlazas);

		checkboxActivo = new JCheckBox("");
		checkboxActivo.setBounds(248, 314, 122, 21);
		panel3.add(checkboxActivo);

		JLabel lblcodigoA = new JLabel("Codigo Arbitro:");
		lblcodigoA.setForeground(Color.WHITE);
		lblcodigoA.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblcodigoA.setBounds(110, 266, 128, 25);
		panel3.add(lblcodigoA);

		comboBoxCodigoA = new JComboBox();
		comboBoxCodigoA.setBounds(248, 270, 83, 21);
		panel3.add(comboBoxCodigoA);
		obtenerArbitro();

		// Habilitar pestañas segun informacion
		if (persona == 'J') {
			tabbedPane.setEnabledAt(1, false);
			tabbedPane.setEnabledAt(2, false);
		} else if (persona == 'A') {
			tabbedPane.setEnabledAt(0, false);
			tabbedPane.setEnabledAt(2, false);
		} else if (persona == 'T') {
			tabbedPane.setEnabledAt(0, false);
			tabbedPane.setEnabledAt(1, false);
		}

	}

	private void obtenerArbitro() {
		main = new Main();
		List<Arbitro> listA = new ArrayList<>();
		try {
			listA = main.obtenerArbitros();
		} catch (GlobalException e) {
			e.printStackTrace();
		}
		for (Arbitro arb : listA) {
			comboBoxCodigoA.addItem(arb.getCodigoA());
			;
		}
	}

	private void inicializarComboBoxCodA() {
		DaoImplementacionMySql dao = new DaoImplementacionMySql();
		try {
			List<Integer> codigos = dao.obtenerCodigosArbitros();
			for (Integer codigo : codigos) {
				comboBoxCodA.addItem(codigo);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al cargar los códigos de árbitros: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public JTextField getTextNombreJ() {
		return textNombreJ;
	}

	public void setTextNombreJ(JTextField textNombreJ) {
		this.textNombreJ = textNombreJ;
	}

	public JTextField getTextNicknameJ() {
		return textNicknameJ;
	}

	public void setTextNicknameJ(JTextField textNicknameJ) {
		this.textNicknameJ = textNicknameJ;
	}

	public JTextField getTextFachNacJ() {
		return textFachNacJ;
	}

	public void setTextFachNacJ(JTextField textFachNacJ) {
		this.textFachNacJ = textFachNacJ;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JButton getBtnAltaJ() {
		return btnAltaJ;
	}

	public void setBtnAltaJ(JButton btnAltaJ) {
		this.btnAltaJ = btnAltaJ;
	}

	public JButton getBtnVolverT() {
		return btnVolverT;
	}

	public void setBtnVolverT(JButton btnVolverT) {
		this.btnVolverT = btnVolverT;
	}

	public JButton getBtnVolverJ() {
		return btnVolverJ;
	}

	public void setBtnVolverJ(JButton btnVolverJ) {
		this.btnVolverJ = btnVolverJ;
	}

	public JButton getBtnVolverA() {
		return btnVolverA;
	}

	public void setBtnVolverA(JButton btnVolverA) {
		this.btnVolverA = btnVolverA;
	}

	public JButton getBtnBajaJ() {
		return btnBajaJ;
	}

	public void setBtnBajaJ(JButton btnBajaJ) {
		this.btnBajaJ = btnBajaJ;
	}

	public JButton getBtnAltaA() {
		return btnAltaA;
	}

	public void setBtnAltaA(JButton btnAltaA) {
		this.btnAltaA = btnAltaA;
	}

	public JButton getBtnBajaA() {
		return btnBajaA;
	}

	public void setBtnBajaA(JButton btnBajaA) {
		this.btnBajaA = btnBajaA;
	}

	public JButton getBtnAltaT() {
		return btnAltaT;
	}

	public void setBtnAltaT(JButton btnAltaT) {
		this.btnAltaT = btnAltaT;
	}

	public JButton getBtnAñadirJ() {
		return btnAñadirJ;
	}

	public void setBtnAñadirJ(JButton btnAñadirJ) {
		this.btnAñadirJ = btnAñadirJ;
	}

	public JButton getBtnClasificarJ() {
		return btnClasificarJ;
	}

	public void setBtnClasificarJ(JButton btnClasificarJ) {
		this.btnClasificarJ = btnClasificarJ;
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public void setBtnSeleccionar(JButton btnSeleccionar) {
		this.btnSeleccionar = btnSeleccionar;
	}

	public JButton getBtnModificarJ() {
		return btnModificarJ;
	}

	public void setBtnModificarJ(JButton btnModificarJ) {
		this.btnModificarJ = btnModificarJ;
	}

	public JComboBox<String> getComboBoxJugadores() {
		return comboBoxJugadores;
	}

	public void setComboBoxJugadores(JComboBox<String> comboBoxJugadores) {
		this.comboBoxJugadores = comboBoxJugadores;
	}

	public JComboBox<String> getComboBoxProvincia() {
		return comboBoxProvincia;
	}

	public void setComboBoxProvincia(JComboBox<String> comboBoxProvincia) {
		this.comboBoxProvincia = comboBoxProvincia;
	}

	public JComboBox<Integer> getComboBoxCodA() {
		return comboBoxCodA;
	}

	public void setComboBoxCodA(JComboBox<Integer> comboBoxCodA) {
		this.comboBoxCodA = comboBoxCodA;
	}

	public JTextField getTextNombreA() {
		return textNombreA;
	}

	public void setTextNombreA(JTextField textNombreA) {
		this.textNombreA = textNombreA;
	}

	public JTextField getTextNombreT() {
		return textNombreT;
	}

	public void setTextNombreT(JTextField textNombreT) {
		this.textNombreT = textNombreT;
	}

	public JTextField getTextFechaT() {
		return textFechaT;
	}

	public void setTextFechaT(JTextField textFechaT) {
		this.textFechaT = textFechaT;
	}

	public JTextField getTextCodigoT() {
		return textCodigoT;
	}

	public void setTextCodigoT(JTextField textCodigoT) {
		this.textCodigoT = textCodigoT;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	public JLabel getLblNombreJ() {
		return lblNombreJ;
	}

	public void setLblNombreJ(JLabel lblNombreJ) {
		this.lblNombreJ = lblNombreJ;
	}

	public JLabel getLblNicknameJ() {
		return lblNicknameJ;
	}

	public void setLblNicknameJ(JLabel lblNicknameJ) {
		this.lblNicknameJ = lblNicknameJ;
	}

	public JLabel getLblFechNacJ() {
		return lblFechNacJ;
	}

	public void setLblFechNacJ(JLabel lblFechNacJ) {
		this.lblFechNacJ = lblFechNacJ;
	}

	public JLabel getLblProvinciaJ() {
		return lblProvinciaJ;
	}

	public void setLblProvinciaJ(JLabel lblProvinciaJ) {
		this.lblProvinciaJ = lblProvinciaJ;
	}

	public JLabel getLblSeleccionarJ() {
		return lblSeleccionarJ;
	}

	public void setLblSeleccionarJ(JLabel lblSeleccionarJ) {
		this.lblSeleccionarJ = lblSeleccionarJ;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JLabel getLblCodigoA() {
		return lblCodigoA;
	}

	public void setLblCodigoA(JLabel lblCodigoA) {
		this.lblCodigoA = lblCodigoA;
	}

	public JLabel getLblNombreA() {
		return lblNombreA;
	}

	public void setLblNombreA(JLabel lblNombreA) {
		this.lblNombreA = lblNombreA;
	}

	public JCheckBox getCheckboxActivo() {
		return checkboxActivo;
	}

	public void setCheckboxActivo(JCheckBox checkboxActivo) {
		this.checkboxActivo = checkboxActivo;
	}

	public JComboBox getComboBoxCodigoA() {
		return comboBoxCodigoA;
	}

	public void setComboBoxCodigoA(JComboBox comboBoxCodigoA) {
		this.comboBoxCodigoA = comboBoxCodigoA;
	}

	public List<String> getJugadoresActivos() {
		return jugadoresActivos;
	}

	public void setJugadoresActivos(List<String> jugadoresActivos) {
		this.jugadoresActivos = jugadoresActivos;
	}

	public JTextField getTextFecha() {
		return textFecha;
	}

	public void setTextFecha(JTextField textFecha) {
		this.textFecha = textFecha;
	}

	public JTextField getTextPlazas() {
		return textPlazas;
	}

	public void setTextPlazas(JTextField textPlazas) {
		this.textPlazas = textPlazas;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnVolverJ)) {
			volverMenuJ();
		} else if (e.getSource().equals(btnVolverA)) {
			volverMenuA();
		} else if (e.getSource().equals(btnVolverT)) {
			volverMenuT();
		} else if (e.getSource().equals(btnAltaJ)) {
			DarDeAltaJugador();
		} else if (e.getSource().equals(btnBajaJ)) {
			DarDeBajaJugador();
		} else if (e.getSource().equals(btnAltaA)) {
			DarDeAltaArbitro();
		} else if (e.getSource().equals(btnBajaA)) {
			DarDeBajaArbitro();
		} else if (e.getSource().equals(btnSeleccionar)) {
			SeleccionarJugador();
		} else if (e.getSource().equals(btnModificarJ)) {
			Modificarjugador();
		} else if (e.getSource().equals(btnAltaT)) {
			altaTorneo();
		} else if (e.getSource().equals(btnAñadirJ)) {
			anadir();
		} else if (e.getSource().equals(btnClasificarJ)) {
			clasificarJugador();
		}
	}

	private void clasificarJugador() {
		VClasificarJ clasificar = new VClasificarJ();
		clasificar.setModal(true);
		clasificar.setVisible(true);
	}

	private void anadir() {
		VAnadirJ anadir = new VAnadirJ();
		anadir.setModal(true);
		anadir.setVisible(true);
	}

	// Métodos para la pestaña Torneo
	private void altaTorneo() {
		try {
			String nombre = textNombreT.getText();
			String codigo = textCodigoT.getText();
			String codigoA = comboBoxCodigoA.getSelectedItem().toString();

			// Validar que no haya campos vacíos
			if (nombre.isEmpty() || codigo.isEmpty() || codigoA.isEmpty() || textFechaT.getText().isEmpty()
					|| textPlazas.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Validar fecha correctamente
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate fecha;
			try {
				fecha = LocalDate.parse(textFechaT.getText(), formatter);
				if (fecha.isBefore(LocalDate.now())) {
					JOptionPane.showMessageDialog(this, "La fecha no puede ser anterior a la fecha actual", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			} catch (DateTimeParseException e) {
				JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Usa dd/MM/yyyy", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Validar plazas
			int plazas;
			try {
				plazas = Integer.parseInt(textPlazas.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "El número de plazas debe ser un valor numérico", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			boolean estaActivo = checkboxActivo.isSelected();

			Torneo tor = new Torneo(nombre, codigo, fecha, plazas, codigoA, estaActivo);
			Main.altaTorneo(tor);

			JOptionPane.showMessageDialog(this, "Torneo dado de alta correctamente", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);

			// Limpiar campos después de dar de alta
			textNombreT.setText("");
			textFecha.setText("");
			textCodigoT.setText("");
			textPlazas.setText("");
			comboBoxCodigoA.setSelectedIndex(-1);
			checkboxActivo.setSelected(false);

			// Habilitar botones para añadir jugadores y clasificarlos
			btnAñadirJ.setEnabled(true);
			btnClasificarJ.setEnabled(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error al dar de alta al torneo: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}


	private void Modificarjugador() {
		// Obtener el nickname original del jugador seleccionado en comboBoxJugadores
		String nicknameOriginal = (String) comboBoxJugadores.getSelectedItem();
		String nuevoNickname = textNicknameJ.getText().trim();
		String nombre = textNombreJ.getText().trim();
		String fechaNacimiento = textFachNacJ.getText().trim();
		String provinciaSeleccionada = (String) comboBoxProvincia.getSelectedItem();

		if (nuevoNickname.isEmpty() || nombre.isEmpty() || fechaNacimiento.isEmpty() || provinciaSeleccionada == null) {
			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
			return;
		}

		DaoImplementacionMySql dao = new DaoImplementacionMySql();
		try {
			// Obtener el ID de la provincia
			int idP = dao.obtenerIdProvincia(provinciaSeleccionada);

			// Modificar el jugador en la base de datos usando el nickname original
			dao.modificarJugador(nicknameOriginal, nuevoNickname, nombre, fechaNacimiento, idP);

			// Si se modifica correctamente, limpiar los campos
			textNombreJ.setText("");
			textNicknameJ.setText("");
			textFachNacJ.setText("");
			comboBoxProvincia.setSelectedIndex(0);

			// Actualizar el comboBoxJugadores
			comboBoxJugadores.removeItem(nicknameOriginal);
			comboBoxJugadores.addItem(nuevoNickname);
			comboBoxJugadores.setSelectedItem(nuevoNickname);

			this.setVisible(false);

			JOptionPane.showMessageDialog(this, "Jugador modificado correctamente.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al modificar el jugador: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void SeleccionarJugador() {
		String nicknameSeleccionado = (String) comboBoxJugadores.getSelectedItem();

		if (nicknameSeleccionado == null) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione un jugador.");
			return;
		}

		DaoImplementacionMySql dao = new DaoImplementacionMySql();
		try {
			// Obtener los datos del jugador por su nickname
			Object[] datosJugador = dao.obtenerJugadorPorNickname(nicknameSeleccionado);

			// Asignar los datos a los campos correspondientes
			textNombreJ.setText((String) datosJugador[0]);
			textNicknameJ.setText((String) datosJugador[1]);
			textFachNacJ.setText(datosJugador[2].toString());
			int idProvincia = (int) datosJugador[3];

			// Obtener el nombre de la provincia usando el id
			String nombreProvincia = dao.obtenerNombreProvinciaPorId(idProvincia);
			comboBoxProvincia.setSelectedItem(nombreProvincia);

			// Inhabilitar el comboBoxJugadores
			comboBoxJugadores.setEnabled(false); // Deshabilitar el comboBox

			// Hacer visibles los campos y etiquetas
			lblNombreJ.setVisible(true);
			lblNicknameJ.setVisible(true);
			lblFechNacJ.setVisible(true);
			lblProvinciaJ.setVisible(true);
			textNombreJ.setVisible(true);
			textNicknameJ.setVisible(true);
			textFachNacJ.setVisible(true);
			comboBoxProvincia.setVisible(true);
			btnModificarJ.setVisible(true);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al obtener los datos del jugador: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void DarDeBajaArbitro() {
		Integer codigoArbitro = (Integer) comboBoxCodA.getSelectedItem();

		if (codigoArbitro == null) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione un código de árbitro.");
			return;
		}

		DaoImplementacionMySql dao = new DaoImplementacionMySql();
		try {
			dao.eliminarArbitro(codigoArbitro);
			JOptionPane.showMessageDialog(null, "Árbitro eliminado correctamente.");

			comboBoxCodA.removeItem(codigoArbitro);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al eliminar el árbitro: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	private void DarDeAltaArbitro() {
		String nombreArbitro = textNombreA.getText().trim();

		DaoImplementacionMySql dao = new DaoImplementacionMySql();
		try {
			dao.darDeAltaArbitro(nombreArbitro);
			JOptionPane.showMessageDialog(null, "Árbitro dado de alta correctamente.");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al dar de alta al árbitro: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	private void DarDeBajaJugador() {
		String nicknameSeleccionado = (String) comboBoxJugadores.getSelectedItem();

		if (nicknameSeleccionado == null) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione un jugador para dar de baja.");
			return;
		}

		DaoImplementacionMySql dao = new DaoImplementacionMySql();

		try {
			dao.eliminarJugador(nicknameSeleccionado);
			JOptionPane.showMessageDialog(this, "Jugador dado de baja correctamente.");

			comboBoxJugadores.removeItem(nicknameSeleccionado);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al dar de baja el jugador: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void DarDeAltaJugador() {
		String nombre = textNombreJ.getText();
		String nickname = textNicknameJ.getText();
		String fechaNacimiento = textFachNacJ.getText();
		String provinciaSeleccionada = (String) comboBoxProvincia.getSelectedItem();

		if (nombre.isEmpty() || nickname.isEmpty() || fechaNacimiento.isEmpty() || provinciaSeleccionada.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
			return;
		}

		DaoImplementacionMySql dao = new DaoImplementacionMySql();

		try {
			int idP = dao.obtenerIdProvincia(provinciaSeleccionada);
			dao.insertarJugador(nombre, nickname, fechaNacimiento, idP);
			// Si se modifica correctamente, limpiar los campos
			textNombreJ.setText("");
			textNicknameJ.setText("");
			textFachNacJ.setText("");
			comboBoxProvincia.setSelectedIndex(0);

			JOptionPane.showMessageDialog(this, "Jugador dado de alta correctamente.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al insertar el jugador: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void volverMenuT() {
		this.getTextNombreA().setVisible(false);
		this.getTextCodigoT().setVisible(false);
		this.getTextFechaT().setVisible(false);
		this.getBtnVolverT().setVisible(false);
		this.getBtnClasificarJ().setVisible(false);
		this.getBtnAñadirJ().setVisible(false);
		this.getBtnAltaT().setVisible(false);
		this.setVisible(false);

	}

	private void volverMenuA() {
		this.getTextNombreA().setVisible(false);
		this.getBtnAltaA().setVisible(false);
		this.getBtnBajaA().setVisible(false);
		this.getBtnVolverA().setVisible(false);
		this.setVisible(false);
	}

	private void volverMenuJ() {
		this.getTextNombreJ().setVisible(false);
		this.getTextNicknameJ().setVisible(false);
		this.getTextFachNacJ().setVisible(false);
		this.getComboBoxProvincia().setVisible(false);
		this.getBtnAltaJ().setVisible(false);
		this.getBtnSeleccionar().setVisible(false);
		this.getComboBoxJugadores().setVisible(false);
		this.getBtnVolverJ().setVisible(false);
		this.setVisible(false);
	}

	private void inicializarComboBoxProvincias() {
		DaoImplementacionMySql dao = new DaoImplementacionMySql();
		try {
			List<String> provincias = dao.obtenerProvincias();
			for (String provincia : provincias) {
				comboBoxProvincia.addItem(provincia);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al cargar provincias: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
