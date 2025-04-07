package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Juega;

class JuegaTest {
	
	
	Juega juega;

	@BeforeEach
	void setUp() throws Exception {
		juega = new Juega();
	}

	@AfterEach
	void tearDown() throws Exception {
		juega = null;
	}

	// Tests for Nickname getter and setter
	@Test
	void testGetNickname() {
		assertNull(juega.getNickname());
	}
	
	@Test
	void testSetNickname() {
		String nickname = "Player1";
		juega.setNickname(nickname);
		assertEquals(nickname, juega.getNickname());
	}
	
	// Tests for CodigoT getter and setter
	@Test
	void testGetCodigoT() {
		assertNull(juega.getCodigoT());
	}
	
	@Test
	void testSetCodigoT() {
		String codigoT = "ABC123";
		juega.setCodigoT(codigoT);
		assertEquals(codigoT, juega.getCodigoT());
	}
	
	// Tests for Personaje getter and setter
	@Test
	void testGetPersonaje() {
		assertNull(juega.getPersonaje());
	}
	
	@Test
	void testSetPersonaje() {
		String personaje = "Warrior";
		juega.setPersonaje(personaje);
		assertEquals(personaje, juega.getPersonaje());
	}
	
	// Tests for Puntos getter and setter
	@Test
	void testGetPuntos() {
		assertEquals(0, juega.getPuntos());
	}
	
	@Test
	void testSetPuntos() {
		int puntos = 100;
		juega.setPuntos(puntos);
		assertEquals(puntos, juega.getPuntos());
	}
}
