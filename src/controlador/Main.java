package controlador;

import java.sql.SQLException;
import java.util.List;

import excepciones.GlobalException;
import modelo.Arbitro;
import modelo.Juega;
import modelo.Jugador;
import modelo.Torneo;
import vista.Login;

public class Main {
	private static Dao dao = new DaoImplementacionMySql();
	
	public static void main(String[] args) {
		Login login = new Login();
		login.setVisible(true);
	}
	public static List<String> obtenerNicknames() throws GlobalException, SQLException {
		return dao.obtenerNicknames();
	}
	public static List<String> obtenerJugadoresSinTorneo(String codigoT) throws GlobalException {
		return dao.obtenerJugadoresSinTorneo(codigoT);
	}

	public static List<Torneo> obtenerTorneo() throws GlobalException{
		return dao.obtenerTorneo();
	}
	public static List<Arbitro> obtenerArbitros() throws GlobalException {
		return dao.obtenerArbitros();
	}

	public static void altaTorneo(Torneo tor) throws GlobalException {
		dao.altaTorneo(tor);
	}

	public static void anadirJugadorATorneo(Juega juega) throws GlobalException {
		dao.anadirJugadorATorneo(juega);
	}
	
	public static List<Juega> obtenerJugadoresConTorneo(String codigoT) throws GlobalException {
		return dao.obtenerJugadoresConTorneo(codigoT);
	}
	
	public static void clasificarJugador(Juega juega) throws GlobalException {
	    dao.clasificarJugador(juega); 
	}

	public static List<Jugador> obtenerRanking() throws GlobalException {
		return dao.obtenerRanking();
	}

	public static List<Jugador> cargarJugadores() throws GlobalException{
		return dao.cargarJugadores();
	}

}