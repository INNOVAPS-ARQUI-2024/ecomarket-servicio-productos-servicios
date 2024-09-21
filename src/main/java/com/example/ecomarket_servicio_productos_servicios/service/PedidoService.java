package com.example.ecomarket_servicio_productos_servicios.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomarket_servicio_productos_servicios.model.Pedido;
import com.example.ecomarket_servicio_productos_servicios.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPedidoPorId(String id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarPedido(String id, Pedido detallesPedido) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido != null) {
            pedido.setUserId(detallesPedido.getUserId());
            pedido.setItems(detallesPedido.getItems());
            pedido.setTotalAmount(detallesPedido.getTotalAmount());
            pedido.setCurrency(detallesPedido.getCurrency());
            pedido.setStatus(detallesPedido.getStatus());
            pedido.setPaymentMethodId(detallesPedido.getPaymentMethodId());
            pedido.setUpdatedAt(new Date());
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    public boolean eliminarPedido(String id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
