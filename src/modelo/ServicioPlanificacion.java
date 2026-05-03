package modelo;


import java.util.Set;

import controlador.KruskalAGM;

public class ServicioPlanificacion {
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

        return total;
    }
}
