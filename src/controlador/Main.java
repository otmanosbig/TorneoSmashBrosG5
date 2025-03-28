package controlador;

import vista.Login;
import vista.VPrincipal;


public class Main {
	private static Dao dao = new DaoImplementacionMySql();
	
	public static void main(String[] args) {
		VPrincipal ventanaPrincipal = new VPrincipal();
		ventanaPrincipal.setVisible(true);
	}

}
