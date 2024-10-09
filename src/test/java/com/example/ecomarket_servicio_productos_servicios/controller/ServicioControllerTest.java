package com.example.ecomarket_servicio_productos_servicios.controller;


import com.example.ecomarket_servicio_productos_servicios.entity.Producto;
import com.example.ecomarket_servicio_productos_servicios.entity.Servicio;
import com.example.ecomarket_servicio_productos_servicios.service.ServicioService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@WebMvcTest(ServicioController.class)
public class ServicioControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServicioService servicioService;

    @Test
    public void testGivenRequestForallOrdersWhenObtenerPedidosEndpointIsCalledThenReturnListOfPedidos () throws Exception {
        // Arrange
        Mockito.when(servicioService.obtenerServicios()).thenReturn(TestUtils.mockServicios());
        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/ecomarket-servicios/servicios")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testGivenValidServiceIDWhenObtenerServiciosEndpointIsCalledThenReturnListOfServicios() throws Exception {
        // Arrange
        Mockito.when(servicioService.obtenerServicioPorId(eq("123"))).thenReturn(TestUtils.mockServicio());
        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/ecomarket-servicios/servicios/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);


        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenNonValidServiceIDWhenObtenerServiciosEndpointIsCalledThenReturnNotFound() throws Exception {
        //Arrange
        Mockito.when(servicioService.obtenerServicioPorId(eq("999"))).thenReturn(TestUtils.mockServicio());
        //Act
        RequestBuilder request = MockMvcRequestBuilders.get("/ecomarket-servicios/servicios/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        //Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    public void testGivenValidServiceWhenServicioIsPostThenReturnCreated () throws Exception {
        // Arrange
        Mockito.when(servicioService.guardarServicio(any())).thenReturn(TestUtils.mockServicio());
        String json = TestUtils.asJsonString(TestUtils.mockProducto());
        // Act
        RequestBuilder request = MockMvcRequestBuilders.post("/ecomarket-servicios/servicio")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        // Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void testGivenValidServiceWhenServicioIsPutThenReturnOk() throws Exception {
        //Arrange
        Mockito.when(servicioService.actualizarServicio(eq("123"), any(Servicio.class))).thenReturn(TestUtils.mockServicio());
        String json = TestUtils.asJsonString(TestUtils.mockProducto());

        //Act
        RequestBuilder request = MockMvcRequestBuilders.put("/ecomarket-servicios/servicios/123")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        //Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testGivenNonValidServiceWhenServicioIsPutThenReturnNotFound() throws Exception {
        //Arrange
        Mockito.when(servicioService.actualizarServicio(eq("123"), any(Servicio.class))).thenReturn(null);
        String json = TestUtils.asJsonString(TestUtils.mockProducto());

        //Act
        RequestBuilder request = MockMvcRequestBuilders.put("/ecomarket-servicios/servicios/123")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);

        //Act & Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGivenValidProductIDWhenEliminarProductoEndpointIsCalledThenReturnOK () throws Exception {
        // Arrange
        Mockito.when(servicioService.eliminarServicio(eq("123"))).thenReturn(true);
        // Act
        RequestBuilder request = MockMvcRequestBuilders.delete("/ecomarket-servicios/servicios/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);


        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGivenNonValidProductoIDWhenEliminarProductoEndpointIsCalledThenReturnNotFound () throws Exception {
        // Arrange
        Mockito.when(servicioService.eliminarServicio(eq("123"))).thenReturn(false);
        // Act
        RequestBuilder request = MockMvcRequestBuilders.delete("/ecomarket-pagos/pedido/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8);


        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }



}
