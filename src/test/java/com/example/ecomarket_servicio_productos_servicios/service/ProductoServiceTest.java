package com.example.ecomarket_servicio_productos_servicios.service;


import com.example.ecomarket_servicio_productos_servicios.entity.Producto;
import com.example.ecomarket_servicio_productos_servicios.repository.ProductoRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootApplication
@ContextConfiguration(classes = Application.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;
    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGivenRepositoryLoadedWithProductosWhenObtenerProductosIsCalledThenReturnAllProductos() {
        // Arrange
        Mockito.when(productoRepository.findAll()).thenReturn(TestUtils.mockProductos());

        // Act
        List<Producto> response = productoService.obtenerProductos();

        // Assert
        assertNotNull(response);
        assertEquals(2, response.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    public void testGivenValidIDWhenObtenerProductosPorIDIsCalledThenReturnProducto(){
        //Arrange
        Mockito.when(productoRepository.findById(eq("123"))).thenReturn(Optional.ofNullable(TestUtils.mockProducto()));

        //Act
        Producto response = productoService.obtenerProductoPorId("123");

        //Assert
        assertNotNull(response);
        verify(productoRepository, times(1)).findById(eq("123"));
        assertEquals("123", response.getProductId());
    }

    @Test
    public void testGivenNonValidIDWhenObtenerProductosPorIDIsCalledThenReturnNull(){
        //Arrange
        Mockito.when(productoRepository.findById(eq("123"))).thenReturn(Optional.ofNullable(TestUtils.mockProducto()));

        //Act
        Producto response = productoService.obtenerProductoPorId("1234");

        //Assert
        assertNull(response);
        verify(productoRepository, times(1)).findById(eq("1234"));
    }

    @Test
    public void testGivenValidProductoObjectWhenGuardarProductoIsCallThenReturnProducto(){
        //Arrange
        Mockito.when(productoRepository.save(any(Producto.class))).thenReturn(TestUtils.mockProducto());

        //Act
        Producto response = productoService.guardarProducto(TestUtils.mockProducto());

        //Assert
        assertNotNull(response);
        verify(productoRepository, times(1)).save(any(Producto.class));

    }

    @Test
    public void testGivenValidProductoObjectWhenModificarProductoIsCallThenReturnUpdateProducto(){
        //Arrange
        String productoID = "123";
        Producto productoExiste = TestUtils.mockProducto();
        Producto updateProducto = TestUtils.mockProducto();

        Mockito.when(productoRepository.findById(eq(productoID))).thenReturn(Optional.ofNullable(productoExiste));
        Mockito.when(productoRepository.save(any(Producto.class))).thenReturn(updateProducto);

        //Act
        Producto response = productoService.actualizarProducto(productoID, updateProducto);

        //Assert
        assertNotNull(response);
        assertEquals(updateProducto.getProductId(), response.getProductId());
        verify(productoRepository, times(1)).findById(eq(productoID));
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    public void testGivenNonValidProductoObjectWhenModificarProductoIsCallThenReturnNull(){
        //Arrange
        Producto updatePedido = TestUtils.mockProducto();

        Mockito.when(productoRepository.findById(eq("123"))).thenReturn(Optional.ofNullable(TestUtils.mockProducto()));

        //Act
        Producto response = productoService.actualizarProducto("1234", updatePedido);

        //Assert
        assertNull(response);
        verify(productoRepository, times(1)).findById(eq("1234"));
        verify(productoRepository, never()).save(any(Producto.class));

    }

    @Test
    public void testGivenExistingProductoWhenEliminarProductoIsCallThenReturnTrue(){
        //Arrange

        Mockito.when(productoRepository.existsById(eq("123"))).thenReturn(true);
        Mockito.doNothing().when(productoRepository).deleteById(eq("123"));

        //Act
        boolean response = productoService.eliminarProducto("123");

        //Assert
        assertTrue(response);
        verify(productoRepository, times(1)).existsById(eq("123"));
        verify(productoRepository, times(1)).deleteById(eq("123"));

    }

    @Test
    public void estGivenRepositoryLoadedWithUsuariosWhenObtenerProductosporUsuarioIsCalledThenReturnAllProductos(){
        // Arrange
        Mockito.when(productoRepository.findBySellerId(anyString())).thenReturn(TestUtils.mockProductos());

        // Act
        List<Producto> response = productoService.obtenerProductosPorUsuario(anyString());

        // Assert
        assertNotNull(response);
        assertEquals(2, response.size());
        verify(productoRepository, times(1)).findBySellerId(anyString());
    }

}
