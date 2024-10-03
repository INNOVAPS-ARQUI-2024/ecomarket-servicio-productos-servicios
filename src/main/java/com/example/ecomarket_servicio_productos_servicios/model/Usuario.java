package com.example.ecomarket_servicio_productos_servicios.model;


import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Usuario {
    private String userId;
    private String name;
    private String email;
    private String role;  // "Comprador" o "Vendedor"
    private String profilePicture;  // Imagen de perfil opcional
    private String phone;
    private Date createdAt;
    private Date updatedAt;
    private boolean isActive;
    private List<String> tiposVendedor;  // Ejemplo: ["producto", "servicio", "evento"]
}
