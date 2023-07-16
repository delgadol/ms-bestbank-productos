package com.bestbank.productos.application.dto.res;

import java.util.ArrayList;
import java.util.List;

import com.bestbank.productos.domain.model.PersonaRoles;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductoRolesRes extends ProductoRes {
  

  private List<PersonaRoles> personaRoles = new ArrayList<>();
  

}
