package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	final String REGISTER_USER = "INSERT INTO usuario (usu, contrasena, nombre, admin) VALUES (?, ?, ?, ?)";

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
        // Consulta SQL para insertar un nuevo usuario en la base de datos
        openConnection();
        
        
        if (con == null) {
            throw new SQLException("No se pudo establecer conexión con la base de datos");
        }
        
        try (PreparedStatement stmt = con.prepareStatement(REGISTER_USER)) {
            // Asignamos los valores del usuario al PreparedStatement
            stmt.setString(1, usuario.getUsu());         // Nombre de usuario
            stmt.setString(2, usuario.getContrasena());  // Contraseña
            stmt.setString(3, usuario.getNombre());      // Nombre
            stmt.setBoolean(4, false);                   // Admin (default to false)

            // Ejecutamos la consulta para insertar el usuario
            int filasInsertadas = stmt.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Usuario registrado exitosamente.");
            } else {
                throw new SQLException("No se pudo registrar el usuario.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in registrar: " + e.getMessage());
            throw new SQLException("Error al registrar usuario: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }
	
	public boolean validarUsuario(String usuario, String contrasena) throws SQLException {
        ResultSet rs = null;
        boolean existe = false; // Variable para saber si el usuario existe

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
                existe = true; // El usuario existe en la BD
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
}
