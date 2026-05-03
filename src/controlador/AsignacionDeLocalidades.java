package controlador;

import modelo.GrafoConPeso;
import modelo.CalculadoraDeCosto;
import modelo.Localidad;


public class AsignacionDeLocalidades {
	private GrafoConPeso grafo;
	private CalculadoraDeCosto calculadora;
    private double precioPorKm;
    
    
    public AsignacionDeLocalidades(int cantidadLocalidades, double precioPorKm) {
    	this.grafo = new GrafoConPeso(cantidadLocalidades);
    	this.calculadora = new CalculadoraDeCosto();
    	this.precioPorKm = precioPorKm;
    }
    
    
    public void conectarLocalidades(Localidad origen, Localidad destino) {
    	double costoTotal = calculadora.calcularCostoConexion(origen, destino, this.precioPorKm);
    	
    	grafo.agregarArista(origen.getIdVertice(), destino.getIdVertice(), costoTotal);
    }
    
}
