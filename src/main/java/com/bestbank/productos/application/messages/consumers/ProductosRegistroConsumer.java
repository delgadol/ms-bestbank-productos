package com.bestbank.productos.application.messages.consumers;

import com.bestbank.productos.application.ProductosApplication;
import com.bestbank.productos.application.dto.message.ProductoBrokerReq;
import com.bestbank.productos.application.dto.message.ProductoBrokerRes;
import com.bestbank.productos.application.dto.req.ProductoReq;
import com.bestbank.productos.application.messages.producers.ProductoRegistradoProducer;
import com.bestbank.productos.application.utils.JsonUtils;
import com.bestbank.productos.infrastructure.utils.ModelMapperUtils;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductosRegistroConsumer {
  
  @Autowired
  private ProductosApplication servProd;
  
  @Autowired
  private ProductoRegistradoProducer servEnvProd;
  
  @KafkaListener(topics = "productos-registrar", groupId = "group_id")
  public void recibeProductoRegistrar(String jsonProductoBrokerReq) {
    log.info("Recibiendo Peticion Producto");
    ProductoBrokerReq productoBrokerReq = JsonUtils.jsonToObject(
        jsonProductoBrokerReq, ProductoBrokerReq.class);
    if (Objects.isNull(productoBrokerReq)) {
      log.error("Prodcuto Request Invalida");
      return;
    }
    ProductoReq productoReq = new ProductoReq();
    productoReq.setCodigoPersona(productoBrokerReq.getCodigoPersona());
    productoReq.setTipoProducto(productoBrokerReq.getTipoProducto());
    servProd
        .postProduct(productoReq)
          .subscribe(prodRes -> {
            ProductoBrokerRes prodBrokerRes = 
                ModelMapperUtils.map(prodRes, ProductoBrokerRes.class);
            prodBrokerRes.setCodCtrlBroker(productoBrokerReq.getCodCtrlBroker());
            log.info("Envio de Producto Registrado : " + prodBrokerRes.getId());
            servEnvProd.enviarProductosResgistrado(prodBrokerRes);
          });
    
  }

}
