package com.example.ecomarket_servicio_productos_servicios.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "notificaciones")
public class Notificacion {

    @Id
    private String notificationId; // ObjectId in MongoDB

    private String userId; // Reference to the user

    private String message;

    private String type;

    private Date date; // Using Date type to store date
}
