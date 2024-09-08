package common;

public class Venta {
	
	private int numeroSerie;
	private int cantUnidades;
	private int montoTotal;
	private String sku;
	
	public int getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(int numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getCantUnidades() {
		return cantUnidades;
	}
	public void setCantUnidades(int cantUnidades) {
		this.cantUnidades = cantUnidades;
	}
	public int getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(int montoTotal) {
		this.montoTotal = montoTotal;
	}
}