package com.example.ecomarket_servicio_productos_servicios.init;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ecomarket_servicio_productos_servicios.model.Notificacion;
import com.example.ecomarket_servicio_productos_servicios.model.Pedido;
import com.example.ecomarket_servicio_productos_servicios.model.Producto;
import com.example.ecomarket_servicio_productos_servicios.model.Resena;
import com.example.ecomarket_servicio_productos_servicios.model.Servicio;
import com.example.ecomarket_servicio_productos_servicios.model.Transaccion;
import com.example.ecomarket_servicio_productos_servicios.repository.NotificacionRepository;
import com.example.ecomarket_servicio_productos_servicios.repository.PedidoRepository;
import com.example.ecomarket_servicio_productos_servicios.repository.ProductoRepository;
import com.example.ecomarket_servicio_productos_servicios.repository.ResenaRepository;
import com.example.ecomarket_servicio_productos_servicios.repository.ServicioRepository;
import com.example.ecomarket_servicio_productos_servicios.repository.TransaccionesRepository;

@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private TransaccionesRepository transaccionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Inicializar Notificaciones
        if (notificacionRepository.count() == 0) {
            Notificacion notificacion1 = new Notificacion();
            notificacion1.setUserId("user1");
            notificacion1.setMessage("Primera notificación de prueba");
            notificacion1.setType("INFO");
            notificacion1.setDate(new Date());

            Notificacion notificacion2 = new Notificacion();
            notificacion2.setUserId("user2");
            notificacion2.setMessage("Segunda notificación de prueba");
            notificacion2.setType("WARNING");
            notificacion2.setDate(new Date());

            notificacionRepository.save(notificacion1);
            notificacionRepository.save(notificacion2);
        }

        // Inicializar Pedidos
        if (pedidoRepository.count() == 0) {
            Pedido pedido1 = new Pedido();
            pedido1.setUserId("user1");
            pedido1.setItems(Arrays.asList("prod1", "serv1"));
            pedido1.setTotalAmount(100.50);
            pedido1.setCurrency("USD");
            pedido1.setStatus("PENDING");
            pedido1.setPaymentMethodId("pm1");
            pedido1.setCreatedAt(new Date());
            pedido1.setUpdatedAt(new Date());

            Pedido pedido2 = new Pedido();
            pedido2.setUserId("user2");
            pedido2.setItems(Arrays.asList("prod2", "serv2"));
            pedido2.setTotalAmount(200.75);
            pedido2.setCurrency("EUR");
            pedido2.setStatus("COMPLETED");
            pedido2.setPaymentMethodId("pm2");
            pedido2.setCreatedAt(new Date());
            pedido2.setUpdatedAt(new Date());

            pedidoRepository.save(pedido1);
            pedidoRepository.save(pedido2);
        }

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

        // Inicializar Reseñas
        if (resenaRepository.count() == 0) {
            Resena resena1 = new Resena();
            resena1.setItemId("prod1");
            resena1.setItemType("PRODUCT");
            resena1.setUserId("user1");
            resena1.setRating(4.5);
            resena1.setComment("Resena de prueba 1");
            resena1.setCreatedAt(new Date());
            resena1.setUpdatedAt(new Date());

            Resena resena2 = new Resena();
            resena2.setItemId("serv2");
            resena2.setItemType("SERVICE");
            resena2.setUserId("user2");
            resena2.setRating(3.0);
            resena2.setComment("Resena de prueba 2");
            resena2.setCreatedAt(new Date());
            resena2.setUpdatedAt(new Date());

            resenaRepository.save(resena1);
            resenaRepository.save(resena2);
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

        // Inicializar Transacciones
        if (transaccionRepository.count() == 0) {
            Transaccion transaccion1 = new Transaccion();
            transaccion1.setBuyerId("user1");
            transaccion1.setSellerId("user2");
            transaccion1.setItemId("prod1");
            transaccion1.setItemType("PRODUCT");
            transaccion1.setAmount(100.50);
            transaccion1.setCurrency("USD");
            transaccion1.setStatus("PENDING");
            transaccion1.setPaymentMethodId("pm1");
            transaccion1.setDescription("Compra de prueba 1");
            transaccion1.setCreatedAt(new Date());
            transaccion1.setUpdatedAt(new Date());

            Transaccion transaccion2 = new Transaccion();
            transaccion2.setBuyerId("user2");
            transaccion2.setSellerId("user1");
            transaccion2.setItemId("serv2");
            transaccion2.setItemType("SERVICE");
            transaccion2.setAmount(150.75);
            transaccion2.setCurrency("EUR");
            transaccion2.setStatus("COMPLETED");
            transaccion2.setPaymentMethodId("pm2");
            transaccion2.setDescription("Compra de prueba 2");
            transaccion2.setCreatedAt(new Date());
            transaccion2.setUpdatedAt(new Date());

            transaccionRepository.save(transaccion1);
            transaccionRepository.save(transaccion2);
        }

        System.out.println("Datos iniciales cargados en la base de datos.");
    }
}
