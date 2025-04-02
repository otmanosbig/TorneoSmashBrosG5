package modelo;

public class Provincia {
	private int idP;
	private String nombreP;

	// getters and setters
	public String getNombreP() {
		return nombreP;
	}

	public void setNombreP(String nombreP) {
		this.nombreP = nombreP;
	}
	
	public int getIdP() {
		return idP;
	}

	public void setIdP(int idP) {
		this.idP = idP;
	}

	@Override
	public String toString() {
		return "idProvincia=" + idP + ", Localidad=" + nombreP + "]";
	}
	
}
