package com.bestbank.productos.application.messages.consumers;

import com.bestbank.productos.application.ProductosApplication;
import com.bestbank.productos.application.dto.req.ProductoReq;
import com.bestbank.productos.application.messages.dto.ClienteProdReq;
import com.bestbank.productos.application.utils.JsonUtils;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductosRegistroConsumer {
  
  @Autowired
  private ProductosApplication servProd;
  
  
  public void postProducto(String jsonClienteProdReq) {
    
    ClienteProdReq cliente = JsonUtils.jsonToObject(jsonClienteProdReq, ClienteProdReq.class);
    if (Objects.isNull(cliente)) {
      return;
    }
    ProductoReq productoReq = new ProductoReq();
    productoReq.setCodigoPersona(cliente.getId());
    productoReq.setTipoProducto(cliente.getTipoProducto());
    servProd.postProduct(productoReq)
    .subscribe(prodRes -> {
      
    });
    
  }

}
