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
    public void testGivenRequestForAllProductosWhenObtenerProductoEndpointIsCalledThenReturnListOfProductos() throws Exception {
        // Arrange
        Mockito.when(productoService.obtenerProductos()).thenReturn(TestUtils.mockProductos());

        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/productos")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenValidProductoIDWhenObtenerProductoEndpointIsCalledThenReturnProducto() throws Exception {
        // Arrange
        Mockito.when(productoService.obtenerProductoPorId(eq("123"))).thenReturn(TestUtils.mockProducto());

        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/productos/123")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenNonValidProductoIDWhenObtenerProductoEndpointIsCalledThenReturnNotFound() throws Exception {
        // Arrange
        Mockito.when(productoService.obtenerProductoPorId(eq("999"))).thenReturn(null);

        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/productos/999")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGivenValidProductoWhenProductoIsPostThenReturnCreated() throws Exception {
        // Arrange
        Mockito.when(productoService.guardarProducto(any())).thenReturn(TestUtils.mockProducto());
        String json = TestUtils.asJsonString(TestUtils.mockProducto());

        // Act
        RequestBuilder request = MockMvcRequestBuilders.post("/productos")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGivenValidProductoWhenProductoIsPutThenReturnOk() throws Exception {
        // Arrange
        Mockito.when(productoService.actualizarProducto(eq("123"), any(Producto.class))).thenReturn(TestUtils.mockProducto());
        String json = TestUtils.asJsonString(TestUtils.mockProducto());

        // Act
        RequestBuilder request = MockMvcRequestBuilders.put("/productos/123")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenNonValidProductoIDWhenEliminarProductoEndpointIsCalledThenReturnNotFound() throws Exception {
        // Arrange
        Mockito.when(productoService.eliminarProducto(eq("999"))).thenReturn(false);

        // Act
        RequestBuilder request = MockMvcRequestBuilders.delete("/productos/999")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGivenProductosWhenObtenerProductosPorUsuarioIsCalledThenReturnOk() throws Exception {
        // Arrange
        String sellerId = "user1";
        List<Producto> productos = TestUtils.mockProductos();
        Mockito.when(productoService.obtenerProductosPorUsuario(sellerId)).thenReturn(productos);

        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/productos/usuario/{sellerId}", sellerId)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
