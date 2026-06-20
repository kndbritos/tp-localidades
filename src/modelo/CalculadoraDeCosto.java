package modelo;

public class CalculadoraDeCosto {

    private static final int RADIO_TIERRA = 6371;
    private static final double distanciaParaRecargo = 300;

    private double calcularDistanciaEnKm(Localidad loc1, Localidad loc2) {
        double latDist = Math.toRadians(loc2.getLatitud() - loc1.getLatitud());
        double lonDist = Math.toRadians(loc2.getLongitud() - loc1.getLongitud());
        double a = Math.sin(latDist / 2) * Math.sin(latDist / 2)
                 + Math.cos(Math.toRadians(loc1.getLatitud())) * Math.cos(Math.toRadians(loc2.getLatitud()))
                 * Math.sin(lonDist / 2) * Math.sin(lonDist / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RADIO_TIERRA * c;
    }

    public double calcularCostoConexion(Localidad loc1, Localidad loc2, ConfiguracionCostos costos) {
        if (loc1 == null || loc2 == null) {
            throw new IllegalArgumentException("Las localidades no pueden ser nulas");
        }
        if (costos == null) {
            throw new IllegalArgumentException("Los costos no pueden ser nulos");
        }
        
        double distancia = calcularDistanciaEnKm(loc1, loc2);
        double costoFinal = distancia * costos.getCostoPorKm();
        
        if (distancia > distanciaParaRecargo) {
            costoFinal = costoFinal + (costoFinal * costos.getPorcentajeRecargo());
        }
        if (!loc1.getProvincia().trim().equalsIgnoreCase(loc2.getProvincia().trim())) {
            costoFinal = costoFinal + costos.getCostoFijoDistintaProvincia();
        }
        return costoFinal;
    }
}