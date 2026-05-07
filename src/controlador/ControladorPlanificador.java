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
	}

	private void validarCantidadDeLocalidadesCargadas() {
		if(localidades.size() < 2) 
		{
			throw new IllegalArgumentException("Para poder planificar conexiones debe haber minimo 2 localidades cargadas");
		}
	}


}
