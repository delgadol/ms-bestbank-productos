package com.bestbank.productos.infrastructure.persistence;

import com.bestbank.productos.domain.model.Saldo;
import com.bestbank.productos.domain.repository.SaldoRespository;

import reactor.core.publisher.Mono;

public abstract class SaldoRepositoryImpl {
  
  protected final SaldoRespository saldoRepo;

  public SaldoRepositoryImpl(SaldoRespository saldoRepo) {
    super();
    this.saldoRepo = saldoRepo;
  }
  
  public abstract Mono<Saldo> save(Saldo primerSaldo);
  

}
