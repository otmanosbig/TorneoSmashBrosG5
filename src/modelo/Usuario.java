package modelo;

public class Usuario {
    private String usu;
    private String contrasena;

    // Constructor
    public Usuario(String usu, String contrasena) {
        this.usu = usu;
        this.contrasena = contrasena;
    }

    // Getters y Setters
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
}
