package com.example.ecomarket_servicio_productos_servicios.entity;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "servicios")
@Builder
public class Servicio {

    @Id
    private String serviceId;
    private String name;
    private String description;
    private double price;
    private String category;
    private String providerId;
    private String availability;
    private List<String> reviews;
    private Date createdAt;
    private Date updatedAt;
}
