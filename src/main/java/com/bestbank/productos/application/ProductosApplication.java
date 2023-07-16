package com.bestbank.productos.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bestbank.productos.application.dto.req.ProductoReq;
import com.bestbank.productos.application.dto.res.ClienteRes;
import com.bestbank.productos.application.dto.res.ProductoRes;
import com.bestbank.productos.application.dto.res.ProductoRolesRes;
import com.bestbank.productos.application.services.CarteraProductosServices;
import com.bestbank.productos.application.services.ProductoServicioTool;
import com.bestbank.productos.application.services.ProductosService;
import com.bestbank.productos.application.services.SaldosService;
import com.bestbank.productos.application.utils.ApplicationConstants;
import com.bestbank.productos.application.utils.BankFnUtils;
import com.bestbank.productos.domain.model.PersonaRoles;
import com.bestbank.productos.domain.model.Producto;
import com.bestbank.productos.domain.model.ProductoCartera;
import com.bestbank.productos.domain.utils.GrupoProducto;
import com.bestbank.productos.domain.utils.TipoCliente;
import com.bestbank.productos.infrastructure.external.utis.WebClientApi;
import com.bestbank.productos.infrastructure.utils.ModelMapperUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductosApplication {
  
  @Value("${app.clientesApiUrl}")
  private String clienteUrlTemp;
  
  private final ProductosService servProd;
  
  private final CarteraProductosServices servCartera;
  
  private final SaldosService servSaldo;
  
  public ProductosApplication(ProductosService servProd, 
      CarteraProductosServices servCartera, 
      SaldosService servSaldo) {
    this.servProd = servProd;
    this.servCartera = servCartera;
    this.servSaldo = servSaldo;
  }

  /**
   * Crea un nuevo producto con la información proporcionada.
   * @param producto la información del producto a crear
   * @return un Mono que emite el objeto ProductoRes resultante
   */
  public Mono<ProductoRes> postProduct(ProductoReq producto){
    return isClienteOK(producto.getCodigoPersona()).
        flatMap(clienteApi->{
          return servCartera.getValoresCarteraPorTipoID(producto.getTipoProducto())
              .flatMap( carteraProd -> {
                /** seteamos el tipo de Cliente **/
                ProductoCartera carteraProdToServ = ModelMapperUtils.map(
                    carteraProd, ProductoCartera.class);
                carteraProdToServ.setTipoCliente(clienteApi.getTipoCliente());
                ProductoServicioTool servProdTool = new ProductoServicioTool();
                servProdTool.setCarteraProd(carteraProd);
                servProdTool.setServProd(servProd);
                servProdTool.setSerSaldo(servSaldo);
                Producto nuevoProducto = new Producto();
                /** Mapeamos todos los Flujos **/
                nuevoProducto.setCodigoPersona(clienteApi.getId());
                nuevoProducto.setCodigoProducto(BankFnUtils.uniqueProductCode());
                nuevoProducto.setComision(carteraProd.getComision());
                nuevoProducto.setEstado("0");
                nuevoProducto.setGrupoProducto(carteraProd.getGrupoProducto());
                nuevoProducto.setIndEliminado(ApplicationConstants.REGISTRO_NO_ELIMINADO);
                nuevoProducto.setMaxOperacionesMes(carteraProd.getMaxOperacionesMes());
                nuevoProducto.setMinDiaMesOperacion(carteraProd.getMinDiaMesOperacion());
                nuevoProducto.setTipoCliente(clienteApi.getTipoCliente());
                nuevoProducto.setTipoProducto(producto.getTipoProducto());
                nuevoProducto.setFechaCreacion(BankFnUtils.getDateTime());
                nuevoProducto.setFechaActualizacion(BankFnUtils.getDateTime());
                /** Nuevos Valores **/
                nuevoProducto.setMinSaldoMensual(carteraProd.getMinSaldoMensual());
                nuevoProducto.setCostExtraOperacionesMes(carteraProd.getCostExtraOperacionesMes());
                nuevoProducto.setCostMinSaldoMensual(carteraProd.getCostMinSaldoMensual());
                /** Mapeamos los Campos Basicos **/
                return servProdTool.save(nuevoProducto).
                    flatMap(productoDB -> {
                      return Mono.just(ModelMapperUtils.map(productoDB, ProductoRes.class));
                    });
              });
        });
  }
  
  /**
   * Actualiza un producto existente con la información proporcionada.
   * 
   * @param idProducto el identificador del producto a actualizar
   * @param producto la información actualizada del producto
   * @return un Mono que emite el objeto ProductoRes resultante
   */
  public Mono<ProductoRes> putProduct(String idProducto, ProductoReq producto){
    return null;
  }
  
  public Mono<ProductoRes> getProductById(String idProducto){
    return checkProductoClientOK(idProducto)
        .flatMap(productoDbOK -> {
          return Mono.just(ModelMapperUtils.map(productoDbOK, ProductoRes.class));
        });
  }
  
  /**
   * Obtiene un producto por su identificador.
   *
   * @param idProduct el identificador del producto
   * @return un Mono que emite el objeto ProductoRes correspondiente al ID proporcionado
   */  
  public Flux<ProductoRes> getAllProductByClientId(String idClient){
    return isClienteOK(idClient).flux().
        flatMap(clienteOK -> {
          return servProd.findAllByCodigoPersonaAndIndEliminado(idClient, ApplicationConstants.REGISTRO_NO_ELIMINADO)
            .filter(prodDBF1 -> prodDBF1.getEstado().equalsIgnoreCase(ApplicationConstants.ESTADO_NORMAL))
            .flatMap(prodDBOK -> {
              return Flux.just(ModelMapperUtils.map(prodDBOK , ProductoRes.class));
            });
        });
  }
  
  /**
   * Elimina un producto por su identificador.
   *
   * @param idProducto el identificador del producto a eliminar
   * @return un Mono que emite el objeto ProductoRes correspondiente al producto eliminado
   */
  public Mono<ProductoRes> delProductById(String idProducto){
    return servProd.findFirstByIdAndIndEliminado(idProducto, 
      ApplicationConstants.REGISTRO_NO_ELIMINADO)
      .filter(prodFiltro1 -> prodFiltro1.getEstado().equalsIgnoreCase(ApplicationConstants.ESTADO_NORMAL))
      .flatMap(prodDBOK -> {
        return WebClientApi.getMono(String.format(clienteUrlTemp,prodDBOK.getCodigoPersona()), 
          ClienteRes.class, idProducto).flatMap(clienteRes -> {
            Producto prodModDB = ModelMapperUtils.map(prodDBOK,Producto.class);
            prodModDB.setIndEliminado(ApplicationConstants.REGISTRO_ELIMINADO);
            return ModelMapperUtils.mapToMono(servProd.save(prodModDB), ProductoRes.class);
          });
      })
      .switchIfEmpty(Mono.error(
          new Throwable(String.format("%s Producto No Resgistrado", idProducto))
          )
      );        
  }
  
  /**
   * Obtiene un Producto y los Codigos Personas y Roles dentro de la cuenta.
   *
   * @param idProducto el identificador del producto a consultar
   * @return un Mono que emite el objeto ProductoRes correspondiente al producto
   */
  public Mono<ProductoRolesRes> getPersonaRolesByProductId(String idProducto){
    return checkProductoClientOK(idProducto).flatMap(productoOK -> {
      return Mono.just(ModelMapperUtils.map(productoOK, ProductoRolesRes.class));
    });
  }
  
  /**
   * Obtiene un Producto y los Codigos Personas y Roles dentro de la cuenta.
   * Luego de Eliminar una Persona
   *
   * @param idProducto el identificador del producto a modificar
   * @param codePersona el identificador cliente a eliminar
   * @return un Mono que emite el objeto ProductoRes correspondiente al producto modificado
   */
  public Mono<ProductoRolesRes> delPersonaRolesByProductIdAndCodePersona(String idProducto, String codePersona){
    return checkProductoClientOK(idProducto).flatMap( productoOK -> {
      Producto prodModDB = ModelMapperUtils.map( productoOK , Producto.class);
      List<PersonaRoles> nuevoPersonaRoles = prodModDB.getPersonaRoles()
          .stream()
          .filter( x -> !x.getCodigoPersona().equals(codePersona))
          .toList();
      prodModDB.setPersonaRoles(nuevoPersonaRoles);
      return ModelMapperUtils.mapToMono(servProd.save(prodModDB),ProductoRolesRes.class);
    });
  }
  
  /**
   * Obtiene un Producto y los Codigos Personas y Roles dentro de la cuenta.
   * Luego de Eliminar una Persona
   *
   * @param idProducto el identificador del producto a modificar
   * @param codePersona el identificador cliente a eliminar
   * @return un Mono que emite el objeto ProductoRes correspondiente al producto modificado
   */
  public Mono<ProductoRolesRes> addPersonaRolesByProductIdAndRolePersona(String idProducto, PersonaRoles personaRol){
    return checkProductoClientOK(idProducto)
        .flatMap(t -> { 
          return isClienteOK(personaRol.getCodigoPersona())
            .flatMap(clienteApi ->{
              return servProd.findFirstByIdAndIndEliminado(idProducto, ApplicationConstants.REGISTRO_NO_ELIMINADO)
                  .filter(producto -> producto.getEstado().equalsIgnoreCase(ApplicationConstants.ESTADO_NORMAL))
                  .flatMap(prodOK -> {
                    Producto modProducto = ModelMapperUtils.map(prodOK, Producto.class);
                    if(!(modProducto.getTipoCliente().equals(TipoCliente.EMPRESARIAL) 
                        && modProducto.getGrupoProducto().equals(GrupoProducto.PASIVOS))) {
                      throw new RuntimeException("Cuenta no Admite adicionales");
                    };
                    int existePersona = modProducto.getPersonaRoles()
                      .stream()
                      .filter( x -> x.getCodigoPersona()
                          .equals(personaRol.getCodigoPersona()))
                      .toList()
                      .size();
                    if (existePersona == 0 ) {
                      modProducto.getPersonaRoles().add(personaRol);
                    }else {
                      throw new RuntimeException("Cliente ya esta registrado");
                    }
                    return servProd.save(modProducto)
                        .flatMap( prodEntidad ->{
                          return Mono.just(ModelMapperUtils.map(prodEntidad, ProductoRolesRes.class));
                        });
                  });
            });
        });
  }
  
  /*
   * funcione Helper Para Verificar Producto y Cliente
   * 
   * */
  private Mono<Producto> checkProductoClientOK(String idProducto){
    return servProd.findFirstByIdAndIndEliminado(idProducto, 
        ApplicationConstants.REGISTRO_NO_ELIMINADO)
        .filter(prodFiltro1 -> prodFiltro1.getEstado().equalsIgnoreCase(ApplicationConstants.ESTADO_NORMAL))
        .flatMap(prodDBOK -> {
          return WebClientApi.getMono(String.format(clienteUrlTemp,prodDBOK.getCodigoPersona()), 
            ClienteRes.class, idProducto).flatMap(clienteRes -> {
              return Mono.just(prodDBOK);
            });
        })
        .switchIfEmpty(Mono.error(
            new Throwable(String.format("%s Producto Desconocido", idProducto))
            )
        );
  }
  
  /*
   * funcion para veridicar un cliente 
   * 
   *  
   * 
   * */
  
  private Mono<ClienteRes> isClienteOK(String idCliente) {
    return WebClientApi.getMono(String.format(clienteUrlTemp,idCliente), 
        ClienteRes.class, String.format("COD: %s",idCliente));
  }

}
