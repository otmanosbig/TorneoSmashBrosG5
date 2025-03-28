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
            // Obtener el usuario y la contraseña desde los campos de texto
            String usuario = textUsuario.getText().trim();  // .trim() para eliminar espacios al principio y al final
            String contrasena = new String(passwordField.getPassword());  // .trim() para eliminar espacios

            // Verificar si los campos están vacíos
            if (usuario.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese usuario y contraseña.");
                return;  // Salir del método sin proceder más
            }

            // Crear una instancia de DaoImplementacionMySql
            DaoImplementacionMySql dao = new DaoImplementacionMySql();
            
            Gestion gestion = new Gestion(this);


            try {
                // Validar usuario llamando a validarUsuario
                if (dao.validarUsuario(usuario, contrasena)) {
                    // Si el usuario existe, ocultamos la ventana de login y mostramos la siguiente ventana
                    this.setVisible(false);  // Ocultar la ventana de login
                    gestion.setVisible(true);  // Mostrar la siguiente ventana
                } else {
                    // Si el usuario o la contraseña son incorrectos, mostrar un mensaje de error
                    JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
                }
            } catch (SQLException ex) {
                // En caso de error en la base de datos
                JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
            }
        } else if (e.getSource().equals(btnRegistrar)) {
            // Si se presiona el botón de registrar, abrir la ventana de registro
            Registrar registrar = new Registrar();
            this.setVisible(false);
            registrar.setVisible(true);
        }
    }
}
