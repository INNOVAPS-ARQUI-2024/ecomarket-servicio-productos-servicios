package com.example.ecomarket_servicio_productos_servicios.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomarket_servicio_productos_servicios.model.Resena;
import com.example.ecomarket_servicio_productos_servicios.repository.ResenaRepository;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public List<Resena> obtenerReseñas() {
        return resenaRepository.findAll();
    }

    public Resena obtenerReseñaPorId(String id) {
        return resenaRepository.findById(id).orElse(null);
    }

    public Resena guardarReseña(Resena resena) {
        return resenaRepository.save(resena);
    }

    public Resena actualizarReseña(String id, Resena detallesReseña) {
        Resena resena = resenaRepository.findById(id).orElse(null);
        if (resena != null) {
            resena.setItemId(detallesReseña.getItemId());
            resena.setItemType(detallesReseña.getItemType());
            resena.setUserId(detallesReseña.getUserId());
            resena.setRating(detallesReseña.getRating());
            resena.setComment(detallesReseña.getComment());
            resena.setUpdatedAt(new Date());
            return resenaRepository.save(resena);
        }
        return null;
    }

    public boolean eliminarReseña(String id) {
        if (resenaRepository.existsById(id)) {
            resenaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
