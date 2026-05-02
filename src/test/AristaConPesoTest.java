package test;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.AristaConPeso;

public class AristaConPesoTest {

	@Test
	public void aristaMenorPesoTest() {
		AristaConPeso a1 = new AristaConPeso(0, 1, 2);
		AristaConPeso a2 = new AristaConPeso(0, 1, 5);
		//A1 es menor que A2
		assertTrue(a1.compareTo(a2) < 0);
	}
	
	@Test
	public void aristaMayorPesoTest() {
		AristaConPeso a1 = new AristaConPeso(0, 1, 5);
		AristaConPeso a2 = new AristaConPeso(0, 1, 2);
		//A1 es mayor que A2
		assertTrue(a1.compareTo(a2) > 0);
	}
	
	@Test
	public void aristaConMismoPesoTest() {
		AristaConPeso a1 = new AristaConPeso(0, 1, 5);
		AristaConPeso a2 = new AristaConPeso(0, 2, 5);
		//A1 pesa lo mismo que A2
		assertTrue(a1.compareTo(a2) == 0);
	}

}
