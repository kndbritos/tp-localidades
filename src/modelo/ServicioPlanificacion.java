package modelo;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ServicioPlanificacion {

    private List<Localidad> localidades;
    private ArchivoLocalidades archivoLocalidades;
    private CalculadoraDeCosto calculadora = new CalculadoraDeCosto();
    private List<Observador> observadores;

    public ServicioPlanificacion() {
        this.localidades = new ArrayList<>();
        this.archivoLocalidades = new ArchivoLocalidades("localidades.json");
        this.observadores = new ArrayList<>();
    }

    public void agregarObservador(Observador obs) {
        observadores.add(obs);
    }

    private void notificarObservadores() {
        for(Observador obs : observadores) {
            obs.actualizar();
        }
    }

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    public void agregarLocalidad(Localidad localidad) {
        validarLocalidadDuplicada(localidad.getNombre(), localidad.getProvincia());
        localidades.add(localidad);
        notificarObservadores();
    }

    public void reiniciar() {
        localidades.clear();
        notificarObservadores();
    }

    private void validarLocalidadDuplicada(String nombre, String provincia) {
        for(Localidad localidad : localidades) {
            if(localidad.getNombre().trim().equalsIgnoreCase(nombre.trim()) && localidad.getProvincia().trim().equalsIgnoreCase(provincia.trim())) {
                 throw new IllegalArgumentException("La localidad ya fue cargada");
            }
        }
    }

    public void guardarEnArchivo() throws IOException {
        this.archivoLocalidades.guardar(localidades);
    }

    public void cargarDesdeArchivo() {
        try {
            this.localidades = this.archivoLocalidades.cargar();
            notificarObservadores();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GrafoConPeso generarGrafoCompleto(double precioPorKm) {
        if(localidades.size() < 2){
            throw new IllegalArgumentException("Para poder planificar conexiones debe haber minimo 2 localidades cargadas");
        }
        
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
        BigDecimal totalRedondeado = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
        return totalRedondeado.doubleValue();
    }
}