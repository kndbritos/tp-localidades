package modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class GrafoConPesoTest {

	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeNegativoTest() {
		GrafoConPeso grafo = new GrafoConPeso(3);
		grafo.agregarArista(-1, 1, 2);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoTest() {
		GrafoConPeso grafo = new GrafoConPeso(3);
		grafo.agregarArista(1, -1, 2);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeExcedidoTest() {
		GrafoConPeso grafo = new GrafoConPeso(3);
		grafo.agregarArista(3, 1, 2);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeExcedidoTest() {
		GrafoConPeso grafo = new GrafoConPeso(3);
		grafo.agregarArista(1, 3, 2);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void pesoNegativoTest() {
		GrafoConPeso grafo = new GrafoConPeso(3);
		grafo.agregarArista(1, 2, -2);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopTest(){
		GrafoConPeso grafo = new GrafoConPeso(3);
		grafo.agregarArista(1, 1, 2);	
	}
	


	
}
