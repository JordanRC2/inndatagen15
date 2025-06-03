package ms.tiendagen15.service.impl;

import ms.tiendagen15.feign.DistribuidorClient;
import ms.tiendagen15.model.Distribuidor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DistribuidorServiceTest {

    @Mock
    private DistribuidorClient distribuidorClient;

    @InjectMocks
    private DistribuidorService distribuidorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadAll() {
        // Arrange
        Distribuidor d1 = new Distribuidor(1, 20.0, "Empresa A", "Juan");
        Distribuidor d2 = new Distribuidor(2, 35.5, "Empresa B", "Ana");
        List<Distribuidor> lista = Arrays.asList(d1, d2);

        when(distribuidorClient.getData()).thenReturn(lista);

        // Act
        List<Distribuidor> result = distribuidorService.readAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Empresa A", result.get(0).getEmpresa());
        verify(distribuidorClient, times(1)).getData();
    }

    @Test
    void testReadById() {
        Distribuidor esperado = new Distribuidor(1, 22.0, "Empresa Z", "Carlos");

        when(distribuidorClient.getById(1)).thenReturn(esperado);

        Distribuidor actual = distribuidorService.readById(1);

        assertNotNull(actual);
        assertEquals("Empresa Z", actual.getEmpresa());
    }

    @Test
    void testCreate() {
        Distribuidor nuevo = new Distribuidor(null, 15.0, "Nueva SA", "Luz");
        Distribuidor creado = new Distribuidor(3, 15.0, "Nueva SA", "Luz");

        when(distribuidorClient.create(nuevo)).thenReturn(creado);

        Distribuidor result = distribuidorService.create(nuevo);

        assertNotNull(result.getId());
        assertEquals("Nueva SA", result.getEmpresa());
    }

    @Test
    void testUpdate() {
        Distribuidor actualizado = new Distribuidor(1, 28.0, "Empresa Actualizada", "Luis");

        when(distribuidorClient.update(1, actualizado)).thenReturn(actualizado);

        Distribuidor result = distribuidorService.update(1, actualizado);

        assertEquals("Empresa Actualizada", result.getEmpresa());
        assertEquals(28.0, result.getCosto());
    }

    @Test
    void testDelete() {
        doNothing().when(distribuidorClient).delete(1);

        distribuidorService.delete(1);

        verify(distribuidorClient, times(1)).delete(1);
    }
}
