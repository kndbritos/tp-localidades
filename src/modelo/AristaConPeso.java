package modelo;

public class AristaConPeso implements Comparable<AristaConPeso> {
    private int verticeOrigen;
    private int verticeDestino;
    private double peso;

    public AristaConPeso(int verticeOrigen, int verticeDestino, double peso) {
        if (verticeOrigen < 0 || verticeDestino < 0) {
            throw new IllegalArgumentException("Los vertices no pueden ser negativos");
        }
        if (peso < 0) {
            throw new IllegalArgumentException("El peso no puede ser negativo");
        }
        this.verticeOrigen = verticeOrigen;
        this.verticeDestino = verticeDestino;
        this.peso = peso;
    }

    public int getOrigen() { return verticeOrigen; }
    public int getDestino() { return verticeDestino; }
    public double getPeso() { return peso; }

    @Override
    public int compareTo(AristaConPeso otra) {
        return Double.compare(this.peso, otra.peso);
    }
}