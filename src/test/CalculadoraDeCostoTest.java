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
	}
	
	//Distancia entre Bs As y Rosario: aprox 290 km
	@Test
	public void distintaProvinciaSumaCostoFijoTest() {
		double costo = calculadora.calcularCostoConexion(buenosAires, rosario, 1);
		assertTrue(costo > costoFijo);
	}

	//Distancia entre Bs As y La Plata: aprox 55 km
	@Test
	public void mismaProvinciaSinCostoFijoTest() {
		double costo = calculadora.calcularCostoConexion(buenosAires, laPlata, 1);
		assertTrue(costo < costoFijo);
	}
	
	//Distancia entre Bs As y Cordoba: aprox 647 km
	@Test
	public void distintaProvinciaConMas300kmTienenPorcentajeyCostoFijoTest() {
		double costo = calculadora.calcularCostoConexion(buenosAires, cordoba, 1);
		assertTrue(costo > costoFijo + 647 * porcentajeRecargo);
	}
	
	//Distancia entre Bs As y MarDelPlata: aprox 381 km
	@Test
	public void mismaProvinciaConMas300kmTienenPorcentajeyNoCostoFijoTest() {
		double costo = calculadora.calcularCostoConexion(buenosAires, marDelPlata, 1);
		assertTrue(costo > 381 * porcentajeRecargo);
	}
}
