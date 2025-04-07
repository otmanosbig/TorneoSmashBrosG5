package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Jugador;

class JugadorTest {
	
	Jugador jugador;

	@BeforeEach
	void setUp() throws Exception {
		jugador = new Jugador();
	}

	@AfterEach
	void tearDown() throws Exception {
		jugador = null;
	}

	// Tests for Puntos getter and setter
	@Test
	void testGetPuntos() {
		// Verificar que puntos se inicializa a 0
		assertEquals(0, jugador.getPuntos());
	}
	
	@Test
	void testSetPuntos() {
		int puntos = 150;
		jugador.setPuntos(puntos);
		assertEquals(puntos, jugador.getPuntos());
	}
	
	// Tests for Nickname getter and setter
	@Test
	void testGetNickname() {
		assertNull(jugador.getNickname());
	}
	
	@Test
	void testSetNickname() {
		String nickname = "ProGamer";
		jugador.setNickname(nickname);
		assertEquals(nickname, jugador.getNickname());
	}
	
	// Tests for Nombre getter and setter
	@Test
	void testGetNombre() {
		assertNull(jugador.getNombre());
	}
	
	@Test
	void testSetNombre() {
		String nombre = "Ana López";
		jugador.setNombre(nombre);
		assertEquals(nombre, jugador.getNombre());
	}
	
	// Tests for NombreP (provincia) getter and setter
	@Test
	void testGetNombreP() {
		assertNull(jugador.getNombreP());
	}
	
	@Test
	void testSetNombreP() {
		String provincia = "Valencia";
		jugador.setNombreP(provincia);
		assertEquals(provincia, jugador.getNombreP());
	}
	
	// Tests for FechaNac getter and setter
	@Test
	void testGetFechaNac() {
		assertNull(jugador.getFechaNac());
	}
	
	@Test
	void testSetFechaNac() {
		LocalDate fechaNac = LocalDate.of(1990, 3, 25);
		jugador.setFechaNac(fechaNac);
		assertEquals(fechaNac, jugador.getFechaNac());
	}
	
//	// Test for toString method
//	@Test
//	void testToString() {
//		// Configurar todos los campos
//		int puntos = 200;
//		String nickname = "GameMaster";
//		String nombre = "Carlos Ruiz";
//		String provincia = "Barcelona";
//		LocalDate fechaNac = LocalDate.of(1988, 7, 12);
//		
//		jugador.setPuntos(puntos);
//		jugador.setNickname(nickname);
//		jugador.setNombre(nombre);
//		jugador.setNombreP(provincia);
//		jugador.setFechaNac(fechaNac);
//		
//		// Crear la cadena esperada
//		String expected = "Jugador [puntos=" + puntos + 
//						  ", nickname=" + nickname + 
//						  ", nombre=" + nombre + 
//						  ", provincia=" + provincia + 
//						  ", fechaNac=" + fechaNac + "]";
//		
//		// Verificar que toString() devuelve la cadena esperada
//		assertEquals(expected, jugador.toString());
//	}
}