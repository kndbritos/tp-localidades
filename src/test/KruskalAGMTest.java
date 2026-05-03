package test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Set;

import controlador.KruskalAGM;
import modelo.AristaConPeso;
import modelo.GrafoConPeso;

public class KruskalAGMTest {

	@Test
	public void testKruskalEligeRutasMasBaratasSinCiclos() {
		GrafoConPeso grafoPrueba = new GrafoConPeso(3);
		
		grafoPrueba.agregarArista(0, 1, 10);
		grafoPrueba.agregarArista(0, 2, 50);
		grafoPrueba.agregarArista(1, 2, 20);
		
		KruskalAGM kruskal = new KruskalAGM();
		
		Set<AristaConPeso> aristasResultantes = kruskal.obtenerAGM(grafoPrueba);
		
		assertEquals(2, aristasResultantes.size());
		
		double costoTotal = 0;
		
		for (AristaConPeso arista : aristasResultantes) {
			costoTotal += arista.getPeso();
		}
		
		assertEquals(30, costoTotal, 0.001);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
    public void testFallaConGrafoSinAristas() {
        GrafoConPeso grafoVacio = new GrafoConPeso(5);
        KruskalAGM kruskal = new KruskalAGM();

        kruskal.obtenerAGM(grafoVacio);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testFallaConGrafoNoConexo() {
        GrafoConPeso grafoIncompleto = new GrafoConPeso(4);
        
        grafoIncompleto.agregarArista(0, 1, 10.0);
        grafoIncompleto.agregarArista(2, 3, 10.0);

        KruskalAGM kruskal = new KruskalAGM();

        kruskal.obtenerAGM(grafoIncompleto);
    }
}
