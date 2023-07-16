package com.bestbank.productos.application.dto.res;

import com.bestbank.productos.domain.utils.TipoCliente;

import lombok.Data;

@Data
public class ClienteRes {
  
  private String id;
  
  private String nombres;
  
  private String apellidos;
  
  private String estado;
  
  private TipoCliente tipoCliente;
  

}
