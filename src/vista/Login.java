package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controlador.DaoImplementacionMySql;
import java.awt.Color;

public class Login extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textUsuario;
    private JPasswordField passwordField;
    private JButton btnlogin;
    private JButton btnRegistrar;
    private JButton btnEntrar, btnSalir;

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 655, 493);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(30, 0, 50)); 
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setForeground(Color.LIGHT_GRAY);
        lblUser.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblUser.setBounds(149, 145, 100, 43);
        contentPane.add(lblUser);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setForeground(Color.LIGHT_GRAY);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblPassword.setBounds(118, 213, 113, 43);
        contentPane.add(lblPassword);

        textUsuario = new JTextField();
        textUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textUsuario.setBackground(new Color(50, 0, 70));
        textUsuario.setForeground(Color.WHITE);
        textUsuario.setBounds(241, 153, 200, 30);
        contentPane.add(textUsuario);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordField.setBackground(new Color(50, 0, 70));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBounds(241, 221, 200, 30);
        contentPane.add(passwordField);

        btnlogin = new JButton("Log In");
        btnlogin.setBackground(new Color(200, 0, 0)); 
        btnlogin.setForeground(Color.BLUE); 
        btnlogin.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnlogin.setBounds(400, 300, 150, 45);
        btnlogin.addActionListener(this);
        contentPane.add(btnlogin);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(30, 0, 50)); 
        btnRegistrar.setForeground(new Color(255, 80, 100)); 
        btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnRegistrar.setBounds(100, 300, 150, 45);
        btnRegistrar.addActionListener(this);
        contentPane.add(btnRegistrar);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(30, 0, 50));
        btnEntrar.setForeground(new Color(255, 80, 100));
        btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnEntrar.setBounds(530, 10, 85, 32);
        btnEntrar.addActionListener(this);
        contentPane.add(btnEntrar);

        btnSalir = new JButton("Salir");
        btnSalir.setBackground(new Color(30, 0, 50));
        btnSalir.setForeground(new Color(255, 80, 100));
        btnSalir.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSalir.setBounds(30, 10, 85, 32);
        btnSalir.addActionListener(this);
        contentPane.add(btnSalir);
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
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } else if (e.getSource().equals(btnRegistrar)) {
            Registrar registrar = new Registrar();
            this.setVisible(false);
            registrar.setVisible(true);
        } else if (e.getSource().equals(btnEntrar)) {
            VListar vListar = new VListar();
            this.setVisible(false);
            vListar.setVisible(true);
        } else if (e.getSource().equals(btnSalir)) {
            dispose();
        }
    }
}
