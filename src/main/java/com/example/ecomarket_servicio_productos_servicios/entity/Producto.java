package com.example.ecomarket_servicio_productos_servicios.entity;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "productos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    private String productId;
    private String name;
    private String description;
    private double price;
    private String currency;
    private String category;
    private int stock;
    private String sellerId; // userId
    private List<String> reviews; // array de reviewIds
    private Date createdAt; // fecha de creación
    private byte[] picture; // Campo para almacenar la imagen
    private long sold; // Número de unidades vendidas
    
}
