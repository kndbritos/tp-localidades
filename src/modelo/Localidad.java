package modelo;

public class Localidad {
	private String nombre;
	private String provincia;
	private double latitud;
	private double longitud;
	private int idVertice;
	
	public Localidad(String nombre, String provincia, double latitud, double longitud, int idVertice) {
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
