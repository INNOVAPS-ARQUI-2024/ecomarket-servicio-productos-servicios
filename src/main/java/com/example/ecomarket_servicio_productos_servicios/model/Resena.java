package com.example.ecomarket_servicio_productos_servicios.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "reseñas")
public class Resena {

    @Id
    private String reviewId;
    private String itemId;  // productId/serviceId
    private String itemType; // tipo de ítem
    private String userId;
    private double rating;   // calificación
    private String comment;  // comentario
    private Date createdAt;  // fecha de creación
    private Date updatedAt;  // fecha de actualización
}
