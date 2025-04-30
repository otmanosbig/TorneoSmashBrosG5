package modelo;

public class Juega {
	private String Nickname;
    private String CodigoT;
    private String Personaje;
    private int Posicion;
    private int puntos_obtenidos;
    final int PUNTOS_MINIMOS=25;
    
    
    public Juega() {
    }
    
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public String getCodigoT() {
		return CodigoT;
	}
	public void setCodigoT(String codigoT) {
		CodigoT = codigoT;
	}
	public String getPersonaje() {
		return Personaje;
	}
	public void setPersonaje(String personaje) {
		Personaje = personaje;
	}
	public int getPosicion() {
		return Posicion;
	}
	public void setPosicion(int posicion) {
		if(posicion==1) {
			setPuntos_obtenidos(PUNTOS_MINIMOS*4);
		}else if(posicion==2) {
			setPuntos_obtenidos(PUNTOS_MINIMOS*3);
		}else if(posicion==3) {
			setPuntos_obtenidos(PUNTOS_MINIMOS*2);
		}else if(posicion==4) {
			setPuntos_obtenidos(PUNTOS_MINIMOS);
		}
		Posicion = posicion;
	}
	public int getPuntos_obtenidos() {
		return puntos_obtenidos;
	}
	public void setPuntos_obtenidos(int puntos_obtenidos) {
		this.puntos_obtenidos = puntos_obtenidos;
	}
	@Override
	public String toString() {
		return "Juega [Nickname=" + Nickname + ", CodigoT=" + CodigoT + ", Personaje=" + Personaje + ", Posicion="
				+ Posicion + ", puntos_obtenidos=" + puntos_obtenidos + "]";
	}

}