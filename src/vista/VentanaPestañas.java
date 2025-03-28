package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import vista.Gestion;
import com.toedter.calendar.JDateChooser;



public class VentanaPestañas extends JDialog implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textNombreJ;
    private JTextField textNicknameJ;
    private JTextField textProvinciaJ;
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
    private JComboBox comboBoxJugadores;
    private JTextField textNombreA;
    private JTextField textCodA;
    private JTextField textNombreT;
    private JTextField textCodigoT;
    private JDateChooser dateChooserFechaNac;
    private JDateChooser dateChooserFechaT;

    // Constructor que inicializa la ventana correctamente
    public VentanaPestañas(JDialog parent) {
        super(parent, "SmashBros Gestionar", true);
        setSize(674, 511);
        setLocationRelativeTo(parent);

        // Panel 1 (Gestión de Jugador) - NO SE TOCA, SE MANTIENE COMO ESTABA
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 674, 511);
        getContentPane().setLayout(null);
        getContentPane().add(tabbedPane);
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        
     // Agregar componentes a panel1 (como botones, etiquetas, etc.)
        JLabel label1 = new JLabel("Panel de Jugadores");
        label1.setBounds(10, 10, 200, 25);
        panel1.add(label1);
        
        tabbedPane.addTab("Gestionar Jugador", panel1);

        JLabel lblNewLabel = new JLabel("Nombre:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(179, 54, 70, 13);
        panel1.add(lblNewLabel);

        JLabel lblNickname = new JLabel("Nickname:");
        lblNickname.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNickname.setBounds(172, 89, 86, 13);
        panel1.add(lblNickname);

        JLabel lblNewLabel_2 = new JLabel("Fecha de nacimiento:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2.setBounds(85, 128, 173, 13);
        panel1.add(lblNewLabel_2);

        JLabel lblProvincia = new JLabel("Provincia:");
        lblProvincia.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblProvincia.setBounds(172, 169, 86, 13);
        panel1.add(lblProvincia);

        textNombreJ = new JTextField();
        textNombreJ.setEnabled(false);
        textNombreJ.setBounds(269, 53, 173, 19);
        panel1.add(textNombreJ);

        textNicknameJ = new JTextField();
        textNicknameJ.setEnabled(false);
        textNicknameJ.setBounds(268, 88, 173, 19);
        panel1.add(textNicknameJ);

        textProvinciaJ = new JTextField();
        textProvinciaJ.setEnabled(false);
        textProvinciaJ.setBounds(268, 168, 173, 19);
        panel1.add(textProvinciaJ);

        btnAltaJ = new JButton("Alta");
        btnAltaJ.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAltaJ.setEnabled(false);
        btnAltaJ.setBounds(461, 52, 150, 21);
        panel1.add(btnAltaJ);

        btnBajaJ = new JButton("Baja");
        btnBajaJ.setEnabled(false);
        btnBajaJ.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBajaJ.setBounds(461, 88, 150, 21);
        panel1.add(btnBajaJ);

        JButton btnModificarJ = new JButton("Modificar");
        btnModificarJ.setEnabled(false);
        btnModificarJ.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnModificarJ.setBounds(461, 127, 150, 21);
        panel1.add(btnModificarJ);

        btnVolverJ = new JButton("Volver");
        btnVolverJ.setEnabled(false);
        btnVolverJ.addActionListener(this);
        btnVolverJ.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnVolverJ.setBounds(280, 267, 85, 21);
        panel1.add(btnVolverJ);
        
        JLabel lblSeleccionar = new JLabel("Seleccionar:");
        lblSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblSeleccionar.setBounds(153, 213, 122, 13);
        panel1.add(lblSeleccionar);
        
        comboBoxJugadores = new JComboBox();
        comboBoxJugadores.setEnabled(false);
        comboBoxJugadores.setBounds(269, 211, 173, 21);
        panel1.add(comboBoxJugadores);
        
        btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSeleccionar.setEnabled(false);
        btnSeleccionar.setBounds(461, 167, 150, 21);
        panel1.add(btnSeleccionar);
        
        JDateChooser dateChooserFechaNac = new JDateChooser();
        dateChooserFechaNac.setBounds(268, 122, 174, 25);
        panel1.add(dateChooserFechaNac);
        
        // Panel 2 (Gestión de Árbitros) - SE MANTIENE IGUAL
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        tabbedPane.addTab("Gestionar Arbitro", panel2);
        
        // Crear un JLabel para el título del panel de Árbitros
        JLabel label2 = new JLabel("Panel de Arbitros");
        label2.setBounds(10, 10, 200, 25); // Define las coordenadas para el título
        panel2.add(label2);

        JLabel lblNewLabel_1 = new JLabel("Nombre:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setBounds(135, 94, 83, 13);
        panel2.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Codigo:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1_1.setBounds(135, 134, 72, 19);
        panel2.add(lblNewLabel_1_1);

        btnAltaA = new JButton("Alta");
        btnAltaA.setEnabled(false);
        btnAltaA.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAltaA.setBounds(410, 92, 150, 21);
        panel2.add(btnAltaA);

        btnBajaA = new JButton("Baja");
        btnBajaA.setEnabled(false);
        btnBajaA.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBajaA.setBounds(410, 135, 150, 21);
        panel2.add(btnBajaA);

        btnVolverA = new JButton("Volver");
        btnVolverA.setEnabled(false);
        btnVolverA.addActionListener(this);
        btnVolverA.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnVolverA.setBounds(295, 264, 85, 21);
        panel2.add(btnVolverA);
        
        textNombreA = new JTextField();
        textNombreA.setEnabled(false);
        textNombreA.setBounds(214, 93, 173, 19);
        panel2.add(textNombreA);
        
        textCodA = new JTextField();
        textCodA.setEnabled(false);
        textCodA.setBounds(214, 136, 173, 19);
        panel2.add(textCodA);

        // Panel 3 (Gestión de Torneos) - NO SE TOCA
        JPanel panel3 = new JPanel();
        panel3.setLayout(null);
        tabbedPane.addTab("Gestionar Torneos", panel3);
        
        JLabel label3 = new JLabel("Panel de Torneos");
        label3.setBounds(10, 10, 200, 25); // Define las coordenadas para el título
        panel3.add(label3);

        JLabel lblNewLabel_1_2 = new JLabel("Nombre:");
        lblNewLabel_1_2.setBounds(168, 89, 70, 20);
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel3.add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_1_1 = new JLabel("Fecha:");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1_1_1.setBounds(178, 138, 60, 19);
        panel3.add(lblNewLabel_1_1_1);
        
        textNombreT = new JTextField();
        textNombreT.setEnabled(false);
        textNombreT.setBounds(248, 92, 173, 19);
        panel3.add(textNombreT);
        
        btnAltaT = new JButton("Alta");
        btnAltaT.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAltaT.setEnabled(false);
        btnAltaT.setBounds(465, 91, 150, 21);
        panel3.add(btnAltaT);
        
        btnAñadirJ = new JButton("AñadirJ");
        btnAñadirJ.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAñadirJ.setEnabled(false);
        btnAñadirJ.setBounds(49, 319, 161, 21);
        panel3.add(btnAñadirJ);
        
        btnClasificarJ = new JButton("ClasificarJ");
        btnClasificarJ.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnClasificarJ.setEnabled(false);
        btnClasificarJ.setBounds(248, 319, 161, 21);
        panel3.add(btnClasificarJ);
        
        JLabel lblNewLabel_1_2_1 = new JLabel("Codigo:");
        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1_2_1.setBounds(168, 181, 70, 20);
        panel3.add(lblNewLabel_1_2_1);
        
        textCodigoT = new JTextField();
        textCodigoT.setEnabled(false);
        textCodigoT.setBounds(248, 184, 173, 19);
        panel3.add(textCodigoT);
        
        btnVolverT = new JButton("Volver");
        btnVolverT.setEnabled(false);
        btnVolverT.addActionListener(this);
        btnVolverT.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnVolverT.setBounds(248, 244, 85, 21);
        panel3.add(btnVolverT);
        
        JDateChooser dateChooserFechaT = new JDateChooser();
        dateChooserFechaT.setBounds(248, 132, 174, 25);
        panel3.add(dateChooserFechaT);

    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JTextField getTextNombreJ() {
        return textNombreJ;
    }

    public JTextField getTextNicknameJ() {
        return textNicknameJ;
    }

    public JDateChooser getDateChooserFechaNac() {
        return dateChooserFechaNac;
    }

    public JTextField getTextProvinciaJ() {
        return textProvinciaJ;
    }
    public JButton getBtnAltaJ() {
        return btnAltaJ;
    }
    public JButton getBtnVolverJ() {
        return btnVolverJ;
    }
    public JButton getBtnBajaJ() {
        return btnBajaJ;
    }
    public JButton getBtnBajaA() {
        return btnBajaA;
    }
    public JButton getBtnAltaA() {
        return btnAltaA;
    }
    public JButton getBtnVolverA() {
        return btnVolverA;
    }
	public JTextField getTextNombreA() {
		return textNombreA;
	}
	public void setTextNombreA(JTextField textNombreA) {
		this.textNombreA = textNombreA;
	}
	public JTextField getTextCodA() {
		return textCodA;
	}
	public void setTextCodA(JTextField textCodA) {
		this.textCodA = textCodA;
	}
	public JButton getBtnVolverT() {
		return btnVolverT;
	}
	public void setBtnVolverT(JButton btnVolverT) {
		this.btnVolverT = btnVolverT;
	}
	public JTextField getTextNombreT() {
		return textNombreT;
	}
	public void setTextNombreT(JTextField textNombreT) {
		this.textNombreT = textNombreT;
	}
	public JDateChooser getDateChooserFechaT() {
		return dateChooserFechaT;
	}
	public void setDateChooserFechaT(JTextField datechooserFechaT) {
		this.dateChooserFechaT = dateChooserFechaT;
	}
	public JTextField getTextCodigoT() {
		return textCodigoT;
	}
	public void setTextCodigoT(JTextField textCodigoT) {
		this.textCodigoT = textCodigoT;
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
	public JButton getBtnAltaT() {
		return btnAltaT;
	}
	public void setBtnAltaT(JButton btnAltaT) {
		this.btnAltaT = btnAltaT;
	}
	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}
	public void setBtnSeleccionar(JButton btnSeleccionar) {
		this.btnSeleccionar = btnSeleccionar;
	}
	public JComboBox getComboBoxJugadores() {
		return comboBoxJugadores;
	}
	public void setComboBoxJugadores(JComboBox comboBoxJugadores) {
		this.comboBoxJugadores = comboBoxJugadores;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnVolverJ)) {
			volverMenuJ();
		} else if (e.getSource().equals(btnVolverA)) {
			volverMenuA();
		} else if (e.getSource().equals(btnVolverT)) {
			volverMenuT();
		}
		
	}

	private void volverMenuT() {
		this.getTextNombreA().setEnabled(false);
        this.getTextCodigoT().setEnabled(false);
        this.getDateChooserFechaNac().setEnabled(false);
        this.getBtnVolverT().setEnabled(false);
        this.getBtnClasificarJ().setEnabled(false);
        this.getBtnAñadirJ().setEnabled(false);
        this.getBtnAltaT().setEnabled(false);
        this.setVisible(false);
		
	}

	private void volverMenuA() {
        this.getTextNombreA().setEnabled(false);
        this.getBtnAltaA().setEnabled(false);
        this.getBtnBajaA().setEnabled(false);
        this.getBtnVolverA().setEnabled(false);
        this.setVisible(false);
    }

	private void volverMenuJ() {
	    // Deshabilitar todos los componentes de la ventana actual (VentanaPestañas)
	    this.getTextNombreJ().setEnabled(false);
	    this.getTextNicknameJ().setEnabled(false);
	    this.getDateChooserFechaNac().setEnabled(false);
	    this.getTextProvinciaJ().setEnabled(false);
	    this.getBtnAltaJ().setEnabled(false);
	    this.getBtnSeleccionar().setEnabled(false);
	    this.getComboBoxJugadores().setEnabled(false);
	    this.getBtnVolverJ().setEnabled(false);
	    
	    this.setVisible(false); 
	}
}
