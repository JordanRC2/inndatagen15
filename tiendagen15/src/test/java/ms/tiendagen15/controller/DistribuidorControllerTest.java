package ms.tiendagen15.controller;

import ms.tiendagen15.model.Distribuidor;
import ms.tiendagen15.service.impl.DistribuidorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DistribuidorControllerTest {

    @Mock
    private DistribuidorService distribuidorService;

    @InjectMocks
    private DistribuidorController distribuidorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadAll_success() {
        List<Distribuidor> lista = Arrays.asList(
                new Distribuidor(1, 150.0, "Distribuidor A", "contactoA@empresa.com"),
                new Distribuidor(2, 200.0, "Distribuidor B", "contactoB@empresa.com")
        );
        when(distribuidorService.readAll()).thenReturn(lista);

        ResponseEntity<List<Distribuidor>> response = distribuidorController.readAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(distribuidorService, times(1)).readAll();
    }

    @Test
    void testReadAll_exception() {
        when(distribuidorService.readAll()).thenThrow(new RuntimeException("Error inesperado"));

        ResponseEntity<List<Distribuidor>> response = distribuidorController.readAll();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(distribuidorService, times(1)).readAll();
    }

    @Test
    void testReadById_exception() {
        when(distribuidorService.readById(1)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Distribuidor> response = distribuidorController.readById(1);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(distribuidorService, times(1)).readById(1);
    }

    @Test
    void testCreate_success() {
        Distribuidor d = new Distribuidor(null, 200.0, "Distribuidor Nuevo", "nuevo@empresa.com");
        Distribuidor creado = new Distribuidor(3, 200.0, "Distribuidor Nuevo", "nuevo@empresa.com");
        when(distribuidorService.create(d)).thenReturn(creado);

        ResponseEntity<Distribuidor> response = distribuidorController.create(d);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(creado, response.getBody());
        verify(distribuidorService, times(1)).create(d);
    }

    @Test
    void testCreate_exception() {
        Distribuidor d = new Distribuidor(null, 200.0, "Distribuidor Nuevo", "nuevo@empresa.com");
        when(distribuidorService.create(d)).thenThrow(new RuntimeException("Error crear"));

        ResponseEntity<Distribuidor> response = distribuidorController.create(d);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(distribuidorService, times(1)).create(d);
    }

    @Test
    void testUpdate_found() {
        Distribuidor d = new Distribuidor(null, 250.0, "Distribuidor Actualizado", "actualizado@empresa.com");
        Distribuidor actualizado = new Distribuidor(1, 250.0, "Distribuidor Actualizado", "actualizado@empresa.com");
        when(distribuidorService.update(1, d)).thenReturn(actualizado);

        ResponseEntity<Distribuidor> response = distribuidorController.update(1, d);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(actualizado, response.getBody());
        verify(distribuidorService, times(1)).update(1, d);
    }

    @Test
    void testUpdate_notFound() {
        Distribuidor d = new Distribuidor(null, 250.0, "Distribuidor Actualizado", "actualizado@empresa.com");
        when(distribuidorService.update(99, d)).thenReturn(null);

        ResponseEntity<Distribuidor> response = distribuidorController.update(99, d);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(distribuidorService, times(1)).update(99, d);
    }

    @Test
    void testUpdate_exception() {
        Distribuidor d = new Distribuidor(null, 250.0, "Distribuidor Actualizado", "actualizado@empresa.com");
        when(distribuidorService.update(1, d)).thenThrow(new RuntimeException("Error actualizar"));

        ResponseEntity<Distribuidor> response = distribuidorController.update(1, d);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(distribuidorService, times(1)).update(1, d);
    }

    @Test
    void testDelete_success() {
        doNothing().when(distribuidorService).delete(1);

        ResponseEntity<String> response = distribuidorController.delete(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Distribuidor eliminado con éxito", response.getBody());
        verify(distribuidorService, times(1)).delete(1);
    }

    @Test
    void testDelete_exception() {
        doThrow(new RuntimeException("Error eliminar")).when(distribuidorService).delete(1);

        ResponseEntity<String> response = distribuidorController.delete(1);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al eliminar distribuidor", response.getBody());
        verify(distribuidorService, times(1)).delete(1);
    }
}
