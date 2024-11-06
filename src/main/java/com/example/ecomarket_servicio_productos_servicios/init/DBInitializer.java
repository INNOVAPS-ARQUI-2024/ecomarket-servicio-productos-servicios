package com.example.ecomarket_servicio_productos_servicios.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        /*
        // Inicializar Productos
        if (productoRepository.count() == 0) {
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

            productoRepository.save(producto1);
            productoRepository.save(producto2);
        }

        // Inicializar Servicios
        if (servicioRepository.count() == 0) {
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


            servicioRepository.save(servicio1);
            servicioRepository.save(servicio2);
        }*/
        System.out.println("Datos iniciales cargados en la base de datos.");
    }
}
