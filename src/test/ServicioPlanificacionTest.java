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
	
    @Test(expected = IllegalArgumentException.class)
    public void generarGrafoConCeroLocalidadesLanzaExcepcionTest() {
        servicio.generarGrafoCompleto(costos);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generarGrafoConUnaLocalidadLanzaExcepcionTest() {
        servicio.agregarLocalidad(new Localidad("A", "Buenos Aires", -34.0, -58.0, 0));
        servicio.generarGrafoCompleto(costos);
    }

    @Test(expected = IllegalArgumentException.class)
    public void planificarConGrafoNuloLanzaExcepcionTest() {
        servicio.planificar(null);
    }
    
    @Test
    public void generarGrafoCompletoDebeCrearAristasTest() {       
        servicio.agregarLocalidad(new Localidad("A", "Buenos Aires", -34.0, -58.0, 0));
        servicio.agregarLocalidad(new Localidad("B", "Cordoba", -31.0, -64.0, 1));       
        GrafoConPeso grafo = servicio.generarGrafoCompleto(costos);
        
        assertNotNull(grafo);
        assertEquals(2, grafo.getTamanio());
        assertEquals(1, grafo.getCantidadDeAristas());
    }
    
    @Test
    public void generarGrafoCompletoConTresLocalidadesDebeCrearTresAristasTest() {
        servicio.agregarLocalidad(new Localidad("A", "Buenos Aires", -34.0, -58.0, 0));
        servicio.agregarLocalidad(new Localidad("B", "Cordoba", -31.0, -64.0, 1));
        servicio.agregarLocalidad(new Localidad("C", "Santa Fe", -32.9, -60.6, 2));
        GrafoConPeso grafo = servicio.generarGrafoCompleto(costos);

        assertEquals(3, grafo.getTamanio());
        assertEquals(3, grafo.getCantidadDeAristas());
    }

    @Test
    public void planificacionConGrafoVacioDebeTenerCostoCeroTest() {
        GrafoConPeso grafo = new GrafoConPeso(0);
        ResultadoPlanificacion resultado = servicio.planificar(grafo);
        assertEquals(0, resultado.getCostoTotal(), 0.001);
        assertEquals(0, resultado.getConexiones().size());
    }

    @Test
    public void calcularCostoTotalDebeSumarCorrectamenteTest() {
        GrafoConPeso grafo = new GrafoConPeso(2);
        grafo.agregarArista(0, 1, 100);
        ResultadoPlanificacion resultado = servicio.planificar(grafo);
        
        assertNotNull(resultado);
        assertEquals(100, resultado.getCostoTotal(), 0.001);
        assertEquals(1, resultado.getConexiones().size());
    }

    @Test
    public void agmDebeTenerCantidadCorrectaDeAristasTest() {
        GrafoConPeso grafo = new GrafoConPeso(3);
        grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(1, 2, 20);
        grafo.agregarArista(0, 2, 50);
        ResultadoPlanificacion resultado = servicio.planificar(grafo);
        
        assertEquals(2, resultado.getConexiones().size());
        assertEquals(30, resultado.getCostoTotal(), 0.001);
    }
    
    @Test
    public void reiniciarDebeEliminarLocalidadesTest() {
    	servicio.agregarLocalidad(new Localidad("A", "Buenos Aires", -34.0, -58.0, 0));
        servicio.reiniciar();

        assertEquals(0, servicio.getLocalidades().size());
    }
}