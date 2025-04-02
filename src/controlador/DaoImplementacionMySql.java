package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import excepciones.LoginException;
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
				if (rs != null) rs.close();
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
            if (rs != null) rs.close();
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
	        if (rs != null) rs.close();
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
	        if (rs != null) rs.close();
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
	        if (rs != null) rs.close();
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
}