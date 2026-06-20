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
	public void testLocalidadConNombreNulo() {
		new Localidad(null, "Buenos Aires", -34.54, -58.71, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testLocalidadConProvinciaNula() {
		new Localidad("San Miguel", null, -34.54, -58.71, 0);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testLocalidadConNombreVacio() {
		new Localidad("", "Buenos Aires", -34.54, -58.71, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testLocalidadConProvinciaVacia() {
		new Localidad("San Miguel", "", -34.54, -58.71, 0);
	}
	
	@Test 
	public void testLocalidadConCoordenadasEnElLimiteExacto() {
		Localidad limitePositivo = new Localidad("Polo Norte", "Internacional", 90.0, 180.0, 0);
		Localidad limiteNegativo = new Localidad("Polo Sur", "Internacional", -90.0, -180.0, 1);
		
		assertEquals(90.0, limitePositivo.getLatitud(), 0.001);
		assertEquals(-180.0, limiteNegativo.getLongitud(), 0.001);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testLocalidadConLatitudIncorrecta() {
		new Localidad("San Miguel", "Buenos Aires", -95.15, -58.71, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testLocalidadConLongitudIncorrecta() {
		new Localidad("San Miguel", "Buenos Aires", -34.54, -200.00, 0);
	}
}