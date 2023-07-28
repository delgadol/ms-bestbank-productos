package com.bestbank.productos.application.dto.message;

import com.bestbank.productos.domain.utils.TipoProducto;
import lombok.Data;

/**
 * Clase que representa una solicitud para crear o actualizar un producto.
 */
@Data
public class ProductoBrokerReq {
  
  private String codCtrlBroker;
  
  private String codigoPersona;
  
  private TipoProducto tipoProducto;
  
  
}
