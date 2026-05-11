package modelo;

public class Localidad {
	private String nombre;
	private String provincia;
	private double latitud;
	private double longitud;
	private int idVertice;
	
	public Localidad(String nombre, String provincia, double latitud, double longitud, int idVertice) {
		
		if (nombre == null || nombre.length() == 0)
			throw new IllegalArgumentException("El nombre de la localidad no puede estar vacío");
		
		if (provincia == null || provincia.trim().isEmpty())
			throw new IllegalArgumentException("La provincia no puede estar vacía");
		
		if (latitud < -90.0 || latitud > 90.0)
			throw new IllegalArgumentException("Latitud inválida. Debe estar entre -90 y 90");
		
		if (longitud < -180.0 || longitud > 180.0)
			throw new IllegalArgumentException("Longitud inválida. Debe estar entre -180 y 180");
		
		if (idVertice < 0)
			throw new IllegalArgumentException("El ID del vértice no puede ser negativo");
        this.nombre = nombre;
        this.provincia = provincia;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idVertice = idVertice;
    }
		
	public String getNombre() { return nombre; }
	public String getProvincia() { return provincia; }
	public double getLatitud() { return latitud; }
	public double getLongitud() { return longitud; }
	public int getIdVertice() { return idVertice; }
}
