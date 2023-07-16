package com.bestbank.productos.infrastructure.persistence;

import com.bestbank.productos.domain.model.ProductoCartera;
import com.bestbank.productos.domain.repository.DatabaseEmulatorRespo;
import com.bestbank.productos.domain.utils.TipoProducto;

import reactor.core.publisher.Mono;


public abstract class DataProductoRepoImpl {
  
  protected DatabaseEmulatorRespo carteraProdRep = new DatabaseEmulatorRespo(); 
  
  public abstract Mono<ProductoCartera> getValoresCarteraPorTipoID(TipoProducto tipoProducto);
      

}
