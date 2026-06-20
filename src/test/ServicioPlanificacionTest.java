package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import modelo.ServicioPlanificacion;
import modelo.ConfiguracionCostos;
import modelo.GrafoConPeso;
import modelo.Localidad;
import modelo.ResultadoPlanificacion;

public class ServicioPlanificacionTest {
	private ServicioPlanificacion servicio;
	private ConfiguracionCostos costos;

	@Before
	public void inicializar() {
		servicio = new ServicioPlanificacion();
		costos = new ConfiguracionCostos(100, 20000, 0.30);
	}
	
    @Test
    public void generarGrafoCompletoDebeCrearAristas() {       
        servicio.agregarLocalidad(new Localidad("A", "Buenos Aires", -34.0, -58.0, 0));
        servicio.agregarLocalidad(new Localidad("B", "Cordoba", -31.0, -64.0, 1));
        
        GrafoConPeso grafo = servicio.generarGrafoCompleto(costos);
        assertNotNull(grafo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generarGrafoConCeroLocalidadesLanzaExcepcion() {
        servicio.generarGrafoCompleto(costos);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generarGrafoConUnaLocalidadLanzaExcepcion() {
        servicio.agregarLocalidad(new Localidad("A", "Buenos Aires", -34.0, -58.0, 0));
        servicio.generarGrafoCompleto(costos);
    }

    @Test(expected = IllegalArgumentException.class)
    public void planificarConGrafoNuloLanzaExcepcion() {
        servicio.planificar(null);
    }

    @Test
    public void planificacionConGrafoVacioDebeTenerCostoCero() {
        GrafoConPeso grafo = new GrafoConPeso(0);
        ResultadoPlanificacion resultado = servicio.planificar(grafo);
        assertEquals(0, resultado.getCostoTotal(), 0.001);
        assertEquals(0, resultado.getConexiones().size());
    }

    @Test
    public void calcularCostoTotalDebeSumarCorrectamente() {
        GrafoConPeso grafo = new GrafoConPeso(2);
        grafo.agregarArista(0, 1, 100);
        ResultadoPlanificacion resultado = servicio.planificar(grafo);
        assertNotNull(resultado);
    }

    @Test
    public void agmDebeTenerCantidadCorrectaDeAristas() {
        GrafoConPeso grafo = new GrafoConPeso(3);
        grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(1, 2, 20);
        grafo.agregarArista(0, 2, 50);

        ResultadoPlanificacion resultado = servicio.planificar(grafo);
        assertEquals(2, resultado.getConexiones().size());
    }
}