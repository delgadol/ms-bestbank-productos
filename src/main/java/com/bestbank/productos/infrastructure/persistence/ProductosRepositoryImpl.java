package com.bestbank.productos.infrastructure.persistence;

import com.bestbank.productos.domain.model.Producto;
import com.bestbank.productos.domain.repository.ProductosRepository;
import com.bestbank.productos.domain.utils.TipoProducto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class ProductosRepositoryImpl {
  
  protected final ProductosRepository productosRepo;
  
  protected ProductosRepositoryImpl(ProductosRepository productosRepo) {
    super();
    this.productosRepo = productosRepo;
  }

  public abstract Mono<Long> countByTipoProductoAndCodigoPersonaAndIndEliminado(TipoProducto tipoProducto,String codigoPersona, Integer indEliminado);
  
  public abstract Mono<Producto> findFirstByIdAndIndEliminado(String id, Integer indEliminado);
  
  public abstract Flux<Producto> findAllByCodigoPersonaAndIndEliminado(String codigoPersona,Integer indEliminado);
  
  public abstract Mono<Producto> save(Producto producto);


}
