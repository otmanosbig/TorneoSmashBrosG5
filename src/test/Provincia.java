package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Provincia;

class ProvinciaTest {
	
	Provincia provincia;

	@BeforeEach
	void setUp() throws Exception {
		provincia = new Provincia();
	}

	@AfterEach
	void tearDown() throws Exception {
		provincia = null;
	}

	// Tests for IdP getter and setter
	@Test
	void testGetIdP() {
		assertEquals(0, provincia.getIdP());
	}
	
	@Test
	void testSetIdP() {
		int idP = 123;
		provincia.setIdP(idP);
		assertEquals(idP, provincia.getIdP());
	}
	
	// Tests for NombreP getter and setter
	@Test
	void testGetNombreP() {
		assertNull(provincia.getNombreP());
	}
	
	@Test
	void testSetNombreP() {
		String nombreP = "Madrid";
		provincia.setNombreP(nombreP);
		assertEquals(nombreP, provincia.getNombreP());
	}
	
	// Test for toString method
	@Test
	void testToString() {
		int idP = 1;
		String nombreP = "Barcelona";
		
		provincia.setIdP(idP);
		provincia.setNombreP(nombreP);
		
		String expected = "idProvincia=" + idP + ", Localidad=" + nombreP + "]";
		assertEquals(expected, provincia.toString());
	}
}