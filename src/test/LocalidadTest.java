package test;

import modelo.Localidad;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocalidadTest {
	
	
	@Test
	public void testLocalidadSeCreaCorrectamente() {
		Localidad localidad = new Localidad("San Miguel", "Buenos Aires", -34.54, -58.71, 0);
		
		assertEquals("San Miguel", localidad.getNombre());
		assertEquals("Buenos Aires", localidad.getProvincia());
		
		assertEquals(-34.54, localidad.getLatitud(), 0.001);
		assertEquals(-58.71, localidad.getLongitud(), 0.001);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testLocalidadConNombreVacio() {
		Localidad localidad = new Localidad("", "Buenos Aires", -34.54, -58.71, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testLocalidadConProvinciaVacia() {
		Localidad localidad = new Localidad("San Miguel", "", -34.54, -58.71, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testLocalidadConLatitudIncorrecta() {
		Localidad localidad = new Localidad("San Miguel", "Buenos Aires", -95.15, -58.71, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testLocalidadConLongitudIncorrecta() {
		Localidad localidad = new Localidad("San Miguel", "Buenos Aires", -34.54, -200.00, 0);
	}
}
