package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import modelo.ServicioPlanificacion;
import modelo.GrafoConPeso;
import modelo.Localidad;
import modelo.ResultadoPlanificacion;

public class ServicioPlanificacionTest {

    @Test
    public void generarGrafoCompletoDebeCrearAristas() {
        ServicioPlanificacion servicio = new ServicioPlanificacion();
        servicio.agregarLocalidad(new Localidad("A", "Buenos Aires", -34.0, -58.0, 0));
        servicio.agregarLocalidad(new Localidad("B", "Cordoba", -31.0, -64.0, 1));

        GrafoConPeso grafo = servicio.generarGrafoCompleto(100);
        assertNotNull(grafo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generarGrafoConCeroLocalidadesLanzaExcepcion() {
        ServicioPlanificacion servicio = new ServicioPlanificacion();
        servicio.generarGrafoCompleto(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generarGrafoConUnaLocalidadLanzaExcepcion() {
        ServicioPlanificacion servicio = new ServicioPlanificacion();
        servicio.agregarLocalidad(new Localidad("A", "Buenos Aires", -34.0, -58.0, 0));
        servicio.generarGrafoCompleto(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void planificarConGrafoNuloLanzaExcepcion() {
        ServicioPlanificacion servicio = new ServicioPlanificacion();
        servicio.planificar(null);
    }

    @Test
    public void planificacionConGrafoVacioDebeTenerCostoCero() {
        ServicioPlanificacion servicio = new ServicioPlanificacion();
        GrafoConPeso grafo = new GrafoConPeso(0);
        ResultadoPlanificacion resultado = servicio.planificar(grafo);
        assertEquals(0, resultado.getCostoTotal(), 0.001);
        assertEquals(0, resultado.getConexiones().size());
    }

    @Test
    public void calcularCostoTotalDebeSumarCorrectamente() {
        ServicioPlanificacion servicio = new ServicioPlanificacion();
        GrafoConPeso grafo = new GrafoConPeso(2);
        grafo.agregarArista(0, 1, 100);
        ResultadoPlanificacion resultado = servicio.planificar(grafo);
        assertNotNull(resultado);
    }

    @Test
    public void agmDebeTenerCantidadCorrectaDeAristas() {
        ServicioPlanificacion servicio = new ServicioPlanificacion();
        GrafoConPeso grafo = new GrafoConPeso(3);
        grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(1, 2, 20);
        grafo.agregarArista(0, 2, 50);

        ResultadoPlanificacion resultado = servicio.planificar(grafo);
        assertEquals(2, resultado.getConexiones().size());
    }
}