package com.bestbank.productos.domain.service.fabricas.cartera;

import com.bestbank.productos.application.services.ProductoServiceEspecial;
import com.bestbank.productos.application.services.ProductosService;
import com.bestbank.productos.application.utils.ApplicationConstants;
import com.bestbank.productos.domain.model.Producto;
import com.bestbank.productos.domain.model.ProductoCartera;
import com.bestbank.productos.domain.utils.TipoCliente;

import reactor.core.publisher.Mono;

public class ProductoServicioSinCtaProdPrevios extends ProductoServiceEspecial {

  private ProductosService servProd;
  private ProductoCartera carteraProd;
  
  public void setServProd(ProductosService servProd) {
    this.servProd = servProd;
  }

  public void setCarteraProd(ProductoCartera carteraProd) {
    this.carteraProd = carteraProd;
  }
  
  private Integer getMaxProductPerProfile(TipoCliente tipoCliente) {
    return (tipoCliente == TipoCliente.PERSONAL)?carteraProd.getMaxProdPersonal():carteraProd.getMaxProdEmpresarial();
  }

  @Override
  public Mono<Producto> save(Producto producto) {
    return servProd.countByTipoProductoAndCodigoPersonaAndIndEliminado(
        producto.getTipoProducto(),producto.getCodigoPersona(), 
        ApplicationConstants.REGISTRO_NO_ELIMINADO)
    .filter( numProdActivos -> numProdActivos < getMaxProductPerProfile(producto.getTipoCliente()))
    .flatMap(permiteGuardar -> {
      return servProd.save(producto);
    })
    .switchIfEmpty(Mono.error(
        new Throwable(String.format("%s Producto No Resgistrado ya posee Maximo Registrado", 
            producto.getTipoProducto().getDescripcion()))
        )
    );
    
  }

}
