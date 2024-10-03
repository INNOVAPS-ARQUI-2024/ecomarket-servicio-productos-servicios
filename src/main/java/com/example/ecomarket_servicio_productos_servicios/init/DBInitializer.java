package com.example.ecomarket_servicio_productos_servicios.init;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ecomarket_servicio_productos_servicios.model.Producto;
import com.example.ecomarket_servicio_productos_servicios.model.Servicio;
import com.example.ecomarket_servicio_productos_servicios.repository.ProductoRepository;
import com.example.ecomarket_servicio_productos_servicios.repository.ServicioRepository;

@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public void run(String... args) throws Exception {
        
        // Inicializar Productos
        if (productoRepository.count() == 0) {
            Producto producto1 = new Producto();
            producto1.setName("Producto 1");
            producto1.setDescription("Descripción del producto 1");
            producto1.setPrice(50.25);
            producto1.setCurrency("USD");
            producto1.setCategory("Categoría 1");
            producto1.setStock(100);
            producto1.setSellerId("user1");
            producto1.setReviews(Arrays.asList("review1"));
            producto1.setCreatedAt(new Date());

            Producto producto2 = new Producto();
            producto2.setName("Producto 2");
            producto2.setDescription("Descripción del producto 2");
            producto2.setPrice(75.00);
            producto2.setCurrency("USD");
            producto2.setCategory("Categoría 2");
            producto2.setStock(150);
            producto2.setSellerId("user2");
            producto2.setReviews(Arrays.asList("review2"));
            producto2.setCreatedAt(new Date());

            productoRepository.save(producto1);
            productoRepository.save(producto2);
        }

        // Inicializar Servicios
        if (servicioRepository.count() == 0) {
            Servicio servicio1 = new Servicio();
            servicio1.setName("Servicio 1");
            servicio1.setDescription("Descripción del servicio 1");
            servicio1.setPrice(100.00);
            servicio1.setCategory("Categoría A");
            servicio1.setProviderId("user1");
            servicio1.setAvailability("Disponible");
            servicio1.setReviews(Arrays.asList("review1"));
            servicio1.setCreatedAt(new Date());
            servicio1.setUpdatedAt(new Date());

            Servicio servicio2 = new Servicio();
            servicio2.setName("Servicio 2");
            servicio2.setDescription("Descripción del servicio 2");
            servicio2.setPrice(200.00);
            servicio2.setCategory("Categoría B");
            servicio2.setProviderId("user2");
            servicio2.setAvailability("No disponible");
            servicio2.setReviews(Arrays.asList("review2"));
            servicio2.setCreatedAt(new Date());
            servicio2.setUpdatedAt(new Date());

            servicioRepository.save(servicio1);
            servicioRepository.save(servicio2);
        }
        System.out.println("Datos iniciales cargados en la base de datos.");
    }
}
