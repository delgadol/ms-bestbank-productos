package com.bestbank.productos.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bestbank.productos.domain.model.Saldo;

public interface SaldoRespository extends ReactiveMongoRepository<Saldo,String> {

}
