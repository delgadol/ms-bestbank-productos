package com.bestbank.productos.application.dto.message;

import com.bestbank.productos.domain.utils.GrupoProducto;
import com.bestbank.productos.domain.utils.TipoCliente;
import com.bestbank.productos.domain.utils.TipoProducto;
import lombok.Data;

/**
 * Clase que representa la respuesta de un producto.
 */
@Data
public class ProductoBrokerRes {
  
  private String codCtrlBroker;
  
  private String id;
  
  private GrupoProducto grupoProducto;
  
  private TipoProducto tipoProducto;
  
  private String codigoProducto;
  
  private String codigoPersona;
  
  private String estado;
  
  private TipoCliente tipoCliente;
  
  private Integer maxOperacionesMes;
  
  private Integer minDiaMesOperacion; 
  
  private Double costExtraOperacionesMes;
  

}
