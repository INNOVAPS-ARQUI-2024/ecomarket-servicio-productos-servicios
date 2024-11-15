package com.example.ecomarket_servicio_productos_servicios.controller;

import com.example.ecomarket_servicio_productos_servicios.entity.Producto;
import com.example.ecomarket_servicio_productos_servicios.service.ProductoService;
import com.example.ecomarket_servicio_productos_servicios.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Test
    public void testObtenerProductos() throws Exception {
        Mockito.when(productoService.obtenerProductos()).thenReturn(TestUtils.mockProductos());

        RequestBuilder request = MockMvcRequestBuilders.get("/api/productos")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testObtenerProductoPorId() throws Exception {
        Mockito.when(productoService.obtenerProductoPorId(eq("123"))).thenReturn(TestUtils.mockProducto());

        RequestBuilder request = MockMvcRequestBuilders.get("/api/productos/123")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCrearProducto() throws Exception {
        MockMultipartFile picture = new MockMultipartFile("picture", "image.jpg", "image/jpeg", "sample image content".getBytes());
        Mockito.when(productoService.guardarProducto(any(Producto.class), any())).thenReturn(TestUtils.mockProducto());

        RequestBuilder request = MockMvcRequestBuilders.multipart("/api/productos")
                .file(picture)
                .param("name", "Producto 1")
                .param("description", "Descripción del producto")
                .param("price", "99.99")
                .param("currency", "USD")
                .param("category", "Tecnología")
                .param("stock", "10")
                .param("sellerId", "seller123")
                .contentType(MediaType.MULTIPART_FORM_DATA);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Producto 1"));
    }

    @Test
    public void testActualizarProducto() throws Exception {
        MockMultipartFile picture = new MockMultipartFile("picture", "image.jpg", "image/jpeg", "updated image content".getBytes());
        Mockito.when(productoService.actualizarProducto(eq("123"), any(Producto.class), any())).thenReturn(TestUtils.mockProducto());

        RequestBuilder request = MockMvcRequestBuilders.multipart("/api/productos/123")
                .file(picture)
                .param("name", "Producto actualizado")
                .param("description", "Descripción actualizada")
                .param("price", "150.00")
                .param("currency", "USD")
                .param("category", "Hogar")
                .param("stock", "20")
                .with(requestPostProcessor -> {
                    requestPostProcessor.setMethod("PUT");
                    return requestPostProcessor;
                })
                .contentType(MediaType.MULTIPART_FORM_DATA);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEliminarProducto() throws Exception {
        Mockito.when(productoService.eliminarProducto(eq("123"))).thenReturn(true);

        RequestBuilder request = MockMvcRequestBuilders.delete("/api/productos/123")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testObtenerProductosPorUsuario() throws Exception {
        String sellerId = "user1";
        Mockito.when(productoService.obtenerProductosPorUsuario(eq(sellerId))).thenReturn(TestUtils.mockProductos());

        RequestBuilder request = MockMvcRequestBuilders.get("/api/productos/usuario/{sellerId}", sellerId)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testObtenerProductosPorCategoria() throws Exception {
        String categoria = "Tecnología";
        Mockito.when(productoService.getProductosPorCategoria(eq(categoria))).thenReturn(TestUtils.mockProductos());

        RequestBuilder request = MockMvcRequestBuilders.get("/api/productos/categoria/{categoria}", categoria)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testObtenerProductosMasVendidos() throws Exception {
        Mockito.when(productoService.getProductosMasVendidos()).thenReturn(TestUtils.mockProductos());

        RequestBuilder request = MockMvcRequestBuilders.get("/api/productos/mas-vendidos")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
