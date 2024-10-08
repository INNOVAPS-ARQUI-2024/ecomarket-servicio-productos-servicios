package com.example.ecomarket_servicio_productos_servicios.service;


import com.example.ecomarket_servicio_productos_servicios.entity.Servicio;
import com.example.ecomarket_servicio_productos_servicios.repository.ServicioRepository;
import com.example.ecomarket_servicio_productos_servicios.utils.TestUtils;
import jakarta.ws.rs.core.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootApplication
@ContextConfiguration(classes = Application.class)
public class ServicioServiceTest {

    @Mock
    private ServicioRepository servicioRepository;
    @InjectMocks
    private ServicioService servicioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGivenRepositoryLoadedWithServiciosWhenObtenerServiciosIsCalledThenReturnAllServicios() {
        // Arrange
        Mockito.when(servicioRepository.findAll()).thenReturn(TestUtils.mockServicios());

        // Act
        List<Servicio> response = servicioService.obtenerServicios();

        // Assert
        assertNotNull(response);
        assertEquals(2, response.size());
        verify(servicioRepository, times(1)).findAll();
    }

    @Test
    public void testGivenValidIDWhenObtenerServiciosPorIDIsCalledThenReturnServicio(){
        //Arrange
        Mockito.when(servicioRepository.findById(eq("123"))).thenReturn(Optional.ofNullable(TestUtils.mockServicio()));

        //Act
        Servicio response = servicioService.obtenerServicioPorId("123");

        //Assert
        assertNotNull(response);
        verify(servicioRepository, times(1)).findById(eq("123"));
        assertEquals("123", response.getServiceId());
    }

    @Test
    public void testGivenNonValidIDWhenObtenerServiciosPorIDIsCalledThenReturnNull(){
        //Arrange
        Mockito.when(servicioRepository.findById(eq("123"))).thenReturn(Optional.ofNullable(TestUtils.mockServicio()));
        //Act
        Servicio response = servicioService.obtenerServicioPorId("1234");

        //Assert
        assertNull(response);
        verify(servicioRepository, times(1)).findById(eq("1234"));
    }

    @Test
    public void testGivenValidServicioObjectWhenGuardarServicioIsCallThenReturnServicio(){
        //Arrange
        Mockito.when(servicioRepository.save(any(Servicio.class))).thenReturn(TestUtils.mockServicio());

        //Act
        Servicio response = servicioService.guardarServicio(TestUtils.mockServicio());

        //Assert
        assertNotNull(response);
        verify(servicioRepository, times(1)).save(any(Servicio.class));

    }

    @Test
    public void testGivenValidServicioObjectWhenModificarServicioIsCallThenReturnUpdateServicio(){
        //Arrange
        String servicioID = "123";
        Servicio servicioExiste = TestUtils.mockServicio();
        Servicio updateServicio = TestUtils.mockServicio();

        Mockito.when(servicioRepository.findById(eq(servicioID))).thenReturn(Optional.ofNullable(servicioExiste));
        Mockito.when(servicioRepository.save(any(Servicio.class))).thenReturn(updateServicio);

        //Act
        Servicio response = servicioService.actualizarServicio(servicioID, updateServicio);

        //Assert
        assertNotNull(response);
        assertEquals(updateServicio.getServiceId(), response.getServiceId());
        verify(servicioRepository, times(1)).findById(eq(servicioID));
        verify(servicioRepository, times(1)).save(any(Servicio.class));
    }

    @Test
    public void testGivenNonExistentServicioWhenActualizarServicioIsCalledThenThrowException() {
        // Arrange
        String id = "1234";
        Servicio updateServicio = TestUtils.mockServicio();
        when(servicioRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            servicioService.actualizarServicio(id, updateServicio);
        });

        verify(servicioRepository, times(1)).findById(id);
        verify(servicioRepository, never()).save(any(Servicio.class));
    }

    @Test
    public void testGivenExistingServicioWhenActualizarServicioIsCalledThenUpdateAndReturnServicio() {
        // Arrange
        String id = "123";
        Servicio existingServicio = TestUtils.mockServicio();
        Servicio updateServicio = TestUtils.mockServicio();
        updateServicio.setName("Updated Name");

        when(servicioRepository.findById(id)).thenReturn(Optional.of(existingServicio));
        when(servicioRepository.save(any(Servicio.class))).thenReturn(updateServicio);

        // Act
        Servicio response = servicioService.actualizarServicio(id, updateServicio);

        // Assert
        assertNotNull(response);
        assertEquals("Updated Name", response.getName());
        verify(servicioRepository, times(1)).findById(id);
        verify(servicioRepository, times(1)).save(any(Servicio.class));
    }

    @Test
    public void testGivenExistingServicioWhenEliminarServicioIsCallThenReturnTrue(){
        //Arrange

        Mockito.when(servicioRepository.existsById(eq("123"))).thenReturn(true);
        Mockito.doNothing().when(servicioRepository).deleteById(eq("123"));

        //Act
        boolean response = servicioService.eliminarServicio("123");

        //Assert
        assertTrue(response);
        verify(servicioRepository, times(1)).existsById(eq("123"));
        verify(servicioRepository, times(1)).deleteById(eq("123"));

    }
}




