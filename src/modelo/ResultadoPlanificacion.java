package modelo;

import java.util.Set;

public class ResultadoPlanificacion {
    private Set<AristaConPeso> conexiones;
    private double costoTotal;

    public ResultadoPlanificacion(Set<AristaConPeso> conexiones, double costoTotal) {
        if (conexiones == null) {
            throw new IllegalArgumentException("Las conexiones no pueden ser nulas");
        }
        if (costoTotal < 0) {
            throw new IllegalArgumentException("El costo total no puede ser negativo");
        }
        this.conexiones = conexiones;
        this.costoTotal = costoTotal;
    }

    public Set<AristaConPeso> getConexiones() { return conexiones; }
    public double getCostoTotal() { return costoTotal; }
}