package test;

import static org.junit.Assert.*;
import org.junit.Test;
import modelo.AristaConPeso;

public class AristaConPesoTest {

	@Test
	public void aristaMenorPesoTest() {
		AristaConPeso a1 = new AristaConPeso(0, 1, 2);
		AristaConPeso a2 = new AristaConPeso(0, 1, 5);
		assertTrue(a1.compareTo(a2) < 0);
	}
	
	@Test
	public void aristaMayorPesoTest() {
		AristaConPeso a1 = new AristaConPeso(0, 1, 5);
		AristaConPeso a2 = new AristaConPeso(0, 1, 2);
		assertTrue(a1.compareTo(a2) > 0);
	}
	
	@Test
	public void aristaConMismoPesoTest() {
		AristaConPeso a1 = new AristaConPeso(0, 1, 5);
		AristaConPeso a2 = new AristaConPeso(0, 2, 5);
		assertTrue(a1.compareTo(a2) == 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void aristaConPesoNegativoTest() {
		new AristaConPeso(0, 1, -5.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void aristaConOrigenNegativoTest() {
		new AristaConPeso(-1, 1, 10.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void aristaConDestinoNegativoTest() {
		new AristaConPeso(0, -1, 10.0);
	}
}