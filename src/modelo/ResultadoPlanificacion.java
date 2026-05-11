package modelo;

import java.util.Set;

public class ResultadoPlanificacion {
			private Set<AristaConPeso> conexiones;
			private double costoTotal;
			
			public ResultadoPlanificacion(Set<AristaConPeso> conexiones, double costoTotal) {
				this.conexiones = conexiones;
				this.costoTotal = costoTotal;
			}
			
			public Set<AristaConPeso> getConexiones(){ return conexiones; }
			public double getCostoTotal() { return costoTotal; }
	
}
