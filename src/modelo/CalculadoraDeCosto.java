package modelo;

public class CalculadoraDeCosto {

	private static final int RADIO_TIERRA = 6371;
	private static final double costoFijo = 20000;
	private static final double porcentajeRecargo = 0.40;
	private static final double distanciaParaRecargo = 0.40;
	
	// Formula de Haversine para calcular distancia en km entre 2 coordenadas reales
	private double calcularDistanciaEnKm(Localidad loc1, Localidad loc2) {
        double latDist = Math.toRadians(loc2.getLatitud() - loc1.getLatitud());
        double lonDist = Math.toRadians(loc2.getLongitud() - loc1.getLongitud());
        double a = Math.sin(latDist / 2) * Math.sin(latDist / 2)
                 + Math.cos(Math.toRadians(loc1.getLatitud())) * Math.cos(Math.toRadians(loc2.getLatitud()))
                 * Math.sin(lonDist / 2) * Math.sin(lonDist / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RADIO_TIERRA * c; 
    }
	
	
	public double calcularCostoConexion(Localidad loc1, Localidad loc2, double costoPorKm) {
        
        double distancia = calcularDistanciaEnKm(loc1, loc2);
        
        double costoFinal = distancia * costoPorKm;

        if (distancia > distanciaParaRecargo) {
            costoFinal = costoFinal + (costoFinal * porcentajeRecargo);
        }

        if (!loc1.getProvincia().equals(loc2.getProvincia())) {
            costoFinal = costoFinal + costoFijo;
        }

        return costoFinal;
    }
}
