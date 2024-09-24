package com.example.ecomarket_servicio_productos_servicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomarket_servicio_productos_servicios.model.Servicio;
import com.example.ecomarket_servicio_productos_servicios.repository.ServicioRepository;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> obtenerServicios() {
        return servicioRepository.findAll();
    }

    public Servicio obtenerServicioPorId(String id) {
        return servicioRepository.findById(id).orElse(null);
    }

    public Servicio guardarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public Servicio actualizarServicio(String id, Servicio detallesServicio) {
        Servicio servicio = servicioRepository.findById(id).orElse(null);
        if (servicio != null) {
            servicio.setName(detallesServicio.getName());
            servicio.setDescription(detallesServicio.getDescription());
            servicio.setPrice(detallesServicio.getPrice());
            servicio.setCategory(detallesServicio.getCategory());
            servicio.setAvailability(detallesServicio.getAvailability());
            servicio.setReviews(detallesServicio.getReviews());
            return servicioRepository.save(servicio);
        } else {
            return null;
        }
    }

    public boolean eliminarServicio(String id) {
        if (servicioRepository.existsById(id)) {
            servicioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}