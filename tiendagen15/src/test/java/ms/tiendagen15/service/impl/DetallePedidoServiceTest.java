package ms.tiendagen15.service.impl;

import ms.tiendagen15.entity.DetallePedido;
import ms.tiendagen15.entity.Productos;
import ms.tiendagen15.repository.DetallePedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DetallePedidoServiceTest {

    @InjectMocks
    private DetallePedidoService detallePedidoService;

    @Mock
    private DetallePedidoRepository detallePedidoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadAll() {
        List<DetallePedido> lista = List.of(new DetallePedido(), new DetallePedido());
        when(detallePedidoRepository.findAll()).thenReturn(lista);

        List<DetallePedido> resultado = detallePedidoService.readAll();
        assertEquals(2, resultado.size());
    }

    @Test
    void testReadByIdFound() {
        DetallePedido detalle = new DetallePedido();
        detalle.setId(1);
        when(detallePedidoRepository.findById(1)).thenReturn(Optional.of(detalle));

        Optional<DetallePedido> resultado = detallePedidoService.readById(1);
        assertTrue(resultado.isPresent());
        assertEquals(1, resultado.get().getId());
    }

    @Test
    void testCreate() {
        DetallePedido detalle = new DetallePedido();
        detalle.setId(1);

        when(detallePedidoRepository.save(detalle)).thenReturn(detalle);
        Integer id = detallePedidoService.create(detalle);

        assertEquals(1, id);
    }

    @Test
    void testUpdate() {
        DetallePedido detalle = new DetallePedido();
        detalle.setId(1);

        when(detallePedidoRepository.save(detalle)).thenReturn(detalle);
        DetallePedido resultado = detallePedidoService.update(detalle);

        assertEquals(detalle, resultado);
    }

    @Test
    void testDeleteByIdExists() {
        DetallePedido detalle = new DetallePedido();
        detalle.setId(1);
        when(detallePedidoRepository.findById(1)).thenReturn(Optional.of(detalle));

        String resultado = detallePedidoService.deleteById(1);

        verify(detallePedidoRepository).deleteById(1);
        assertEquals("Detalle eliminado.", resultado);
    }

    @Test
    void testDeleteByIdNotFound() {
        when(detallePedidoRepository.findById(2)).thenReturn(Optional.empty());

        String resultado = detallePedidoService.deleteById(2);

        verify(detallePedidoRepository, never()).deleteById(anyInt());
        assertEquals("Detalle no encontrado.", resultado);
    }

    @Test
    void testCalcularTotalPorDetalle() {
        Productos producto = new Productos();
        producto.setPrecio(10.0);

        DetallePedido detalle = mock(DetallePedido.class);
        when(detalle.getCantidad()).thenReturn(3.0);
        when(detalle.getProductos()).thenReturn(producto);

        when(detallePedidoRepository.findById(1)).thenReturn(Optional.of(detalle));

        Double total = detallePedidoService.calcularTotalPorDetalle(1);
        assertEquals(30.0, total);
    }

    @Test
    void testCalcularTotalPorDetalleNotFound() {
        when(detallePedidoRepository.findById(99)).thenReturn(Optional.empty());

        Double total = detallePedidoService.calcularTotalPorDetalle(99);
        assertEquals(0.0, total);
    }
}
