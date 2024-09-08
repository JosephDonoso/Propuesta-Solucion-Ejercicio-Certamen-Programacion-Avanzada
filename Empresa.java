package common;

public class Empresa {
	private String rut;
	private String giro;
	private MiMapa<String, Producto> mapProductos;
	private ColeccionLista<Venta> listVentas;
	
	public Empresa(String rut, String giro) {
		this.rut = rut;
		this.giro = giro;
		productos = new MiMapa<String, Producto>();
		ventas = new ColleccionLista<Venta>();
	}
	
	public boolean actualizarPrducto(String sku, String newDescripcion, int newPrecio) {
		if (!mapProductos.estaClave(sku)) return false;
		Producto producto = mapProductos.obtiene(sku);
		//Necesario para funcion ActualizarPrecioproducto de CyberMonday
		if(newDescripcion != null) producto.setDescripcion(newDescripcion);
		producto.setPrecio(newPrecio);
	}
	
	public ColeccionLista obtenerVentasMayores(int montoCorte) {
		Coleccionlista ventasMayores = new ColeccionLista<Venta>();
		Venta ventaActual = null;
		for(int i = 1; i <= listVentas.largo(); i++) {
			ventaActual = listVentas.elementoActual();
			if (ventaActual.getMontoTotal() > montoCorte) {
                ventasMayores.agrega(ventaActual);
            }
		}
		if (ventasMayores.largo() == 0) return null;
		return ventasMayores;
	}

	
	
	
	
	
	
	//--------Esto no es parte de la prueba-----------
	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
	//------------------------------------------------
	
}
