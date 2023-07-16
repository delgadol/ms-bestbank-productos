package com.bestbank.productos.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bestbank.productos.domain.model.Producto;
import com.bestbank.productos.domain.utils.TipoProducto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductosRepository extends ReactiveMongoRepository<Producto, String>{
  
  Mono<Long> countByTipoProductoAndCodigoPersonaAndIndEliminado(TipoProducto tipoProducto,String codigoPersona, Integer indEliminado);
  
  Mono<Producto> findFirstByIdAndIndEliminado(String id, Integer indEliminado);
  
  Flux<Producto> findAllByCodigoPersonaAndIndEliminado(String codigoPersona,Integer indEliminado);

}