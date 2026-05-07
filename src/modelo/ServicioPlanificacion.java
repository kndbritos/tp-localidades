package modelo;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import controlador.KruskalAGM;

public class ServicioPlanificacion {
	private CalculadoraDeCosto calculadora = new CalculadoraDeCosto();
	
	
	public GrafoConPeso generarGrafoCompleto(List<Localidad> localidades, double precioPorKm) {
		GrafoConPeso grafo = new GrafoConPeso(localidades.size());
		
		for (int i = 0; i < localidades.size(); i++) {
			for (int j = i+1; j < localidades.size(); j++) {
				Localidad origen = localidades.get(i);
				Localidad destino = localidades.get(j);
				
				double costoFinal = calculadora.calcularCostoConexion(origen, destino, precioPorKm);
				
				grafo.agregarArista(i, j, costoFinal);
			}
		}
		
		return grafo;
	}
	
	public ResultadoPlanificacion planificar(GrafoConPeso grafo) {

        KruskalAGM kruskal = new KruskalAGM();

        Set<AristaConPeso> conexiones = kruskal.obtenerAGM(grafo);

        double total = calcularCostoTotal(conexiones);

        return new ResultadoPlanificacion(conexiones, total);
    }

    private double calcularCostoTotal(Set<AristaConPeso> conexiones) {

        double total = 0;

        for (AristaConPeso arista : conexiones) {
            total += arista.getPeso();
        }
        
        BigDecimal totalRedondeado =
                new BigDecimal(total)
                        .setScale(2,
                                RoundingMode.HALF_UP);

        return totalRedondeado.doubleValue();
    }
    
}
