package controlador;

import java.util.ArrayList;

import modelo.GrafoConPeso;
import modelo.Localidad;
import modelo.ResultadoPlanificacion;
import modelo.ServicioPlanificacion;

public class ControladorPlanificador {
	
	private ArrayList<Localidad> localidades;
	private ServicioPlanificacion servicio;
	
	public ControladorPlanificador() {
		this.localidades = new ArrayList<Localidad>();
		this.servicio = new ServicioPlanificacion();
	}
	
	public ArrayList<Localidad> getLocalidades(){
		return localidades;
	}
	
	public void agregarLocalidad(String nombre, String provincia, String latitudText, String longitudText)
	{
		validarLocalidadDuplicada(nombre,provincia);
		validarNombre(nombre);
		validarProvincia(provincia);
		validarCoordenada(latitudText);
		validarCoordenada(longitudText);

		
						
		double latitud = Double.parseDouble(latitudText);
		double longitud = Double.parseDouble(longitudText);
		int idVertice = localidades.size();		
		Localidad localidad = new Localidad(nombre, provincia, latitud, longitud, idVertice);
		
		localidades.add(localidad);
	}

	public ResultadoPlanificacion planificar() {
		validarCantidadDeLocalidadesCargadas();
		GrafoConPeso grafo = servicio.generarGrafoCompleto(localidades, 1);
		return servicio.planificar(grafo);
	}


	
	//Validaciones para la interfaz
	private void validarCoordenada(String coordenadaText) {
		if (coordenadaText.isEmpty() || coordenadaText == null) 
		{
			throw new IllegalArgumentException("La coordenada no puede estar vacia");
		}
		if (coordenadaText.contains(",")) 
		{
			throw new IllegalArgumentException("Las coordenadas deben escribirse con '.', no con ','");
		}
		if(!coordenadaText.matches("-?\\d+(\\.\\d+)?")) {
			throw new IllegalArgumentException("La coordenada solo puede ser numerica");
		}

	}

	private void validarCantidadDeLocalidadesCargadas() {
		if(localidades.size() < 2) 
		{
			throw new IllegalArgumentException("Para poder planificar conexiones debe haber minimo 2 localidades cargadas");
		}
	}
	
	private void validarNombre(String nombre){
		if (nombre==null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre no puede estar vacio");
		}
		if(!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
			throw new IllegalArgumentException("El nombre solo puede contener letras");
		}
	}
	
	private void validarProvincia(String provincia) {
		if (provincia==null || provincia.isBlank()) {
			throw new IllegalArgumentException("La provincia no puede estar vacia");
		}
		if(!provincia.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
			throw new IllegalArgumentException("La provincia solo puede contener letras");
		}
	}

	private void validarLocalidadDuplicada(String nombre,String provincia) {
		for(Localidad localidad : localidades) {
			if(localidad.getNombre().equalsIgnoreCase(nombre) && localidad.getProvincia().equalsIgnoreCase(provincia)) {
				 throw new IllegalArgumentException("La localidad ya fue cargada");
			}
		}
	}

	
}
