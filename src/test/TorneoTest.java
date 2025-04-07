package test;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Torneo;

class TorneoTest {
	
	Torneo torneo;

	@BeforeEach
	void setUp() throws Exception {
		torneo = new Torneo();
	}

	@AfterEach
	void tearDown() throws Exception {
		torneo = null;
	}

	// Tests for NombreT getter and setter
	@Test
	void testGetNombreT() {
		assertNull(torneo.getNombreT());
	}
	
	@Test
	void testSetNombreT() {
		String nombreT = "Torneo Nacional";
		torneo.setNombreT(nombreT);
		assertEquals(nombreT, torneo.getNombreT());
	}
	
	// Tests for CodigoT getter and setter
	@Test
	void testGetCodigoT() {
		assertNull(torneo.getCodigoT());
	}
	
	@Test
	void testSetCodigoT() {
		String codigoT = "TN2023";
		torneo.setCodigoT(codigoT);
		assertEquals(codigoT, torneo.getCodigoT());
	}
	
	// Tests for Plazas getter and setter
	@Test
	void testGetPlazas() {
		assertEquals(0, torneo.getPlazas());
	}
	
	@Test
	void testSetPlazas() {
		int plazas = 100;
		torneo.setPlazas(plazas);
		assertEquals(plazas, torneo.getPlazas());
	}
	
	// Tests for Fecha getter and setter
	@Test
	void testGetFecha() {
		assertNull(torneo.getFecha());
	}
	
	@Test
	void testSetFecha() {
		LocalDate fecha = LocalDate.of(2023, 12, 15);
		torneo.setFecha(fecha);
		assertEquals(fecha, torneo.getFecha());
	}
}