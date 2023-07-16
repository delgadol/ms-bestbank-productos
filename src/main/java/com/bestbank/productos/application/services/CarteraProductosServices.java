package com.bestbank.productos.application.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bestbank.productos.domain.model.ProductoCartera;
import com.bestbank.productos.domain.utils.GrupoProducto;
import com.bestbank.productos.domain.utils.TipoProducto;
import com.bestbank.productos.infrastructure.persistence.DataProductoRepoImpl;

import reactor.core.publisher.Mono;

@Service
public class CarteraProductosServices extends DataProductoRepoImpl{

  @Override
  public Mono<ProductoCartera> getValoresCarteraPorTipoID(
      TipoProducto tipoProducto) {
//    ProductoCartera carteraProd = ModelMapperUtils.map(
//        carteraProdRep.getModelData(tipoProducto.toString()),ProductoCartera.class);
    
    Map<String, Object> itemData = new HashMap<>();
    itemData = carteraProdRep.getModelData(tipoProducto.toString());
    ProductoCartera carteraProd = null;
    if (itemData != null) {
      carteraProd = new ProductoCartera();
      carteraProd.setComision((Double)itemData.get("comision"));
      carteraProd.setGrupoProducto((GrupoProducto)itemData.get("grupoProducto"));
      carteraProd.setMaxOperacionesMes((Integer)itemData.get("maxOperacionesMes"));
      carteraProd.setMaxProdEmpresarial((Integer)itemData.get("maxProdEmpresarial"));
      carteraProd.setMaxProdPersonal((Integer)itemData.get("maxProdPersonal"));
      carteraProd.setMinDiaMesOperacion((Integer)itemData.get("minDiaMesOperacion"));
      carteraProd.setMinSaldoMensual((Integer)itemData.get("minSaldoMensual"));
      @SuppressWarnings("unchecked")
      List<TipoProducto> prodPrevios = (List<TipoProducto>) itemData.get("reqPrevios");
      carteraProd.setReqPrevios(prodPrevios);
      carteraProd.setTipoProducto(tipoProducto);
    }
    return (tipoProducto == null?Mono.error(new Throwable("Producto Desconocido")):Mono.just(carteraProd));
  }

}
