package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.ConfiguracionCostos;

public class ConfiguracionCostosTest {

	@Test (expected = IllegalArgumentException.class)
	public void costoPorKmNegativoTest() {
		new ConfiguracionCostos(-1, 1, 0.1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void costoFijoDistintasProvinciasNegativoTest() {
		new ConfiguracionCostos(1, -1, 0.1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void porcentajeRecargoNegativoTest() {
		new ConfiguracionCostos(1, 1, -0.1);
	}
	
	@Test 
	public void costoPorKmCeroTest() {
		ConfiguracionCostos costos = new ConfiguracionCostos(0, 1, 0.1);
		assertEquals(0, costos.getCostoPorKm(), 0.001);	
	}
	
	@Test 
	public void costoFijoDistintasProvinciasCeroTest() {
		ConfiguracionCostos costos = new ConfiguracionCostos(1, 0, 0.1);
		assertEquals(0, costos.getCostoFijoDistintaProvincia(), 0.001);
	}
	
	@Test 
	public void porcentajeRecargoCeroTest() {
		ConfiguracionCostos costos = new ConfiguracionCostos(1, 1, 0.0);
		assertEquals(0, costos.getPorcentajeRecargo(), 0.001);
	}
	
	@Test 
	public void configuracionDeCostosValidoTest() {
		ConfiguracionCostos costos = new ConfiguracionCostos(100, 2000, 0.3);
		assertEquals(100, costos.getCostoPorKm(), 0.001);
		assertEquals(2000, costos.getCostoFijoDistintaProvincia(), 0.001);
		assertEquals(0.3, costos.getPorcentajeRecargo(), 0.001);
	}

}
