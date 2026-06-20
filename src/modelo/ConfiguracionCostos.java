package modelo;

public class ConfiguracionCostos {
	private double costoFijoDistintaProvincia;
	private double porcentajeRecargo;
	private double costoPorKm;
	
	public ConfiguracionCostos(double costoPorKm, double costoFijoDistintaProvincia, double porcentajeRecargo) {
		validarValorPositivo(costoPorKm);
		validarValorPositivo(costoFijoDistintaProvincia);
		validarValorPositivo(porcentajeRecargo);
		
		this.costoPorKm = costoPorKm;
		this.costoFijoDistintaProvincia = costoFijoDistintaProvincia;
		this.porcentajeRecargo = porcentajeRecargo;
	}

	private void validarValorPositivo(double valor) {
		if (valor < 0)
			throw new IllegalArgumentException("El valor no puede ser negativo");
	}

	public double getCostoFijoDistintaProvincia() { return costoFijoDistintaProvincia; }
	public double getPorcentajeRecargo() { return porcentajeRecargo; }
	public double getCostoPorKm() { return costoPorKm; }
	
	
}
