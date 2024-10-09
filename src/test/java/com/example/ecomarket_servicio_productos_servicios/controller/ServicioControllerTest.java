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
        Mockito.when(servicioService.obtenerServicios()).thenReturn(TestUtils.mockServicios());

        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/servicios")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenValidServiceIDWhenObtenerServicioEndpointIsCalledThenReturnServicio() throws Exception {
        // Arrange
        Mockito.when(servicioService.obtenerServicioPorId(eq("123"))).thenReturn(TestUtils.mockServicio());

        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/servicios/123")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGivenNonValidServiceIDWhenObtenerServicioEndpointIsCalledThenReturnNotFound() throws Exception {
        // Arrange
        Mockito.when(servicioService.obtenerServicioPorId(eq("999"))).thenReturn(null);

        // Act
        RequestBuilder request = MockMvcRequestBuilders.get("/servicios/999")
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
        RequestBuilder request = MockMvcRequestBuilders.post("/servicios")
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
        RequestBuilder request = MockMvcRequestBuilders.put("/servicios/123")
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
        RequestBuilder request = MockMvcRequestBuilders.delete("/servicios/999")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
