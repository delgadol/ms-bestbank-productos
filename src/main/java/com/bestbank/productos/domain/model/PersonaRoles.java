package com.bestbank.productos.domain.model;

import com.bestbank.productos.domain.utils.TipoPersonaRol;
import java.io.Serializable;
import lombok.Data;

/**
 * Clase que representa los roles de una persona y que implementa la interfaz Serializable.
 */
@Data
public class PersonaRoles implements Serializable {
  
  private static final long serialVersionUID = -4670435393085960694L;

  private String codigoPersona;
  
  private TipoPersonaRol rol;
  

}