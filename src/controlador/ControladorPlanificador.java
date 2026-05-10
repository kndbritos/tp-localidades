package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.ArchivoLocalidades;
import modelo.GrafoConPeso;
import modelo.Localidad;
import modelo.ResultadoPlanificacion;
import modelo.ServicioPlanificacion;

public class ControladorPlanificador {
	
	private List<Localidad> localidades;
	private ServicioPlanificacion servicio;
	private ArchivoLocalidades archivoLocalidades;
	
	public ControladorPlanificador() {
		this.localidades = new ArrayList<Localidad>();
		this.servicio = new ServicioPlanificacion();
		this.archivoLocalidades = new ArchivoLocalidades("localidades.json");
	}
	
	public List<Localidad> getLocalidades(){
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
		if (coordenadaText == null || coordenadaText.isEmpty()) 
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
		if (nombre==null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede estar vacio");
		}
		if(!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
			throw new IllegalArgumentException("El nombre solo puede contener letras");
		}
	}
	
	private void validarProvincia(String provincia) {
		if (provincia==null || provincia.isEmpty()) {
			throw new IllegalArgumentException("La provincia no puede estar vacia");
		}
		if(!provincia.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
			throw new IllegalArgumentException("La provincia solo puede contener letras");
		}
	}

	private void validarLocalidadDuplicada(String nombre,String provincia) {
		for(Localidad localidad : localidades) {
			if(localidad.getNombre().trim().equalsIgnoreCase(nombre.trim()) && localidad.getProvincia().trim().equalsIgnoreCase(provincia.trim())) {
				 throw new IllegalArgumentException("La localidad ya fue cargada");
			}
		}
	}

	public void reiniciar() {
		localidades.clear();
	}

	public void guardarEnArchivo() {
		try {
			
			this.archivoLocalidades.guardar(localidades);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public void cargarDesdeArchivo() {
		try {
			
			this.localidades = this.archivoLocalidades.cargar();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	
}
