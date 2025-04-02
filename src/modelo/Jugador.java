package modelo;

import java.time.LocalDate;

public class Jugador {

	private int puntos = 0;
	private String nickname;
	private String nombre;
	private String provincia;
	private LocalDate fechaNac;
	

	// getters and setters
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreP() {
		return provincia;
	}
	public void setNombreP(String provincia) {
		this.provincia = provincia;
	}
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	@Override
	public String toString() {
		return "Jugador [puntos=" + puntos + ", nickname=" + nickname + ", nombre=" + nombre + ", provincia=" + provincia
				+ ", fechaNac=" + fechaNac + "]";
	}
	
}
