package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Arbitro; // Asegúrate de que la clase Arbitro esté en este paquete

public class ArbitroTest {
    private Arbitro arbitro;

    @BeforeEach
    public void setUp() {
        arbitro = new Arbitro(); // Se crea una nueva instancia antes de cada prueba
        arbitro.setNombre("NORA");
        arbitro.setCodigoA(1234);
    }

    @AfterEach
    public void tearDown() {
        arbitro = null; // Se limpia la referencia después de cada prueba
    }

    @Test
    void testSetNombre() {
        String nombre = "OTMAN";
        arbitro.setNombre(nombre); // Asignamos el nombre
        assertEquals(nombre, arbitro.getNombre(), "El nombre del árbitro no se asignó correctamente.");
    }
    
    @Test
    public void testGetNombre() {
        String nombre = "NORA";
        arbitro.setNombre(nombre); // Asignamos el nombre
        String resultado = arbitro.getNombre(); // Obtenemos el nombre
        assertEquals(nombre, resultado, "El método getNombre no devuelve el valor correcto.");
    }

    @Test
    public void testSetCodigoA() {
        int codigo = 12334;
        arbitro.setCodigoA(codigo); // Asignamos el código
        assertEquals(codigo, arbitro.getCodigoA(), "El código del árbitro no se asignó correctamente.");
    }
    
    @Test
    public void testGetCodigoA() {
        int codigo = 12345;
        arbitro.setCodigoA(codigo); // Asignamos el código
        int resultado = arbitro.getCodigoA(); // Obtenemos el código
        assertEquals(codigo, resultado, "El método getCodigoA no devuelve el valor correcto.");
    }
}


