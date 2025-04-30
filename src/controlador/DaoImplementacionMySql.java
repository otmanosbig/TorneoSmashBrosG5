package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import excepciones.GlobalException;
import excepciones.LoginException;
import modelo.Arbitro;
import modelo.Juega;
import modelo.Jugador;
import modelo.Torneo;
import modelo.Usuario;

public class DaoImplementacionMySql implements Dao {

	private Connection con;
	private PreparedStatement stmt;
	private ResourceBundle configFile;
	private String urlBD;
	private String userBD;
	private String passwordBD;

	final String LOGGIN = "SELECT * FROM usuario WHERE usu = ? AND contrasena = ?";
	final String REGISTER_USER = "INSERT INTO usuario (usu, contrasena) VALUES (?, ?)";
	final String GET_NICKNAMES = "SELECT nickname FROM jugador";
	final String GET_PROVINCIAS = "SELECT nombreP FROM provincia";
	final String GET_IDP = "SELECT idP FROM provincia WHERE nombreP = ?";
	final String INSERT_JUGADOR = "INSERT INTO jugador (nombre, nickname, fechaNac, idP) VALUES (?, ?, ?, ?)";
	final String DELETE_JUGADOR = "DELETE FROM jugador WHERE nickname = ?";
	final String CREATE_USER = "INSERT INTO usuario (usu, contrasena) VALUES (?, ?)";
	final String OBTENER_JUG = "SELECT * FROM jugador WHERE nickname = ?";
	final String GENERAR_COD_ARB = "SELECT codigoA FROM arbitro";
	final String ALTA_ARB = "INSERT INTO arbitro (codigoA, nombre) VALUES (?, ?)";
	final String SELEC_COD_ARB = "SELECT codigoA FROM arbitro";
	final String OBT_NOM_PROV_ID = "SELECT nombreP FROM provincia WHERE idP = ?";
	final String UPDATE_JUGADOR = "UPDATE jugador SET nombre = ?, nickname = ?, fechaNac = ?, idP = ? WHERE nickname = ?";
	final String ELIMINAR_ARBITRO = "DELETE FROM arbitro WHERE codigoA = ?";
	final String NOMBRE_TORNEO = "SELECT nombre from torneo";
	final String ALTA_TORNEO = "INSERT INTO torneo (codigoT, nombre, fecha, plazas, codigoA, estaActivo) VALUES (?,?,?,?,?,?)";
	final String OBTENER_ARBITRO = "SELECT * from arbitro";
	final String COMPROBAR_PLAZAS = "SELECT plazas FROM torneo WHERE codigoT = ?";
	final String INSERTAR_JUGADOR = "INSERT INTO juega (nickname, codigoT, puntos) VALUES (?, ?, ?)";
	final String JUGADORES_INSCRITOS = "SELECT j.nickname FROM juega ju JOIN jugador j ON ju.nickname = j.nickname WHERE ju.codigoT = ?";
	final String EXISTE = "SELECT * FROM juega WHERE nickname = ? AND codigoT = ?";
	final String GET_TORNEO = "SELECT * from torneo";
	final String OBTENER_JUGADORES_SIN_TORNEO = "SELECT * FROM jugador j WHERE NOT EXISTS ( SELECT 1 FROM juega ju WHERE ju.nickname = j.nickname AND ju.codigoT = ?)";
	final String ANADIR_JUGADOR_A_TORNEO = "INSERT INTO juega (nickname, codigoT, personaje, puntos) VALUES (?, ?, ?, ?)";
	final String OBTENER_JUGADORES_DE_UN_TORNEO = "SELECT * FROM JUEGA WHERE CODIGOT = ?";
	final String CLASIFICAR_JUGADOR = "UPDATE Juega SET posicion = ?, puntos = ? WHERE codigoT = ? AND nickname = ?";
	final String DATOS_TORNEO = "SELECT * FROM torneo WHERE codigoT = ?";
	final String RANKING_JUGADOR = "SELECT j.nickname, j.nombre, SUM(jg.puntos) AS total_puntos " +
            "FROM jugador j JOIN juega jg ON j.nickname = jg.nickname " +
            "GROUP BY j.nickname, j.nombre " + 
            "ORDER BY total_puntos DESC";
	final String CARGAR_JUGADOR = "SELECT nickname, nombre, fechaNac FROM jugador";

	public DaoImplementacionMySql() {
		this.configFile = ResourceBundle.getBundle("modelo.configClase");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");

		System.out.println("URL from config: " + urlBD);
		System.out.println("User from config: " + userBD);
	}

	private void openConnection() {
		try {

			String modifiedUrl = urlBD;
			if (!modifiedUrl.contains("allowPublicKeyRetrieval")) {
				modifiedUrl += (modifiedUrl.contains("?") ? "&" : "?") + "allowPublicKeyRetrieval=true";
			}

			System.out.println("Connecting with URL: " + modifiedUrl);

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("MySQL JDBC Driver not found!");
				e.printStackTrace();
			}

			con = DriverManager.getConnection(modifiedUrl, this.userBD, this.passwordBD);

			if (con != null) {
				System.out.println("Database connection successful!");
			}
		} catch (SQLException e) {
			System.out.println("Connection error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void closeConnection() throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (con != null) {
			con.close();
			System.out.println("Connection closed.");
		}
	}

	@Override
	public void login(Usuario usuario) throws LoginException {
		ResultSet rs = null;

		openConnection();

		try {
			if (con == null) {
				throw new LoginException("No se pudo establecer conexión con la base de datos");
			}

			stmt = con.prepareStatement(LOGGIN);

			stmt.setString(1, usuario.getUsu());
			stmt.setString(2, usuario.getContrasena());

			rs = stmt.executeQuery();

			if (!rs.next()) {
				throw new LoginException("Usuario o contraseña incorrecta");
			}
		} catch (SQLException e) {
			System.out.println("SQL Error in login: " + e.getMessage());
			throw new LoginException("Excepcion de SQL al hacer login: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void registrar(Usuario usuario) throws SQLException {
		openConnection();

		if (con == null) {
			throw new SQLException("No se pudo establecer conexión con la base de datos");
		}

		System.out.println("Ejecutando consulta SQL: " + CREATE_USER);
		try (PreparedStatement stmt = con.prepareStatement(CREATE_USER)) {
			stmt.setString(1, usuario.getUsu());
			stmt.setString(2, usuario.getContrasena());

			int filasInsertadas = stmt.executeUpdate();

			if (filasInsertadas > 0) {
				System.out.println("Usuario insertado correctamente.");
			} else {
				throw new SQLException("No se pudo registrar el usuario.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error al registrar usuario: " + e.getMessage());
		} finally {
			closeConnection();
		}
	}
	
	public void insertarJugador1(String nombre, String nickname, String fechaNacimiento, int idP) throws SQLException {
		String sql = "INSERT INTO jugador (nombre, nickname, fechaNac, idP) VALUES (?, ?, ?, ?)";
		openConnection();

		if (con == null) {
			throw new SQLException("No se pudo establecer conexión con la base de datos");
		}

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, nombre);
			stmt.setString(2, nickname);
			stmt.setString(3, fechaNacimiento);
			stmt.setInt(4, idP); // Cambia aquí para insertar el idP

			int filasInsertadas = stmt.executeUpdate();

			if (filasInsertadas > 0) {
				System.out.println("Jugador insertado correctamente.");
			} else {
				throw new SQLException("No se pudo insertar el jugador.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error al insertar jugador: " + e.getMessage());
		} finally {
			closeConnection();
		}
	}
	
	@Override
	public List<String> altaTorneo(Torneo tor) throws GlobalException {
		List<String> nombresTorneos = new ArrayList<>();

		openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(ALTA_TORNEO);
			stmt.setString(1, tor.getCodigoT());
			stmt.setString(2, tor.getNombreT());
			stmt.setDate(3, Date.valueOf(tor.getFecha()));
			stmt.setInt(4, tor.getPlazas());
			stmt.setString(5, tor.getcodigoA());
			stmt.setBoolean(6, tor.isEstaActivo());

			int filasInsertadas = stmt.executeUpdate();
			if (filasInsertadas == 0) {
				throw new SQLException("No se pudo registrar el torneo.");
			}

			try (PreparedStatement stmtQuery = con.prepareStatement(NOMBRE_TORNEO);
					ResultSet rs = stmtQuery.executeQuery()) {
				while (rs.next()) {
					nombresTorneos.add(rs.getString("nombre"));
				}
			}
		} catch (SQLException e) {
			throw new GlobalException("Error al registrar el torneo: " + e.getMessage());
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nombresTorneos;
	}

	
	@Override
	public List<Arbitro> obtenerArbitros() throws GlobalException {
		openConnection();
		ResultSet rs = null;
		List<Arbitro> listArbitro = new ArrayList<>();

		try {
			PreparedStatement stmt = con.prepareStatement(OBTENER_ARBITRO);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Arbitro arb = new Arbitro();
				arb.setCodigoA(rs.getInt(1));
				arb.setNombre(rs.getString(2));
				listArbitro.add(arb);
				System.out.println("Arbitro " + arb);
			}
			// gestionar que la tabla de arbitros este vacia
//	        if (!rs.next()){
//	        	throw new GlobalException("No hay arbitros registrados!!!");
//	        }
		} catch (SQLException e) {
			System.out.println("Error en el sql.");
		} finally {
			try {
				if (rs != null)
					rs.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listArbitro;
	}
	
	@Override
	public List<Torneo> obtenerTorneo() throws GlobalException {
		List<Torneo> listTor = new ArrayList<>();

		ResultSet rs = null;
		openConnection();

		try {
			stmt = con.prepareStatement(GET_TORNEO);
			rs = stmt.executeQuery();

			// if(!rs.next()) {
			// throw new GlobalException("No hay torneo registrado.");
			// }
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String codigo = rs.getString("codigoT");
				LocalDate fecha = rs.getDate("fecha").toLocalDate();
				int plazas = rs.getInt("plazas");
				String codigoA = rs.getString("codigoA");
				boolean estaActivo = rs.getBoolean("estaActivo");

				Torneo tor = new Torneo(nombre, codigo, fecha, plazas, codigoA, estaActivo);
				listTor.add(tor);

			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta SQL: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listTor;
	}
	
	@Override
	public List<String> obtenerJugadoresSinTorneo(String codigoT) throws GlobalException {
		List<String> jugadores = new ArrayList<>();
		ResultSet rs = null;

		openConnection();

		try {
			stmt = con.prepareStatement(OBTENER_JUGADORES_SIN_TORNEO);
			stmt.setString(1, codigoT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String buscado = rs.getString("nickname");
				jugadores.add(buscado);
			}
		} catch (SQLException e) {
			throw new GlobalException("Error al obtener jugadores sin torneo: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				closeConnection();
			} catch (SQLException e) {
				throw new GlobalException("Error: " + e.getMessage());
			}
		}

		return jugadores;
	}
	
	@Override
	public void anadirJugadorATorneo(Juega ju) throws GlobalException {

		openConnection();

		try {
			PreparedStatement stmt = con.prepareStatement(ANADIR_JUGADOR_A_TORNEO);
			stmt.setString(1, ju.getNickname());
			stmt.setString(2, ju.getCodigoT());
			stmt.setString(3, ju.getPersonaje());
			stmt.setInt(4, ju.getPuntos_obtenidos());

			int filasInsertadas = stmt.executeUpdate();
			if (filasInsertadas == 0) {
				throw new SQLException("No se pudo insertar el jugador en el torneo.");
			}

		} catch (SQLException e) {
			throw new GlobalException("Error al añadir jugador al torneo: " + e.getMessage());
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public List<Juega> obtenerJugadoresConTorneo(String codigoT) throws GlobalException {
		List<Juega> listJu = new ArrayList<>();
		ResultSet rs = null;

		openConnection();

		try {
			stmt = con.prepareStatement(OBTENER_JUGADORES_DE_UN_TORNEO);
			stmt.setString(1, codigoT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Juega ju = new Juega();
				ju.setCodigoT(rs.getString(2));
				ju.setNickname(rs.getString(1));
				ju.setPersonaje(rs.getString(3));

				listJu.add(ju);
			}
		} catch (SQLException e) {
			throw new GlobalException("Error al obtener jugadores: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				closeConnection();
			} catch (SQLException e) {
				throw new GlobalException("Error: " + e.getMessage());
			}
		}

		return listJu;
	}
	
	@Override
	public void clasificarJugador(Juega juega) throws GlobalException {
		ResultSet rs = null;
		openConnection();

		try {

			stmt = con.prepareStatement(CLASIFICAR_JUGADOR);

			stmt.setInt(1, juega.getPosicion());
			System.out.println("PUNTOS OBTENIDOS");
			stmt.setInt(2, juega.getPuntos_obtenidos());
			stmt.setString(3, juega.getCodigoT());
			stmt.setString(4, juega.getNickname());
			int res = stmt.executeUpdate();
			if (res <= 0) throw new GlobalException("No se pudo clasificar el jugador.");
		} catch (SQLException e) {
			System.out.println("SQL Exception");
		}
	}

	// listar en tablas
	@Override
	public List<String> cargarNicknames() throws GlobalException {
		List<String> nicknames = new ArrayList<>();
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(GET_NICKNAMES);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String nickname = rs.getString("nickname");
				nicknames.add(nickname);
			}

		} catch (SQLException e) {
			throw new GlobalException("Error al cargar los nicknames de los jugadores: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				closeConnection();
			} catch (SQLException e) {
				throw new GlobalException("Error al cerrar la conexión: " + e.getMessage());
			}
		}

		return nicknames;
	}
	
	@Override
	public List<Jugador> obtenerRanking() throws GlobalException {
	    List<Jugador> ranking = new ArrayList<>();
	    ResultSet rs = null;

	    try {
	        openConnection();
	        stmt = con.prepareStatement(RANKING_JUGADOR);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Jugador j = new Jugador();
	            j.setNickname(rs.getString("nickname"));
	            j.setNombre(rs.getString("nombre"));
	            j.setPuntos(rs.getInt("total_puntos"));
	            ranking.add(j);
	        }
	    } catch (SQLException e) {
	        System.err.println("SQL Error in obtenerRanking: " + e.getMessage());
	        throw new GlobalException("Error al obtener el ranking");
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            closeConnection();
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }
	    }
	    return ranking;
	}
	
	@Override
	public List<Jugador> cargarJugadores() throws GlobalException {
		List<Jugador> jugadores = new ArrayList<>();
		ResultSet rs = null;

		openConnection();

		try {
			stmt = con.prepareStatement(CARGAR_JUGADOR);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Jugador jugador = new Jugador();

				jugador.setNickname(rs.getString("nickname"));
				jugador.setNombre(rs.getString("nombre"));
				jugador.setFechaNac(rs.getDate("fechaNac").toLocalDate());
				System.out.println(jugador.getNombre());
				jugadores.add(jugador);
			}

		} catch (SQLException e) {
			throw new GlobalException("Error al cargar jugadores: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				closeConnection();
			} catch (SQLException e) {
				throw new GlobalException("Error al cerrar conexión: " + e.getMessage());
			}
		}
		return jugadores;
	}

	public boolean validarUsuario(String usuario, String contrasena) throws SQLException {
		ResultSet rs = null;
		boolean existe = false;

		openConnection();

		if (con == null) {
			throw new SQLException("No se pudo establecer conexión con la base de datos");
		}

		try {
			stmt = con.prepareStatement(LOGGIN);
			stmt.setString(1, usuario);
			stmt.setString(2, contrasena);
			rs = stmt.executeQuery();

			if (rs.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			System.out.println("SQL Error in validarUsuario: " + e.getMessage());
			throw new SQLException("Error al validar usuario: " + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			closeConnection();
		}
		return existe;
	}

	public List<String> obtenerNicknames() throws SQLException {
		List<String> nicknames = new ArrayList<>();
		ResultSet rs = null;

		openConnection();

		if (con == null) {
			throw new SQLException("No se pudo establecer conexión con la base de datos");
		}

		try {
			System.out.println("Ejecutando consulta SQL: " + GET_NICKNAMES);
			stmt = con.prepareStatement(GET_NICKNAMES);
			rs = stmt.executeQuery();

			while (rs.next()) {
				nicknames.add(rs.getString("nickname"));
			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta SQL: " + e.getMessage());
			throw new SQLException("Error al obtener los nicknames: " + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			closeConnection();
		}
		return nicknames;
	}
	
	public List<String> obtenerProvincias() throws SQLException {
		List<String> provincias = new ArrayList<>();
		ResultSet rs = null;
		
		openConnection();
		
		if (con == null) {
			throw new SQLException("No se pudo establecer conexión con la base de datos");
		}
		
		try {
			System.out.println("Ejecutando consulta SQL: " + GET_PROVINCIAS);
			stmt = con.prepareStatement(GET_PROVINCIAS);
			rs = stmt.executeQuery();

			while (rs.next()) {
				provincias.add(rs.getString("nombreP"));
			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta SQL: " + e.getMessage());
			throw new SQLException("Error al obtener las provincias: " + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			closeConnection();
		}
		return provincias;
	}
	
	public void insertarJugador(String nombre, String nickname, String fechaNacimiento, int idP) throws SQLException {
		openConnection();
		
		if (con == null) {
			throw new SQLException("No se pudo establecer conexión con la base de datos");
		}
		
		System.out.println("Ejecutando consulta SQL: " + INSERT_JUGADOR);
		try (PreparedStatement stmt = con.prepareStatement(INSERT_JUGADOR)) {
			stmt.setString(1, nombre);
			stmt.setString(2, nickname);
			stmt.setString(3, fechaNacimiento);
			stmt.setInt(4, idP);
			
			int filasInsertadas = stmt.executeUpdate();
			
			if (filasInsertadas > 0) {
				System.out.println("Jugador insertado correctamente.");
			} else {
				throw new SQLException("No se pudo insertar el jugador.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error al insertar jugador: " + e.getMessage());
		} finally {
			closeConnection();
		}
	}
	
	public int obtenerIdProvincia(String nombreProvincia) throws SQLException {
		int idP = -1;
		ResultSet rs = null;
		
		openConnection();
		
		if (con == null) {
			throw new SQLException("No se pudo establecer conexión con la base de datos");
		}
		
		try {
			System.out.println("Ejecutando consulta SQL: " + GET_IDP);
			stmt = con.prepareStatement(GET_IDP);
			stmt.setString(1, nombreProvincia);
			rs = stmt.executeQuery();

			if (rs.next()) {
				idP = rs.getInt("idP");
			} else {
				throw new SQLException("Provincia no encontrada.");
			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta SQL: " + e.getMessage());
			throw new SQLException("Error al obtener el idP de la provincia: " + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			closeConnection();
		}
		return idP;
	}
	
	public void eliminarJugador(String nickname) throws SQLException {
		openConnection();
		
		if (con == null) {
			throw new SQLException("No se pudo establecer conexión con la base de datos");
		}
		
		System.out.println("Ejecutando consulta SQL: " + DELETE_JUGADOR);
		try (PreparedStatement stmt = con.prepareStatement(DELETE_JUGADOR)) {
			stmt.setString(1, nickname);
			int filasEliminadas = stmt.executeUpdate();
			
			if (filasEliminadas > 0) {
				System.out.println("Jugador eliminado correctamente.");
			} else {
				throw new SQLException("No se pudo eliminar el jugador. Puede que no exista.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error al eliminar jugador: " + e.getMessage());
		} finally {
			closeConnection();
		}
	}
	
	public Object[] obtenerJugadorPorNickname(String nickname) throws SQLException {
	    Object[] datosJugador = new Object[4];
	    ResultSet rs = null;
	    
	    openConnection();
	    
	    if (con == null) {
	        throw new SQLException("No se pudo establecer conexión con la base de datos");
	    }
	    
	    System.out.println("Ejecutando consulta SQL para obtener jugador: " + nickname);
	    try {
	        stmt = con.prepareStatement(OBTENER_JUG);
	        stmt.setString(1, nickname);
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            datosJugador[0] = rs.getString("nombre");
	            datosJugador[1] = rs.getString("nickname");
	            datosJugador[2] = rs.getDate("fechaNac").toLocalDate();
	            datosJugador[3] = rs.getInt("idP");
	        } else {
	            throw new SQLException("No se encontró el jugador con el nickname: " + nickname);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en la consulta SQL: " + e.getMessage());
	        throw new SQLException("Error al obtener los datos del jugador: " + e.getMessage());
	    } finally {
	        if (rs != null) rs.close();
	        closeConnection();
	    }
	    return datosJugador;
	}
	
	public String obtenerNombreProvinciaPorId(int idProvincia) throws SQLException {
	    String nombreProvincia = null;
	    ResultSet rs = null;

	    openConnection();

	    try {
	        System.out.println("Ejecutando consulta SQL: " + OBT_NOM_PROV_ID);
	        PreparedStatement stmt = con.prepareStatement(OBT_NOM_PROV_ID);
	        stmt.setInt(1, idProvincia);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            nombreProvincia = rs.getString("nombreP");
	        } else {
	            throw new SQLException("No se encontró la provincia con ID: " + idProvincia);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en la consulta SQL: " + e.getMessage());
	        throw new SQLException("Error al obtener el nombre de la provincia: " + e.getMessage());
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        closeConnection();
	    }
	    return nombreProvincia;
	}
	
	public int generarCodigoArbitro() throws SQLException {
	    List<Integer> codigosExistentes = new ArrayList<>();
	    int codigoGenerado = 1;

	    openConnection();

	    try {
	    	System.out.println("Ejecutando consulta SQL: " + GENERAR_COD_ARB);
	        PreparedStatement stmt = con.prepareStatement(GENERAR_COD_ARB);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            codigosExistentes.add(rs.getInt("codigoA"));
	        }
	        while (codigosExistentes.contains(codigoGenerado)) {
	            codigoGenerado++;
	        }
	    } finally {
	        closeConnection();
	    }

	    return codigoGenerado;
	}
	
	public void darDeAltaArbitro(String nombre) throws SQLException {
	    if (nombre == null || nombre.isEmpty()) {
	        throw new SQLException("El nombre del árbitro es obligatorio.");
	    }

	    int codigoA = generarCodigoArbitro();

	    openConnection();

	    try {
	    	System.out.println("Ejecutando consulta SQL: " + ALTA_ARB);
	        PreparedStatement stmt = con.prepareStatement(ALTA_ARB);
	        stmt.setInt(1, codigoA);
	        stmt.setString(2, nombre);

	        int filasInsertadas = stmt.executeUpdate();
	        if (filasInsertadas > 0) {
	            System.out.println("Árbitro dado de alta correctamente con código: " + codigoA);
	        } else {
	            throw new SQLException("No se pudo dar de alta al árbitro.");
	        }
	    } finally {
	        closeConnection();
	    }
	}
	
	public List<Integer> obtenerCodigosArbitros() throws SQLException {
	    List<Integer> codigosArbitros = new ArrayList<>();
	    ResultSet rs = null;

	    openConnection();

	    try {
	    	System.out.println("Ejecutando consulta SQL: " + SELEC_COD_ARB);
	        PreparedStatement stmt = con.prepareStatement(SELEC_COD_ARB);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            codigosArbitros.add(rs.getInt("codigoA"));
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener los códigos de árbitros: " + e.getMessage());
	        throw e;
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        closeConnection();
	    }

	    return codigosArbitros;
	}
	
	public void eliminarArbitro(Integer codigoArbitro) throws SQLException {
	    openConnection();

	    try {
	    	System.out.println("Ejecutando consulta SQL: " + ELIMINAR_ARBITRO);
	        PreparedStatement stmt = con.prepareStatement(ELIMINAR_ARBITRO);
	        stmt.setInt(1, codigoArbitro);

	        int filasEliminadas = stmt.executeUpdate();
	        if (filasEliminadas > 0) {
	            System.out.println("Árbitro eliminado correctamente.");
	        } else {
	            throw new SQLException("No se pudo eliminar el árbitro.");
	        }
	    } finally {
	        closeConnection();
	    }
	}
	
	public void modificarJugador(String nicknameOriginal, String nuevoNickname, String nombre, String fechaNacimiento, int idP) throws SQLException {
		openConnection();

	    if (con == null) {
	        throw new SQLException("No se pudo establecer conexión con la base de datos");
	    }
	    
	    System.out.println("Ejecutando consulta SQL: " + UPDATE_JUGADOR);
	    try (PreparedStatement stmt = con.prepareStatement(UPDATE_JUGADOR)) {
	        stmt.setString(1, nombre);
	        stmt.setString(2, nuevoNickname); // Usar el nuevo nickname
	        stmt.setString(3, fechaNacimiento);
	        stmt.setInt(4, idP);
	        stmt.setString(5, nicknameOriginal); // Usar el nickname original para la condición

	        int filasActualizadas = stmt.executeUpdate();

	        if (filasActualizadas > 0) {
	            System.out.println("Jugador modificado correctamente.");
	        } else {
	            throw new SQLException("No se pudo modificar el jugador. Puede que no exista.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error al modificar jugador: " + e.getMessage());
	    } finally {
	        closeConnection();
	    }
	}
}