package modelo;


public class AristaConPeso implements Comparable<AristaConPeso>{
	private int verticeOrigen;
	private int verticeDestino;
	private double peso;
	
	public AristaConPeso(int verticeOrigen, int verticeDestino, double peso) 
	{
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.peso = peso;
	}
	
	// Getters
	public int getOrigen(){
		return verticeOrigen;
	}	
	public int getDestino(){
		return verticeDestino;
	}	
	public double getPeso(){
		return peso;
	}

	//Compara pesos de aristas para ordenarlas en Kruskal
	@Override
	public int compareTo(AristaConPeso otra) {
		//Si this < otra = negativo - this=otra 0, - this > otra = positivo
		return Double.compare(this.peso, otra.peso); 
	}
	
	
}
