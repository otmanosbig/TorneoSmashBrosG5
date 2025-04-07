package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Usuario;

class UsuarioTest {
	
	Usuario usuario;
	String testUsu = "admin";
	String testContrasena = "password123";

	@BeforeEach
	void setUp() throws Exception {
		// Utilizamos el constructor con parámetros
		usuario = new Usuario(testUsu, testContrasena);
	}

	@AfterEach
	void tearDown() throws Exception {
		usuario = null;
	}

	// Test para verificar que el constructor inicializa correctamente
	@Test
	void testConstructor() {
		assertEquals(testUsu, usuario.getUsu());
		assertEquals(testContrasena, usuario.getContrasena());
	}

	// Tests for Usu getter and setter
	@Test
	void testGetUsu() {
		assertEquals(testUsu, usuario.getUsu());
	}
	
	@Test
	void testSetUsu() {
		String newUsu = "newAdmin";
		usuario.setUsu(newUsu);
		assertEquals(newUsu, usuario.getUsu());
	}
	
	// Tests for Contrasena getter and setter
	@Test
	void testGetContrasena() {
		assertEquals(testContrasena, usuario.getContrasena());
	}
	
	@Test
	void testSetContrasena() {
		String newContrasena = "newPassword456";
		usuario.setContrasena(newContrasena);
		assertEquals(newContrasena, usuario.getContrasena());
	}
}