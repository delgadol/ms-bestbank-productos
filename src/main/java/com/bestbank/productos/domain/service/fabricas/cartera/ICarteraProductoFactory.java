package com.bestbank.productos.domain.service.fabricas.cartera;

import com.bestbank.productos.application.services.ProductoServiceEspecial;
import com.bestbank.productos.domain.model.ProductoCartera;

public interface ICarteraProductoFactory {
  
  ProductoServiceEspecial crearPRoductoService(ProductoCartera carteraProducto);

}
