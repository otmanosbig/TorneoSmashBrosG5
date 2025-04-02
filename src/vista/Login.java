package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.DaoImplementacionMySql;

public class Login extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textUsuario;
    private JPasswordField passwordField;
    private JButton btnlogin;
    private JButton btnRegistrar;

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 655, 493);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblUser.setBounds(149, 145, 78, 43);
        contentPane.add(lblUser);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblPassword.setBounds(118, 213, 113, 43);
        contentPane.add(lblPassword);

        textUsuario = new JTextField();
        textUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textUsuario.setBounds(241, 153, 162, 26);
        contentPane.add(textUsuario);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordField.setBounds(241, 221, 162, 26);
        contentPane.add(passwordField);

        btnlogin = new JButton("Log In");
        btnlogin.setBounds(432, 302, 113, 43);
        btnlogin.addActionListener(this);
        contentPane.add(btnlogin);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(96, 302, 131, 43);
        btnRegistrar.addActionListener(this);
        contentPane.add(btnRegistrar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource().equals(btnlogin)) {
            String usuario = textUsuario.getText().trim();
            String contrasena = new String(passwordField.getPassword());

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese usuario y contraseña.");
                return;
            }

            DaoImplementacionMySql dao = new DaoImplementacionMySql();
            
            Gestion gestion = new Gestion(this);


            try {
                if (dao.validarUsuario(usuario, contrasena)) {
                    this.setVisible(false);
                    gestion.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
            }
        } else if (e.getSource().equals(btnRegistrar)) {
            Registrar registrar = new Registrar();
            this.setVisible(false);
            registrar.setVisible(true);
        }
    }
}