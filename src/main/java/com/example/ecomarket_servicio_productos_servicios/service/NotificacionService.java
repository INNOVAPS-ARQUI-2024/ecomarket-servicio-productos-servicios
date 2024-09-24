package com.example.ecomarket_servicio_productos_servicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomarket_servicio_productos_servicios.model.Notificacion;
import com.example.ecomarket_servicio_productos_servicios.repository.NotificacionRepository;


@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    // Obtener todas las notificaciones
    public List<Notificacion> obtenerNotificaciones() {
        return notificacionRepository.findAll();
    }

    // Obtener una notificación por ID
    public Notificacion obtenerNotificacionPorId(String id) {
        return notificacionRepository.findById(id).orElse(null);
    }

    // Guardar una nueva notificación
    public Notificacion guardarNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    // Actualizar una notificación existente
    public Notificacion actualizarNotificacion(String id, Notificacion detallesNotificacion) {
        Notificacion notificacion = notificacionRepository.findById(id).orElse(null);
        if (notificacion != null) {
            notificacion.setUserId(detallesNotificacion.getUserId());
            notificacion.setMessage(detallesNotificacion.getMessage());
            notificacion.setType(detallesNotificacion.getType());
            notificacion.setDate(detallesNotificacion.getDate());
            return notificacionRepository.save(notificacion);
        } else {
            return null;
        }
    }

    // Eliminar una notificación por ID
    public boolean eliminarNotificacion(String id) {
        if (notificacionRepository.existsById(id)) {
            notificacionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}