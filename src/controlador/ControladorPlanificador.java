package controlador;

import modelo.Localidad;
import modelo.ResultadoPlanificacion;
import modelo.ServicioPlanificacion;
import modelo.GrafoConPeso;

public class ControladorPlanificador {

    private ServicioPlanificacion modelo;

    public ControladorPlanificador(ServicioPlanificacion modelo) {
        this.modelo = modelo;
    }

    public void agregarLocalidad(String nombre, String provincia, String latitudText, String longitudText) {
        validarNombre(nombre);
        validarProvincia(provincia);
        validarCoordenada(latitudText);
        validarCoordenada(longitudText);
                    
        double latitud = Double.parseDouble(latitudText);
        double longitud = Double.parseDouble(longitudText);
        int idVertice = modelo.getLocalidades().size();
        Localidad localidad = new Localidad(nombre, provincia, latitud, longitud, idVertice);
        
        modelo.agregarLocalidad(localidad);
    }

    public ResultadoPlanificacion planificar() {
        GrafoConPeso grafo = modelo.generarGrafoCompleto(1);
        return modelo.planificar(grafo);
    }

    public void reiniciar() {
        modelo.reiniciar();
    }

    public void guardarEnArchivo() throws Exception {
        modelo.guardarEnArchivo();
    }

    private void validarCoordenada(String coordenadaText) {
        if (coordenadaText == null || coordenadaText.isEmpty()) {
            throw new IllegalArgumentException("La coordenada no puede estar vacia");
        }
        if (coordenadaText.contains(",")) {
            throw new IllegalArgumentException("Las coordenadas deben escribirse con '.', no con ','");
        }
        if(!coordenadaText.matches("-?\\d+(\\.\\d+)?")) {
            throw new IllegalArgumentException("La coordenada solo puede ser numerica");
        }
    }

    private void validarNombre(String nombre){
        if (nombre==null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if(!nombre.matches("[a-zA-ZАИМСЗаимсзЯя ]+")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras");
        }
    }

    private void validarProvincia(String provincia) {
        if (provincia==null || provincia.isEmpty()) {
            throw new IllegalArgumentException("La provincia no puede estar vacia");
        }
        if(!provincia.matches("[a-zA-ZАИМСЗаимсзЯя ]+")) {
            throw new IllegalArgumentException("La provincia solo puede contener letras");
        }
    }
}