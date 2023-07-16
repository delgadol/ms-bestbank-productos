package com.bestbank.productos.application.services;

import com.bestbank.productos.domain.model.Producto;
import com.bestbank.productos.domain.model.ProductoCartera;

import reactor.core.publisher.Mono;

public abstract class ProductoServiceEspecial {
  
  protected final ProductoCartera carteraProp = new ProductoCartera();
  
  public abstract Mono<Producto> save(Producto producto);

}
