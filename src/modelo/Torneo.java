package modelo;

import java.time.LocalDate;

public class Torneo {

	private String nombreT;
	private String codigoT;
	private int Plazas;
	private LocalDate fecha;
	//hahahah
	
	// getters and setters
	public String getNombreT() {
		return nombreT;
	}
	public void setNombreT(String nombreT) {
		this.nombreT = nombreT;
	}
	public String getCodigoT() {
		return codigoT;
	}
	public void setCodigoT(String codigoT) {
		this.codigoT = codigoT;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public int getPlazas() {
		return Plazas;
	}
	public void setPlazas(int plazas) {
		Plazas = plazas;
	}
	
}
