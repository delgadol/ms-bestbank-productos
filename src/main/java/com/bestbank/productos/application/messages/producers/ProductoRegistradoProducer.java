package com.bestbank.productos.application.messages.producers;

import com.bestbank.productos.application.dto.message.ProductoBrokerRes;
import com.bestbank.productos.application.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductoRegistradoProducer {
  
  private static final String KAFKA_TOPIC = "productos-registrado";
  
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;
  
  public void enviarProductosResgistrado(ProductoBrokerRes producto) {
    String[] idCtrlChanel = producto.getCodCtrlBroker().split(":");
    String kafKaChanel = KAFKA_TOPIC;
    if (idCtrlChanel.length>1) {
      producto.setCodCtrlBroker(idCtrlChanel[0]);
      kafKaChanel = idCtrlChanel[1];
    }
    final String kafkaTopic = kafKaChanel;
    String jsonProductoBrokerReq = JsonUtils.objectToJson(producto);
    log.info("cola >>" + jsonProductoBrokerReq);
    this.kafkaTemplate.send(kafkaTopic, jsonProductoBrokerReq);
    
  }

}
