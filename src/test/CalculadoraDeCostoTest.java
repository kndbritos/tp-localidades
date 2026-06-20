package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import modelo.CalculadoraDeCosto;
import modelo.Localidad;

public class CalculadoraDeCostoTest {
	
	private CalculadoraDeCosto calculadora;
	private Localidad buenosAires;
	private Localidad rosario;
	private Localidad cordoba;
	private Localidad laPlata;
	private Localidad marDelPlata;
	private double costoFijo;
	private double porcentajeRecargo;
	private double margenDeError;

	@Before
	public void setUp() {
		calculadora = new CalculadoraDeCosto();
		buenosAires = new Localidad( "Buenos Aires", "Buenos Aires", -34.6037, -58.3816, 0 );
		rosario = new Localidad( "Rosario", "Santa Fe", -32.9442, -60.6505, 1 );
		cordoba = new Localidad( "Cordoba", "Cordoba", -31.4201, -64.1888, 2 );
		laPlata = new Localidad("La Plata", "Buenos Aires", -34.9214, -57.9544, 3);
		marDelPlata = new Localidad("Mar Del Plata", "Buenos Aires", -38.0055, -57.5426, 4);
		
		costoFijo = 20000;
		porcentajeRecargo = 0.40;
		margenDeError = 10;
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void calcularCostoConOrigenNuloLanzaExcepcionTest() {
		calculadora.calcularCostoConexion(null, rosario, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calcularCostoConDestinoNuloLanzaExcepcionTest() {
		calculadora.calcularCostoConexion(buenosAires, null, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calcularCostoConPrecioPorKmNegativoLanzaExcepcionTest() {
		calculadora.calcularCostoConexion(buenosAires, rosario, -5.0);
	}

	@Test
	public void distintaProvinciaSumaCostoFijoTest() {
		double costo = calculadora.calcularCostoConexion(buenosAires, rosario, 1);
		assertTrue(costo > costoFijo);
	}

	@Test
	public void mismaProvinciaSinCostoFijoTest() {
		double costo = calculadora.calcularCostoConexion(buenosAires, laPlata, 1);
		assertTrue(costo < costoFijo);
	}
	
	@Test
	public void distintaProvinciaConMas300kmTienenPorcentajeyCostoFijoTest() {
		double costo = calculadora.calcularCostoConexion(buenosAires, cordoba, 1);
		double costoEsperado = costoFijo + 647 + 647 * porcentajeRecargo;
		assertEquals(costoEsperado, costo, margenDeError);
	}
	
	@Test
	public void mismaProvinciaConMas300kmTienenPorcentajeyNoCostoFijoTest() {
		double costo = calculadora.calcularCostoConexion(buenosAires, marDelPlata, 1);
		double costoEsperado = 381 + 381 * porcentajeRecargo;
		assertEquals(costoEsperado, costo, margenDeError);
	}
}