package modelo;

public class Usuario {

	private String nombre;
	private String usu;
	private String contrasena;
	
	// getters and setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsu() {
		return usu;
	}
	public void setUsu(String usu) {
		this.usu = usu;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContraseña(String contrasena) {
		this.contrasena = contrasena;
	}
	
	//constructor
	public Usuario(String nombre, String usu, String contrasena) {
		super();
		this.nombre = nombre;
		this.usu = usu;
		this.contrasena = contrasena;
	}
	
	
}
