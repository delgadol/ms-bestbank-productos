package com.bestbank.productos.application.dto.req;

import com.bestbank.productos.domain.utils.TipoPersonaRol;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductoRolesReq {
  
  @NotEmpty
  private String codigoPersona;
  
  
  @NotNull
  private TipoPersonaRol rol;
  

}
