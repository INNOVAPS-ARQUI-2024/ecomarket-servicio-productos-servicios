package com.example.ecomarket_servicio_productos_servicios.controller;

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

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@WebMvcTest(ServicioController.class)
public class ServicioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioService servicioService;

    @Test
    public void testGivenRequestForAllServiciosWhenObtenerServiciosEndpointIsCalledThenReturnListOfServicios() throws Exception {
        // Arrange
        List<Servicio> mockServicios = TestUtils.mockServicios();
        Mockito.when(servicioService.obtenerServicios()).thenReturn(mockServicios);

        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/api/servicios")
                .contentType(MediaType.APPLICATION_JSON);

        // Assert
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockServicios.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].serviceId").value(mockServicios.get(0).getServiceId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(mockServicios.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(mockServicios.get(0).getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category").value(mockServicios.get(0).getCategory()));

        // Verificar que el servicio fue llamado una vez
        Mockito.verify(servicioService, Mockito.times(1)).obtenerServicios();
    }

    @Test
    public void testGivenValidServiceIDWhenObtenerServicioEndpointIsCalledThenReturnServicio() throws Exception {
        // Arrange
        Mockito.when(servicioService.obtenerServicioPorId(eq("123"))).thenReturn(TestUtils.mockServicio());

        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/api/servicios/123")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenNonValidServiceIDWhenObtenerServicioEndpointIsCalledThenReturnNotFound() throws Exception {
        // Arrange
        Mockito.when(servicioService.obtenerServicioPorId(eq("999"))).thenReturn(null);

        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/api/servicios/999")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGivenValidServicioWhenServicioIsPostThenReturnCreated() throws Exception {
        // Arrange
        Mockito.when(servicioService.guardarServicio(any())).thenReturn(TestUtils.mockServicio());
        String json = TestUtils.asJsonString(TestUtils.mockServicio());

        // Act
        RequestBuilder request = MockMvcRequestBuilders.post("/api/servicios")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGivenValidServicioWhenServicioIsPutThenReturnOk() throws Exception {
        // Arrange
        Mockito.when(servicioService.actualizarServicio(eq("123"), any(Servicio.class))).thenReturn(TestUtils.mockServicio());
        String json = TestUtils.asJsonString(TestUtils.mockServicio());

        // Act
        RequestBuilder request = MockMvcRequestBuilders.put("/api/servicios/123")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenNonValidServiceIDWhenEliminarServicioEndpointIsCalledThenReturnNotFound() throws Exception {
        // Arrange
        Mockito.when(servicioService.eliminarServicio(eq("999"))).thenReturn(false);

        // Act
        RequestBuilder request = MockMvcRequestBuilders.delete("/api/servicios/999")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
