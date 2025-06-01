package ms.tiendagen15.service.impl;

import ms.tiendagen15.entity.Productos;
import ms.tiendagen15.repository.ProductosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductosServiceTest {

    @Mock
    private ProductosRepository productoRepository;

    private ProductosService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productoService = new ProductosService(productoRepository);
    }

    @Test
    void testReadById_whenExists() {
        Productos producto = new Productos(5, "Gorra", 100.0, "Accesorios", 20, true);

        when(productoRepository.findById(5)).thenReturn(Optional.of(producto));

        Optional<Productos> resultado = productoService.readById(5);

        assertTrue(resultado.isPresent(), "El producto con id=5 debe estar presente");
        assertEquals(producto, resultado.get(), "El producto devuelto debe ser igual al mock");
    }

    @Test
    void testReadById_whenNotExists() {
        when(productoRepository.findById(999)).thenReturn(Optional.empty());
        Optional<Productos> resultado = productoService.readById(999);
        assertFalse(resultado.isPresent(), "No debe encontrar el producto con id=999");
    }

    @Test
    void testCreate_returnsIdOfSavedProduct() {
        Productos nuevo = new Productos(null, "Bufanda",  150.0, "Accesorios", 30, true);
        doAnswer(invocation -> {
            Productos p = invocation.getArgument(0);
            p.setIdProducto(10);
            return p;
        }).when(productoRepository).save(nuevo);

        Integer idGenerado = productoService.create(nuevo);

        assertEquals(10, idGenerado, "Debe devolver el id asignado por JPA (10)");
        verify(productoRepository, times(1)).save(nuevo);
    }

    @Test
    void testUpdate_returnsUpdatedProduct() {
        Productos existente = new Productos(7, "Chaqueta", 600.0, "Ropa",  8, true);
        when(productoRepository.save(existente)).thenReturn(existente);

        Productos resultado = productoService.update(existente);

        assertEquals(existente, resultado, "Debe devolver el producto actualizado");
        verify(productoRepository, times(1)).save(existente);
    }

    @Test
    void testDeleteById_whenExists() {
        Productos existente = new Productos(12, "Camisa",  250.0, "Ropa",  5, true);

        when(productoRepository.findById(12)).thenReturn(Optional.of(existente));
        doAnswer(inv -> {
            Productos pArg = inv.getArgument(0);
            return pArg;
        }).when(productoRepository).save(any(Productos.class));

        String mensaje = productoService.deleteById(12);

        assertEquals("Producto desactivado.", mensaje);
        assertFalse(existente.getActivo(), "El campo activo debe cambiar a false");
        verify(productoRepository, times(1)).findById(12);
        verify(productoRepository, times(1)).save(existente);
    }

    @Test
    void testDeleteById_whenNotExists() {
        when(productoRepository.findById(999)).thenReturn(Optional.empty());

        String mensaje = productoService.deleteById(999);

        assertEquals("Producto no encontrado.", mensaje);
        verify(productoRepository, times(1)).findById(999);
        verify(productoRepository, never()).save(any());
    }

    @Test
    void testFindByCategoria() {
        Productos p1 = new Productos(21, "Gorro", 120.0, "Accesorios", 10, true);
        Productos p2 = new Productos(22, "Bufanda", 150.0, "Accesorios", 30, true);

        when(productoRepository.findByCategoria("Accesorios"))
                .thenReturn(Arrays.asList(p1, p2));

        List<Productos> resultado = productoService.findByCategoria("Accesorios");

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(p1));
        assertTrue(resultado.contains(p2));
        verify(productoRepository, times(1)).findByCategoria("Accesorios");
    }

    @Test
    void testSumarStock() {
        Integer suma = productoService.sumarStock(5, 8);
        assertEquals(13, suma);
    }

    @Test
    void testBuscarProductosConPrecioMenorA() {
        Productos barato1 = new Productos(31, "Lápiz",    10.0, "Útiles", 100, true);
        Productos barato2 = new Productos(32, "Cuaderno", 15.0, "Útiles",  50, true);

        when(productoRepository.findByPrecioLessThan(20.0))
                .thenReturn(Arrays.asList(barato1, barato2));

        List<Productos> resultado = productoService.buscarProductosConPrecioMenorA(20.0);

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(barato1));
        assertTrue(resultado.contains(barato2));
        verify(productoRepository, times(1)).findByPrecioLessThan(20.0);
    }

    @Test
    void testObtenerProductosConStockMenorA5() {
        Productos bajo1 = new Productos(41, "Goma", 5.0, "Útiles",  3, true);
        Productos bajo2 = new Productos(42, "Regla",         12.0, "Útiles",  2, true);

        when(productoRepository.findProductosConStockBajo())
                .thenReturn(Arrays.asList(bajo1, bajo2));

        List<Productos> resultado = productoService.obtenerProductosConStockMenorA5();

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(bajo1));
        assertTrue(resultado.contains(bajo2));
        verify(productoRepository, times(1)).findProductosConStockBajo();
    }
}
