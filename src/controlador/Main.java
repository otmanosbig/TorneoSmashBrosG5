package controlador;

import vista.Login;

public class Main {
	private static Dao dao = new DaoImplementacionMySql();
	
	public static void main(String[] args) {
		Login login = new Login();
		login.setVisible(true);
	}

}