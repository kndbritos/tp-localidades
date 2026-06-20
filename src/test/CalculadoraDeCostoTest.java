package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import modelo.CalculadoraDeCosto;
import modelo.ConfiguracionCostos;
import modelo.Localidad;

public class CalculadoraDeCostoTest {
	
	private CalculadoraDeCosto calculadora;
	private ConfiguracionCostos costos;
	private Localidad buenosAires;
	private Localidad rosario;
	private Localidad cordoba;
	private Localidad laPlata;
	private Localidad marDelPlata;
	private double costoPorKm;
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
		
		costoPorKm = 1;
		costoFijo = 20000;
		porcentajeRecargo = 0.40;
		margenDeError = 10;
		
		costos = new ConfiguracionCostos(costoPorKm, costoFijo, porcentajeRecargo);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void calcularCostoConOrigenNuloLanzaExcepcionTest() {
		calculadora.calcularCostoConexion(null, rosario, costos);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calcularCostoConDestinoNuloLanzaExcepcionTest() {
		calculadora.calcularCostoConexion(buenosAires, null, costos);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calcularCostoConCostosNulosTest() {
		calculadora.calcularCostoConexion(buenosAires, rosario, null);
	}

	@Test
	public void distintaProvinciaSumaCostoFijoTest() {
		double costo = calculadora.calcularCostoConexion(buenosAires, rosario, costos);
		assertTrue(costo > costos.getCostoFijoDistintaProvincia());
	}

	@Test
	public void mismaProvinciaSinCostoFijoTest() {
		double costo = calculadora.calcularCostoConexion(buenosAires, laPlata, costos);
		assertTrue(costo < costos.getCostoFijoDistintaProvincia());
	}
	
	@Test
	public void distintaProvinciaConMas300kmTienenPorcentajeyCostoFijoTest() {
		double costo = calculadora.calcularCostoConexion(buenosAires, cordoba, costos);
		double costoBase = 647 * costoPorKm;
		double costoEsperado = costoFijo + costoBase + costoBase * porcentajeRecargo;
		assertEquals(costoEsperado, costo, margenDeError);
	}
	
	@Test
	public void mismaProvinciaConMas300kmTienenPorcentajeyNoCostoFijoTest() {
		double costo = calculadora.calcularCostoConexion(buenosAires, marDelPlata, costos);
		double costoBase = 381 * costoPorKm;
		double costoEsperado = costoBase + costoBase * porcentajeRecargo;
		assertEquals(costoEsperado, costo, margenDeError);
	}
}