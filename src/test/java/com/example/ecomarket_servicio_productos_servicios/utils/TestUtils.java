package com.example.ecomarket_servicio_productos_servicios.utils;

import com.example.ecomarket_servicio_productos_servicios.entity.Producto;
import com.example.ecomarket_servicio_productos_servicios.entity.Servicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestUtils {

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Producto mockProducto(){
        return Producto.builder()
                .productId("123")
                .name("Producto 1")
                .description("Descripción del producto 1")
                .price(50.25)
                .currency("USD")
                .category("Categoría 1")
                .stock(100)
                .sellerId("user1")
                .reviews(Arrays.asList("review1"))
                .createdAt(new Date())
                .build();
    }

    public static List<Producto> mockProductos(){
        Producto producto1 = Producto.builder()
                .name("Producto 1")
                .description("Descripción del producto 1")
                .price(50.25)
                .currency("USD")
                .category("Categoría 1")
                .stock(100)
                .sellerId("user1")
                .reviews(Arrays.asList("review1"))
                .createdAt(new Date())
                .build();

        Producto producto2 = Producto.builder()
                .name("Producto 2")
                .description("Descripción del producto 2")
                .price(75.00)
                .currency("USD")
                .category("Categoría 2")
                .stock(150)
                .sellerId("user2")
                .reviews(Arrays.asList("review2"))
                .createdAt(new Date())
                .build();

        return Lists.newArrayList(producto1, producto2);
    }

    public static Servicio mockServicio(){
        return Servicio.builder()
                .serviceId("123")
                .name("Servicio 1")
                .description("Descripción del servicio 1")
                .price(100.00)
                .category("Categoría A")
                .providerId("user1")
                .availability("Disponible")
                .reviews(Arrays.asList("review1"))
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    public static List<Servicio> mockServicios(){
        Servicio servicio1 = Servicio.builder()
                .name("Servicio 1")
                .description("Descripción del servicio 1")
                .price(100.00)
                .category("Categoría A")
                .providerId("user1")
                .availability("Disponible")
                .reviews(Arrays.asList("review1"))
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();


        Servicio servicio2 = Servicio.builder()
                .name("Servicio 2")
                .description("Descripción del servicio 2")
                .price(200.00)
                .category("Categoría B")
                .providerId("user2")
                .availability("No disponible")
                .reviews(Arrays.asList("review2"))
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        return Lists.newArrayList(servicio1, servicio2);
    }

}
