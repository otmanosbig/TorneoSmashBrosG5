package controlador;

import java.sql.SQLException;
import java.util.List;

import excepciones.GlobalException;
import excepciones.LoginException;
import modelo.Arbitro;
import modelo.Juega;
import modelo.Jugador;
import modelo.Torneo;
import modelo.Usuario;

public interface Dao {
	public void login(Usuario usuario) throws GlobalException, LoginException;
	public List<String> obtenerNicknames() throws GlobalException, SQLException;
	public List<Torneo> obtenerTorneo() throws GlobalException;
	public List<String> altaTorneo(Torneo tor) throws GlobalException;
	public List<Arbitro> obtenerArbitros()throws GlobalException;
	public List<String> obtenerJugadoresSinTorneo(String codigoT) throws GlobalException;
	public void anadirJugadorATorneo(Juega ju) throws GlobalException;
	public List<Juega> obtenerJugadoresConTorneo(String codigoT) throws GlobalException;
	public void clasificarJugador(Juega juega) throws GlobalException;
	public List<Jugador> cargarJugadores() throws GlobalException;
	public List<String> cargarNicknames() throws GlobalException;
	public List<Jugador> obtenerRanking() throws GlobalException;
}
