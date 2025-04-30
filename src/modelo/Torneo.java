package modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Torneo {

	private String nombreT;
    private String codigoT;
    private LocalDate fecha;
    private int plazas;
    private String codigoA;
    private boolean estaActivo;
    
    //constructor
	public Torneo(String nombreT, String codigoT, LocalDate fechaString, int plazas, String codigoA, boolean estaActivo) {
		super();
		this.nombreT = nombreT;
		this.codigoT = codigoT;
		this.fecha = fechaString;
		this.plazas = plazas;
		this.codigoA = codigoA;
		this.estaActivo = estaActivo;
	}

	public Torneo() {
	}
		
	//getters y setters
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
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public String getcodigoA() {
		return codigoA;
	}

	public void setCodigoA(String codigoA) {
		this.codigoA = codigoA;
	}

	public boolean isEstaActivo() {
		return estaActivo;
	}

	public void setEstaActivo(boolean estaActivo) {
		this.estaActivo = estaActivo;
	}

}
