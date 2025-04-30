package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.Main;
import excepciones.GlobalException;
import modelo.Juega;
import modelo.Torneo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VClasificarJ extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBoxJugador,comboBoxPosicion,comboBoxTorneo;
	private JButton btnBuscar, btnVolver, btnGuardar;


	public VClasificarJ() {
		setBounds(100, 100, 679, 459);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(30, 0, 50)); 

		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setForeground(Color.WHITE);
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNickname.setBounds(109, 136, 100, 25);
		contentPanel.add(lblNickname);

		// JComboBox para seleccionar un jugador
		comboBoxJugador = new JComboBox<String>();
		comboBoxJugador.setBackground(Color.BLACK);
		comboBoxJugador.setBounds(187, 138, 200, 25);
		contentPanel.add(comboBoxJugador);

		JLabel lblTorneo = new JLabel("Torneo:");
		lblTorneo.setBackground(Color.BLACK);
		lblTorneo.setForeground(Color.WHITE);
		lblTorneo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTorneo.setBounds(125, 89, 100, 25);
		contentPanel.add(lblTorneo);

		// JComboBox para seleccionar un torneo
		comboBoxTorneo = new JComboBox<String>();
		comboBoxTorneo.setForeground(Color.WHITE);
		comboBoxTorneo.setBounds(187, 91, 200, 25);
		contentPanel.add(comboBoxTorneo);

		btnVolver = new JButton("Volver");
		btnVolver.setForeground(Color.RED);
		btnVolver.setBackground(Color.BLACK);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVolver.setBounds(42, 355, 134, 30);
		btnVolver.addActionListener(this); 
		contentPanel.add(btnVolver);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.BLUE);
		btnGuardar.setBackground(Color.BLACK);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(466, 355, 134, 30);
		btnGuardar.addActionListener(this); 
		contentPanel.add(btnGuardar);

		JLabel lblposicion = new JLabel("Posicion:");
		lblposicion.setForeground(Color.WHITE);
		lblposicion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblposicion.setBounds(109, 189, 78, 25);
		contentPanel.add(lblposicion);

		comboBoxPosicion = new JComboBox();
		comboBoxPosicion.setForeground(Color.WHITE);
		comboBoxPosicion.setBackground(Color.BLACK);
		comboBoxPosicion.setBounds(187, 189, 202, 25);
		contentPanel.add(comboBoxPosicion);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(418, 93, 88, 21);
		contentPanel.add(btnBuscar);
		btnBuscar.addActionListener(this);


		JLabel lblClasificaccionDelJugadores = new JLabel("Clasificacion del jugadores");
		lblClasificaccionDelJugadores.setForeground(Color.WHITE);
		lblClasificaccionDelJugadores.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		lblClasificaccionDelJugadores.setBounds(190, 29, 250, 25);
		contentPanel.add(lblClasificaccionDelJugadores);
		
		cargarDatos();
	}


	private void cargarDatos() {
		try {
			comboBoxJugador.setEnabled(false);
			List<Torneo> torneos = Main.obtenerTorneo();
			for (Torneo torneo : torneos) {
				comboBoxTorneo.addItem(torneo.getCodigoT());
			}
			for (int i = 1; i <= 5; i++) {
	            comboBoxPosicion.addItem(Integer.toString(i));
	        }
		} catch (GlobalException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnGuardar)) {
			guardarDatos();
		}else if(e.getSource().equals(btnVolver)){
			volver();
		}else if(e.getSource().equals(btnBuscar)) {
			buscarDatos();
		}
	}


	private void volver() {
		dispose();
	}


	private void buscarDatos() {
		try {
			comboBoxJugador.removeAllItems();
			List<Juega> nombreJugadores = Main.obtenerJugadoresConTorneo(comboBoxTorneo.getSelectedItem().toString());
			System.out.println(nombreJugadores.size());
			for (Juega ju : nombreJugadores) {
				comboBoxJugador.addItem(ju.getNickname());
			}
			comboBoxJugador.setEnabled(true);
		} catch (GlobalException e) {
			e.printStackTrace();
		}
	}


	private void guardarDatos() {
	    try {
	        String torneo = (String) comboBoxTorneo.getSelectedItem();
	        String nickname = (String) comboBoxJugador.getSelectedItem();
	        String posiciones = (String) comboBoxPosicion.getSelectedItem();

	        if (torneo == null || nickname == null || posiciones == null) {
	            JOptionPane.showMessageDialog(this, "Todos los campos deben estar seleccionados.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        int posicion = Integer.parseInt(posiciones);
	        System.out.println("POSICION: " + posicion);
	        Juega juega = new Juega();
	        juega.setCodigoT(torneo);
	        juega.setNickname(nickname);
	        juega.setPosicion(posicion); 
	        
	        
	        Main.clasificarJugador(juega); 
	        JOptionPane.showMessageDialog(this, "Clasificación guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        dispose(); 

	    } catch (GlobalException ex) {
	        JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "La posición debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

}
