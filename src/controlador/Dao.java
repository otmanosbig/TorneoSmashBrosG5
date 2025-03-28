package controlador;

import excepciones.LoginException;
import modelo.Usuario;

public interface Dao {
	public void login(Usuario usuario) throws LoginException;
}
