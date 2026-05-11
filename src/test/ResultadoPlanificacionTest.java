package test;
import modelo.AristaConPeso;

import modelo.ResultadoPlanificacion;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ResultadoPlanificacionTest {

	@Test
	public void constructorConexiones() {
		Set<AristaConPeso> conexiones=new HashSet<>();
		conexiones.add(new AristaConPeso(0,1,100));
		ResultadoPlanificacion resultado= new ResultadoPlanificacion(conexiones, 100);
		assertNotNull(resultado.getConexiones());
		assertEquals(1, resultado.getConexiones().size());
		
	}
	
	@Test 
	public void constructorCostoTotal() {
		Set<AristaConPeso> conexiones=new HashSet<>();
		ResultadoPlanificacion resultado= new ResultadoPlanificacion(conexiones, 250.5);
		assertEquals(250.5, resultado.getCostoTotal(),0.001);
	}

	@Test
	public void getConexionesDebeRetornarLasAristasCorrectas() {

	        Set<AristaConPeso> conexiones = new HashSet<>();
	        AristaConPeso arista = new AristaConPeso(0, 1, 100);
	        conexiones.add(arista);

	        ResultadoPlanificacion resultado = new ResultadoPlanificacion(conexiones, 100);
	        assertTrue(resultado.getConexiones().contains(arista));
	}

	@Test
	public void resultadoPuedeCrearseSinConexiones() {

	        Set<AristaConPeso> conexiones = new HashSet<>();
	        ResultadoPlanificacion resultado = new ResultadoPlanificacion(conexiones, 0);

	        assertEquals(0, resultado.getConexiones().size());

	        assertEquals(0, resultado.getCostoTotal(), 0.001);
    }
}
