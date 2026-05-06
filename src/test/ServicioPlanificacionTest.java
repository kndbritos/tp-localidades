package test;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modelo.ServicioPlanificacion;
import modelo.GrafoConPeso;
import modelo.Localidad;
import modelo.ResultadoPlanificacion;
import modelo.AristaConPeso;
public class ServicioPlanificacionTest {

	

    @Test
    public void generarGrafoCompletoDebeCrearAristas() {

        ServicioPlanificacion servicio =
                new ServicioPlanificacion();

        List<Localidad> localidades =
                new ArrayList<>();

        Localidad a =
                new Localidad("A", "Buenos Aires", -34.0, -58.0, 0);

        Localidad b =
                new Localidad("B", "Cordoba", -31.0, -64.0, 1);

        localidades.add(a);
        localidades.add(b);

        GrafoConPeso grafo =
                servicio.generarGrafoCompleto(localidades, 100);

        assertNotNull(grafo);
    }

    @Test
    public void planificacionConGrafoVacioDebeTenerCostoCero() {

        ServicioPlanificacion servicio =
                new ServicioPlanificacion();

        GrafoConPeso grafo =
                new GrafoConPeso(0);

        ResultadoPlanificacion resultado =
                servicio.planificar(grafo);

        assertEquals(0,
                resultado.getCostoTotal(),
                0.001);

        assertEquals(0,
                resultado.getConexiones().size());
    }

    @Test
    public void calcularCostoTotalDebeSumarCorrectamente() {

        ServicioPlanificacion servicio =
                new ServicioPlanificacion();

        GrafoConPeso grafo =
                new GrafoConPeso(2);

        grafo.agregarArista(0, 1, 100);

        ResultadoPlanificacion resultado =
                servicio.planificar(grafo);

        assertNotNull(resultado);
    }

    @Test
    public void agmDebeTenerCantidadCorrectaDeAristas() {

        ServicioPlanificacion servicio =
                new ServicioPlanificacion();

        GrafoConPeso grafo =
                new GrafoConPeso(3);

        grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(1, 2, 20);
        grafo.agregarArista(0, 2, 50);

        ResultadoPlanificacion resultado =
                servicio.planificar(grafo);

        assertEquals(2,
                resultado.getConexiones().size());
    }
}

