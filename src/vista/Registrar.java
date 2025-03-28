package vista;

import controlador.DaoImplementacionMySql;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Registrar extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private JTextField textField;
    private JTextField textField_1;
    private JButton btnVolverR;
    private JCheckBox chckbxNewCheckBox;
    private boolean esAdmin = false;  // Variable para saber si el usuario es admin

    public Registrar() {
        setBounds(100, 100, 684, 485);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabel_1_2 = new JLabel("Nombre:");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1_2.setBounds(198, 141, 70, 20);
        contentPanel.add(lblNewLabel_1_2);

        btnVolverR = new JButton("Volver");
        btnVolverR.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnVolverR.setBounds(47, 362, 95, 21);
        btnVolverR.addActionListener(this);
        contentPanel.add(btnVolverR);

        JButton btnRegistrarR = new JButton("Registrar");
        btnRegistrarR.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnRegistrarR.setBounds(481, 364, 118, 21);
        contentPanel.add(btnRegistrarR);

        chckbxNewCheckBox = new JCheckBox("¿Eres Admin?");
        chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 16));
        chckbxNewCheckBox.setBounds(175, 247, 154, 21);
        contentPanel.add(chckbxNewCheckBox);

        // Agregar un ActionListener para cuando se marque o desmarque la casilla
        chckbxNewCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chckbxNewCheckBox.isSelected()) {
                    passwordField.setEnabled(true);  // Habilitar el campo de contraseña
                    esAdmin = true;  // El usuario es admin
                } else {
                    passwordField.setEnabled(false);  // Deshabilitar el campo de contraseña
                    passwordField.setText("");  // Limpiar el campo de contraseña
                    esAdmin = false;  // El usuario no es admin
                }
            }
        });

        passwordField = new JPasswordField();
        passwordField.setEnabled(false);  // Inicialmente deshabilitado
        passwordField.setBounds(175, 274, 118, 19);
        contentPanel.add(passwordField);

        JLabel lblNewLabel_1_2_1 = new JLabel("Contraseña:");
        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1_2_1.setBounds(175, 221, 109, 20);
        contentPanel.add(lblNewLabel_1_2_1);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(277, 224, 204, 19);
        contentPanel.add(passwordField_1);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField.setBounds(278, 142, 203, 19);
        contentPanel.add(textField);
        textField.setColumns(10);

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblUser.setBounds(198, 171, 78, 43);
        contentPanel.add(lblUser);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_1.setColumns(10);
        textField_1.setBounds(278, 183, 203, 19);
        contentPanel.add(textField_1);

        // Botón para registrar
        btnRegistrarR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textField_1.getText();
                String nombre = textField.getText();
                String contrasena = new String(passwordField_1.getPassword());

                // Si el checkbox está marcado, el usuario será admin
                if (esAdmin) {
                    String contrasenaAdmin = new String(passwordField.getPassword());
                    if (!contrasenaAdmin.equals("admin")) {
                        JOptionPane.showMessageDialog(null, "La contraseña de administrador no es correcta.");
                        return;  // No registrar el usuario si la contraseña de admin es incorrecta
                    }

                Usuario nuevoUsuario = new Usuario(usuario, contrasena, nombre);

                DaoImplementacionMySql dao = new DaoImplementacionMySql();
                try {
                    // Registrar el usuario en la base de datos
                    dao.registrar(nuevoUsuario);
                    JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar el usuario: " + ex.getMessage());
                }
            }
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnVolverR)) {
        	volverLogin();
        }
    }
	private void volverLogin() {
		Login login = new Login();
		this.setVisible(false);
		login.setVisible(true);
	}
}