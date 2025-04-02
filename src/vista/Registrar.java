package vista;

import controlador.DaoImplementacionMySql;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Registrar extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JPasswordField passwordFieldContAdmin;
    private JPasswordField passwordField_1;
    private JTextField textField_1;
    private JButton btnVolverR;
    private JButton btnRegistrarR;
    
    public Registrar() {
        setBounds(100, 100, 684, 485);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        btnVolverR = new JButton("Volver");
        btnVolverR.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnVolverR.setBounds(47, 362, 95, 21);
        btnVolverR.addActionListener(this);
        contentPanel.add(btnVolverR);

        btnRegistrarR = new JButton("Registrar");
        btnRegistrarR.setEnabled(false);
        btnRegistrarR.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnRegistrarR.setBounds(481, 364, 118, 21);
        btnRegistrarR.addActionListener(this);
        contentPanel.add(btnRegistrarR);

        passwordFieldContAdmin = new JPasswordField();
        passwordFieldContAdmin.setBounds(175, 274, 118, 19);
        contentPanel.add(passwordFieldContAdmin);

        JLabel lblNewLabel_1_2_1 = new JLabel("Contraseña:");
        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1_2_1.setBounds(175, 221, 109, 20);
        contentPanel.add(lblNewLabel_1_2_1);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(277, 224, 204, 19);
        contentPanel.add(passwordField_1);

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblUser.setBounds(198, 171, 78, 43);
        contentPanel.add(lblUser);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_1.setColumns(10);
        textField_1.setBounds(278, 183, 203, 19);
        contentPanel.add(textField_1);

        passwordFieldContAdmin.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarPassword();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarPassword();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarPassword();
				
			}
        });
    }
    private void validarPassword() {
        String password = new String(passwordFieldContAdmin.getPassword());
        btnRegistrarR.setEnabled(password.contains("Admin210"));
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnVolverR)) {
        	volverLogin();
        } else if (e.getSource().equals(btnRegistrarR)) {
        	registro();
        }
    }
	private void registro() {
	    String usuario = textField_1.getText();
	    String contrasena = new String(passwordField_1.getPassword());
	    
	    if (usuario.isEmpty() || contrasena.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    Usuario nuevoUsuario = new Usuario(usuario, contrasena);
	    
	    DaoImplementacionMySql dao = new DaoImplementacionMySql();
	    
	    try {
	        dao.registrar(nuevoUsuario);
	        JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	    Login login = new Login();
	    this.setVisible(false);
	    login.setVisible(true);  
	}

	private void volverLogin() {
		Login login = new Login();
		this.setVisible(false);
		login.setVisible(true);
	}
}