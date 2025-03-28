package modelo;

import java.time.LocalDate;

public class Torneo {

    private String nombreT;
    private String codigoT;
    private LocalDate fecha;
    private int plazas;
    private boolean estaActivo;

    // Constructor que inicializa los atributos
    public Torneo(String nombreT, String codigoT, LocalDate fecha, int plazas, boolean estaActivo) {
        this.nombreT = nombreT;
        this.codigoT = codigoT;
        this.fecha = fecha;
        this.plazas = plazas;
        this.estaActivo = estaActivo;
    }

    // Getters y setters
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

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
}
