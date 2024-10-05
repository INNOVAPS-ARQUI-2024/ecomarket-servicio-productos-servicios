package com.example.ecomarket_servicio_productos_servicios.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "eventos")
public class Evento {
    @Id
    private String eventoId;
    private String nombre;
    private String lugar;
    private String descripcion;
    private List<Long> rangoPrecios; // Cambiado a lista para rango de precios [min, max]
    private Date fechaHora;
    private String userId;
}
