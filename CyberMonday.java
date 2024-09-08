package common;

public class CyberMonday {
	private Listable<Empresa> listEmpresas;
	
	public Empresa eliminaEmpresa(String rutEmpresa) {
		Empresa empresaActual = null;
		for(int i = 0; i < listEmpresas; i++) {
			empresaActual = listEmpresas.get(i);
			if (empresaActual.getRut().equals(rutEmpresa)) {
				//Esta sentencia necesita el supuesto de que el Listable.remove(int i) retorna el objeto a eliminar
				return listEmpresas.remove(i);
			}
		}
		
		return null;
	}
	
	public boolean actualizarPrecioProducto(String sku, int newPrecio, String rutEmpresa){
		Empresa empresaActual = null;
		//Por cada empresa llamar a su función actualizar producto
		for(int i = 0; i < listEmpresas; i++) {
			empresaActual = listEmpresas.get(i);
			if (empresaActual.getRut().equals(rutEmpresa)) {
				//Tuvo que ajustarse la función actualizar producto, 
				//sino iba a ser vomitivo hacer la actualización de precio
				return empresaActual.actualizarPrducto(sku, null, newPrecio);
			}
		}
		return false; //Empresa no se encontró
	}
	
	public MiMapa obtenerSKUsMayores(int umbral) {
		MiMapa<String, Venta> mapSkusMayores = new MiMapa<String, Venta>();
		Empresa empresaActual = null;
		ColeccionLista<Venta> ventasMayores = null;
		//Por cada empresa obtener la lista de ventas mayores
		for(int i = 0; i < listEmpresas; i++) {
			empresaActual = listEmpresas.get(i);
			ventasMayores = empresaActual.obtenerVentasMayores(umbral);
			//Si la función no nos retorna null
			if (ventasMayores != null) {
				Venta ventaActual = null;
				Venta mayorVenta = null;
				String skuProducto = null;
				int montoTotalActual, mayorMontoTotal;
				//Por cada venta en la lista de ventas mayores comparar los montos asociados a cada producto
				for(int j = 1; j <= ventasMayores.largo(); j++) {
					ventaActual = ventasMayores.elementoActual();
					skuProducto = ventaActual.getSku();
					//Evaluar si el producto ya estaba en el mapa
					if(mapSkusMayores.estaClave(skuProducto)) {
						mayorVenta = mapSkusMayores.obtiene(skuProducto);
						montoTotalActual = ventaActual.getMontoTotal();
						mayorMontoTotal = mayorVenta.getMontoTotal();
						//Evaluar si la venta tiene un monto mayor al del mapa, en cuyo caso actualizar
						if (mayorMontoTotal < montoTotalActual) {
							//Como el mapa no tiene función actualizar un valor, se borra y se vuelve a agregar, pero con la venta actual
							mapSkusMayores.elimina(skuProducto);
							mapSkusMayores.agrega(skuProducto, ventaActual);
						}
					}
					//Si no estaba en el mapa, agrgarlo y asociarle esa venta
					else {
						mapSkusMayores.agrega(skuProducto, ventaActual);
					}
				}
				
			}
		}
		
		return mapSkusMayores;
	}
}
