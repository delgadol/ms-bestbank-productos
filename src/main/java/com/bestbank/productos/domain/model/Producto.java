package com.bestbank.productos.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bestbank.productos.domain.utils.GrupoProducto;
import com.bestbank.productos.domain.utils.TipoCliente;
import com.bestbank.productos.domain.utils.TipoProducto;

import lombok.Data;

/**
 * Representa un producto.
 * La clase Producto es una entidad que se mapea a la colección "productos" en la base de datos.
 */
@Document(collection = "productos")
@Data
public class Producto implements Serializable{

  
  /**
   */ 
  private static final long serialVersionUID = -3885933279296836915L;
  
  @Id
  private String id;
  
  private GrupoProducto grupoProducto;
  
  private TipoProducto tipoProducto;
  
  private String codigoProducto;
  
  private String codigoPersona;
  
  private double comision;
  
  private Integer maxOperacionesMes = Integer.MAX_VALUE;
  
  private Integer minDiaMesOperacion;
  
  private Integer indEliminado = 0 ;
  
  private String estado = "0"; 
  
  private TipoCliente tipoCliente;
  
  private List<PersonaRoles> personaRoles = new ArrayList<>();
  
  private Date fechaCreacion ;
  
  private Date fechaActualizacion;
  

}