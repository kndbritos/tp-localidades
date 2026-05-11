package modelo;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KruskalAGM {
	private int[] padre;
	
	private int buscar(int i) {
        while (this.padre[i] != i) {
            i = this.padre[i];
        }
        return i;
    }
	
	private void unir(int i, int j) {
        int raizI = buscar(i);
        int raizJ = buscar(j);       
        this.padre[raizI] = raizJ; 
    }
		
	private void inicializarUnionFind(int cantidadVertices) {
		this.padre = new int[cantidadVertices];
		
		for (int i = 0; i < cantidadVertices; i++) {
			this.padre[i] = i;
		}
	}
		
	public Set<AristaConPeso> obtenerAGM(GrafoConPeso grafo) {
		Set<AristaConPeso> aristasAGM = new HashSet<>();
		
		List<AristaConPeso> aristasOrdenadas = new ArrayList<>(grafo.getAristas());
		Collections.sort(aristasOrdenadas);
		
		inicializarUnionFind(grafo.getTamanio());
		
		for (AristaConPeso arista : aristasOrdenadas) {
			int origen = arista.getOrigen();
			int destino = arista.getDestino();
			
			if (buscar(origen) != buscar(destino)) {
				aristasAGM.add(arista);
				unir(origen, destino);
				
				if (aristasAGM.size() == grafo.getTamanio() - 1) {
					break;
				}
			}
		}
		
		if (grafo.getTamanio() > 1 && aristasAGM.size() != grafo.getTamanio() - 1) {
			throw new IllegalArgumentException("el grafo no es conexo. No es posible armar un AGM");
		}		
		return aristasAGM;
	}

}
