package com.bestbank.productos.application.services;

import org.springframework.stereotype.Service;

import com.bestbank.productos.domain.model.Saldo;
import com.bestbank.productos.domain.repository.SaldoRespository;
import com.bestbank.productos.infrastructure.persistence.SaldoRepositoryImpl;

import reactor.core.publisher.Mono;

@Service
public class SaldosService extends SaldoRepositoryImpl{

  public SaldosService(SaldoRespository saldoRepo) {
    super(saldoRepo);
  }

  @Override
  public Mono<Saldo> save(Saldo primerSaldo) {
    return saldoRepo.save(primerSaldo);
  }

}
