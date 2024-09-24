package com.example.ecomarket_servicio_productos_servicios.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecomarket_servicio_productos_servicios.model.Transaccion;

public interface TransaccionesRepository extends MongoRepository<Transaccion, String> {

}