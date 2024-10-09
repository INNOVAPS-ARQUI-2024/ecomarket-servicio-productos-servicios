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
    public void testGivenRequestForallOrdersWhenObtenerProductoEndpointIsCalledThenReturnListOfProductos () throws Exception {
        // Arrange
        Mockito.when(productoService.obtenerProductos()).thenReturn(TestUtils.mockProductos());
        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/ecomarket-productos/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenValidProductosIDWhenObtenerProductosEndpointIsCalledThenReturnListOfProductos() throws Exception {
        // Arrange
        Mockito.when(productoService.obtenerProductoPorId(eq("123"))).thenReturn(TestUtils.mockProducto());
        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/ecomarket-productos/productos/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);


        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenNonValidProductIDWhenObtenerProductosEndpointIsCalledThenReturnNotFound() throws Exception {
        //Arrange
        Mockito.when(productoService.obtenerProductoPorId(eq("999"))).thenReturn(TestUtils.mockProducto());
        //Act
        RequestBuilder request = MockMvcRequestBuilders.get("/ecomarket-productos/productos/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        //Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGivenValidProductosWhenProductoIsPostThenReturnCreated () throws Exception {
        // Arrange
        Mockito.when(productoService.guardarProducto(any())).thenReturn(TestUtils.mockProducto());
        String json = TestUtils.asJsonString(TestUtils.mockProducto());
        // Act
        RequestBuilder request = MockMvcRequestBuilders.post("/ecomarket-productos/producto")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGivenNonValidProductosWhenProductosIsPostThenReturnBadRequest () throws Exception {
        // Arrange
        Mockito.when(productoService.guardarProducto(any())).thenThrow(new IllegalArgumentException("Producto inválido"));
        String json = TestUtils.asJsonString(TestUtils.mockProducto());
        // Act
        RequestBuilder request = MockMvcRequestBuilders.post("/ecomarket-productos/producto")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testGivenValidProductoWhenProductoIsPutThenReturnOk() throws Exception {
        //Arrange
        Mockito.when(productoService.actualizarProducto(eq("123"), any(Producto.class))).thenReturn(TestUtils.mockProducto());
        String json = TestUtils.asJsonString(TestUtils.mockProducto());

        //Act
        RequestBuilder request = MockMvcRequestBuilders.put("/ecomarket-productos/productos/123")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        //Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenNonValidProductoWhenProductoIsPutThenReturnNotFound() throws Exception {
        //Arrange
        Mockito.when(productoService.actualizarProducto(eq("123"), any(Producto.class))).thenReturn(null);
        String json = TestUtils.asJsonString(TestUtils.mockProducto());

        //Act
        RequestBuilder request = MockMvcRequestBuilders.put("/ecomarket-productos/productos/123")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        //Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGivenNonValidProductoWhenProductoIsPutThenReturnBadRequest () throws Exception {
        // Arrange
        Mockito.when(productoService.actualizarProducto(eq("123"), any(Producto.class)))
                .thenThrow(new IllegalArgumentException("Producto inválido"));
        String json = TestUtils.asJsonString(TestUtils.mockProducto());
        // Act
        RequestBuilder request = MockMvcRequestBuilders.put("/ecomarket-productos/productos/123")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testGivenValidProductIDWhenEliminarProductoEndpointIsCalledThenReturnOK () throws Exception {
        // Arrange
        Mockito.when(productoService.eliminarProducto(eq("123"))).thenReturn(true);
        // Act
        RequestBuilder request = MockMvcRequestBuilders.delete("/ecomarket-productos/productos/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);


        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGivenNonValidProductoIDWhenEliminarProductoEndpointIsCalledThenReturnNotFound () throws Exception {
        // Arrange
        Mockito.when(productoService.eliminarProducto(eq("123"))).thenReturn(false);
        // Act
        RequestBuilder request = MockMvcRequestBuilders.delete("/ecomarket-pagos/pedido/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);


        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGivenProductosWhenObtenerProductosporUsuarioIsCalledThenReturnisOK() throws Exception {
        // Arrange
        String sellerId = "user1";
        List<Producto> productos = TestUtils.mockProductos();
        Mockito.when(productoService.obtenerProductosPorUsuario(sellerId)).thenReturn(productos);

        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/ecomarket-productos/usuario/{sellerId}", sellerId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
