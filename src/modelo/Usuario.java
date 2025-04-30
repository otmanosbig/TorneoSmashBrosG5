package modelo;

public class Usuario {
	private String usu;
	private String contrasena;
	
	// getters and setters
	public String getUsu() {
		return usu;
	}
	public void setUsu(String usu) {
		this.usu = usu;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	//constructor
	public Usuario( String usu, String contrasena) {
		super();
		this.usu = usu;
		this.contrasena = contrasena;
	}
	
}
