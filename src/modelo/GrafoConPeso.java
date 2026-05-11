package modelo;

import java.util.ArrayList;

public class GrafoConPeso {
	
	private int cantidadVertices;
	private ArrayList<AristaConPeso> aristas;
	
	public GrafoConPeso(int cantidadVertices){
		this.cantidadVertices = cantidadVertices;
		this.aristas = new ArrayList<>();
	}
	
	public void agregarArista(int origen, int destino, double peso)
	{		
		verificarVertice(origen);
		verificarVertice(destino);
		verificarPeso(peso);
		verificarDistintos(origen, destino);
		
		aristas.add(new AristaConPeso(origen, destino,peso));
	}
	
	public int getTamanio() { return this.cantidadVertices; }	
	public int getCantidadDeAristas() { return aristas.size(); }	
	public ArrayList<AristaConPeso> getAristas() { return aristas; }
	
	//Verificaciones
	private void verificarVertice(int vertice) {
		if(vertice < 0 || vertice >= cantidadVertices) {
			throw new IllegalArgumentException("Vertice invalido");
		}
	}	
	private void verificarPeso(double peso) {
		if(peso < 0) {
			throw new IllegalArgumentException("El peso no puede ser negativo");
		}
	}	
	private void verificarDistintos(int i, int j) {
		if(i == j) {
			throw new IllegalArgumentException("No se permiten Loops");
		}
	}

}
