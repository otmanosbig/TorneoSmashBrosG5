package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VPrincipal extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    public static void main(String[] args) {
        try {
            VPrincipal dialog = new VPrincipal();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public VPrincipal() {
        setBounds(100, 100, 609, 426);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // Botón "Ver" que abre la ventana VSinIniciar
        JButton btnVer = new JButton("Ver");
        btnVer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VSinIniciar vsinIniciar = new VSinIniciar();
                vsinIniciar.setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });
        btnVer.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVer.setBounds(82, 182, 102, 21);
        contentPanel.add(btnVer);

        // Botón "Iniciar Sesion" que abre la ventana Login
        JButton btnIniciarSesion = new JButton("Iniciar Sesion");
        btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crea y muestra la ventana de login
                Login login = new Login();
                login.setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });
        btnIniciarSesion.setBounds(314, 182, 152, 21);
        contentPanel.add(btnIniciarSesion);
    }
}

