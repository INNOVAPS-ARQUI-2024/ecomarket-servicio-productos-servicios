package com.example.ecomarket_servicio_productos_servicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomarket_servicio_productos_servicios.model.Transaccion;
import com.example.ecomarket_servicio_productos_servicios.repository.TransaccionesRepository;

@Service
public class TransaccionesService {

    @Autowired
    private TransaccionesRepository transaccionesRepository;

    public List<Transaccion> obtenerTransacciones() {
        return transaccionesRepository.findAll();
    }

    public Transaccion obtenerTransaccionPorId(String id) {
        return transaccionesRepository.findById(id).orElse(null);
    }

    public Transaccion guardarTransaccion(Transaccion transaccion) {
        return transaccionesRepository.save(transaccion);
    }

    public Transaccion actualizarTransaccion(String id, Transaccion detallesTransaccion) {
        Transaccion transaccion = transaccionesRepository.findById(id).orElse(null);
        if (transaccion != null) {
            transaccion.setAmount(detallesTransaccion.getAmount());
            transaccion.setCurrency(detallesTransaccion.getCurrency());
            transaccion.setStatus(detallesTransaccion.getStatus());
            transaccion.setDescription(detallesTransaccion.getDescription());
            return transaccionesRepository.save(transaccion);
        } else {
            return null;
        }
    }

    public boolean eliminarTransaccion(String id) {
        if (transaccionesRepository.existsById(id)) {
            transaccionesRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}