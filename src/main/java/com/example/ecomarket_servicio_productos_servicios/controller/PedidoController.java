package com.example.ecomarket_servicio_productos_servicios.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecomarket_servicio_productos_servicios.model.Pedido;
import com.example.ecomarket_servicio_productos_servicios.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> obtenerPedidos() {
        return pedidoService.obtenerPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable String id) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.guardarPedido(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable String id, @RequestBody Pedido detallesPedido) {
        Pedido pedidoActualizado = pedidoService.actualizarPedido(id, detallesPedido);
        return pedidoActualizado != null ? ResponseEntity.ok(pedidoActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable String id) {
        boolean fueEliminado = pedidoService.eliminarPedido(id);
        return fueEliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
