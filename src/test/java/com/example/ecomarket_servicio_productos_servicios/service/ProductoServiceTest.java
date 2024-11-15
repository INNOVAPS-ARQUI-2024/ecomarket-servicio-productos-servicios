package com.example.ecomarket_servicio_productos_servicios.service;

import com.example.ecomarket_servicio_productos_servicios.entity.Producto;
import com.example.ecomarket_servicio_productos_servicios.repository.ProductoRepository;
import com.example.ecomarket_servicio_productos_servicios.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
    public void testObtenerProductos() {
        // Arrange
        List<Producto> productosMock = TestUtils.mockProductos(); // Suponemos que devuelve una lista mock de productos
        Mockito.when(productoRepository.findAll()).thenReturn(productosMock);

        // Act
        List<Producto> response = productoService.obtenerProductos();

        // Assert
        assertNotNull(response);
        assertEquals(2, response.size()); // Aseguramos que la lista tiene 2 productos, según el mock
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerProductoPorIdExistente() {
        // Arrange
        String productoID = "123";
        Producto productoMock = TestUtils.mockProducto(); // Suponemos que devuelve un producto mock
        Mockito.when(productoRepository.findById(eq(productoID))).thenReturn(Optional.of(productoMock));

        // Act
        Producto response = productoService.obtenerProductoPorId(productoID);

        // Assert
        assertNotNull(response);
        verify(productoRepository, times(1)).findById(eq(productoID));
    }

    @Test
    public void testObtenerProductoPorIdInexistente() {
        // Arrange
        String productoID = "123";
        Mockito.when(productoRepository.findById(eq(productoID))).thenReturn(Optional.empty());

        // Act
        Producto response = productoService.obtenerProductoPorId(productoID);

        // Assert
        assertNull(response);
        verify(productoRepository, times(1)).findById(eq(productoID));
    }



    @Test
    public void testGuardarProductoConImagen() throws IOException {
        // Arrange
        Producto mockProducto = TestUtils.mockProducto();
        MockMultipartFile mockPicture = new MockMultipartFile("file", "imagen.jpg", "image/jpeg", "data".getBytes());

        Mockito.when(productoRepository.save(any(Producto.class))).thenReturn(mockProducto);

        // Act
        Producto response = productoService.guardarProducto(mockProducto, mockPicture);

        // Assert
        assertNotNull(response);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    public void testGuardarProductoSinImagen() {
        // Arrange
        Producto mockProducto = TestUtils.mockProducto();

        Mockito.when(productoRepository.save(any(Producto.class))).thenReturn(mockProducto);

        // Act
        Producto response = productoService.guardarProducto(mockProducto, null);

        // Assert
        assertNotNull(response);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    public void testGuardarProductoConDatosInvalidos() {
        // Arrange
        Producto mockProducto = TestUtils.mockProducto();
        mockProducto.setName(null); // Campo obligatorio faltante

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            productoService.guardarProducto(mockProducto, null);
        });
        verify(productoRepository, never()).save(any(Producto.class));
    }

    @Test
    public void testActualizarProductoConImagen() throws IOException {
        // Arrange
        String productoID = "123";
        Producto productoExistente = TestUtils.mockProducto();
        Producto detallesActualizados = TestUtils.mockProducto();
        MockMultipartFile mockPicture = new MockMultipartFile("file", "imagen.jpg", "image/jpeg", "data".getBytes());

        Mockito.when(productoRepository.findById(eq(productoID))).thenReturn(Optional.of(productoExistente));
        Mockito.when(productoRepository.save(any(Producto.class))).thenReturn(detallesActualizados);

        // Act
        Producto response = productoService.actualizarProducto(productoID, detallesActualizados, mockPicture);

        // Assert
        assertNotNull(response);
        assertEquals(detallesActualizados.getProductId(), response.getProductId());
        verify(productoRepository, times(1)).findById(eq(productoID));
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    public void testActualizarProductoSinImagen() {
        // Arrange
        String productoID = "123";
        Producto productoExistente = TestUtils.mockProducto();
        Producto detallesActualizados = TestUtils.mockProducto();

        Mockito.when(productoRepository.findById(eq(productoID))).thenReturn(Optional.of(productoExistente));
        Mockito.when(productoRepository.save(any(Producto.class))).thenReturn(detallesActualizados);

        // Act
        Producto response = productoService.actualizarProducto(productoID, detallesActualizados, null);

        // Assert
        assertNotNull(response);
        verify(productoRepository, times(1)).findById(eq(productoID));
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    public void testActualizarProductoConDatosInvalidos() {
        // Arrange
        String productoID = "123";
        Producto detallesActualizados = TestUtils.mockProducto();
        detallesActualizados.setPrice(-1.0); // Precio inválido

        Mockito.when(productoRepository.findById(eq(productoID))).thenReturn(Optional.of(TestUtils.mockProducto()));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            productoService.actualizarProducto(productoID, detallesActualizados, null);
        });
        verify(productoRepository, never()).save(any(Producto.class));
    }

    @Test
    public void testActualizarProductoNoExistente() {
        // Arrange
        String productoID = "123";
        Producto detallesActualizados = TestUtils.mockProducto();

        Mockito.when(productoRepository.findById(eq(productoID))).thenReturn(Optional.empty());

        // Act
        Producto response = productoService.actualizarProducto(productoID, detallesActualizados, null);

        // Assert
        assertNull(response);
        verify(productoRepository, times(1)).findById(eq(productoID));
        verify(productoRepository, never()).save(any(Producto.class));
    }

    @Test
    public void testEliminarProductoExistente() {
        // Arrange
        String productoID = "123";
        Mockito.when(productoRepository.existsById(eq(productoID))).thenReturn(true);
        Mockito.doNothing().when(productoRepository).deleteById(eq(productoID));

        // Act
        boolean response = productoService.eliminarProducto(productoID);

        // Assert
        assertTrue(response);
        verify(productoRepository, times(1)).existsById(eq(productoID));
        verify(productoRepository, times(1)).deleteById(eq(productoID));
    }

    @Test
    public void testEliminarProductoInexistente() {
        // Arrange
        String productoID = "123";
        Mockito.when(productoRepository.existsById(eq(productoID))).thenReturn(false);

        // Act
        boolean response = productoService.eliminarProducto(productoID);

        // Assert
        assertFalse(response);
        verify(productoRepository, times(1)).existsById(eq(productoID));
        verify(productoRepository, never()).deleteById(anyString());
    }

    @Test
    public void testObtenerProductosPorUsuario() {
        // Arrange
        String sellerId = "seller123";
        Mockito.when(productoRepository.findBySellerId(eq(sellerId))).thenReturn(TestUtils.mockProductos());

        // Act
        List<Producto> response = productoService.obtenerProductosPorUsuario(sellerId);

        // Assert
        assertNotNull(response);
        assertEquals(2, response.size()); // Supongamos que TestUtils.mockProductos() devuelve 2 elementos
        verify(productoRepository, times(1)).findBySellerId(eq(sellerId));
    }

    @Test
    public void testObtenerProductosPorCategoria() {
        // Arrange
        String categoria = "Electrónica";
        Mockito.when(productoRepository.findByCategory(eq(categoria))).thenReturn(TestUtils.mockProductos());

        // Act
        List<Producto> response = productoService.getProductosPorCategoria(categoria);

        // Assert
        assertNotNull(response);
        assertEquals(2, response.size()); // Supongamos que TestUtils.mockProductos() devuelve 2 elementos
        verify(productoRepository, times(1)).findByCategory(eq(categoria));
    }

    @Test
    public void testObtenerProductosMasVendidos() {
        // Arrange
        Mockito.when(productoRepository.findTop10ByOrderBySold()).thenReturn(TestUtils.mockProductos());

        // Act
        List<Producto> response = productoService.getProductosMasVendidos();

        // Assert
        assertNotNull(response);
        assertEquals(2, response.size()); // Supongamos que TestUtils.mockProductos() devuelve 2 elementos
        verify(productoRepository, times(1)).findTop10ByOrderBySold();
    }

}
