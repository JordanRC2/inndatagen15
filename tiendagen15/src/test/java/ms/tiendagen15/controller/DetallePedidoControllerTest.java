package ms.tiendagen15.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ms.tiendagen15.entity.DetallePedido;
import ms.tiendagen15.service.IDetallePedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DetallePedidoController.class)
class DetallePedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDetallePedidoService detalleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAll() throws Exception {
        DetallePedido d1 = new DetallePedido();
        d1.setId(1);
        DetallePedido d2 = new DetallePedido();
        d2.setId(2);

        Mockito.when(detalleService.readAll()).thenReturn(List.of(d1, d2));

        mockMvc.perform(get("/api/detalles-pedido"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetByIdFound() throws Exception {
        DetallePedido detalle = new DetallePedido();
        detalle.setId(1);

        Mockito.when(detalleService.readById(1)).thenReturn(Optional.of(detalle));

        mockMvc.perform(get("/api/detalles-pedido/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        Mockito.when(detalleService.readById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/detalles-pedido/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreate() throws Exception {
        DetallePedido detalle = new DetallePedido();
        detalle.setId(1);

        Mockito.when(detalleService.create(any())).thenReturn(1);

        mockMvc.perform(post("/api/detalles-pedido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detalle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testUpdateFound() throws Exception {
        DetallePedido detalle = new DetallePedido();
        detalle.setId(1);

        Mockito.when(detalleService.readById(1)).thenReturn(Optional.of(detalle));
        Mockito.when(detalleService.update(any())).thenReturn(detalle);

        mockMvc.perform(put("/api/detalles-pedido/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detalle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testUpdateNotFound() throws Exception {
        DetallePedido detalle = new DetallePedido();
        detalle.setId(99);

        Mockito.when(detalleService.readById(99)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/detalles-pedido/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detalle)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDelete() throws Exception {
        Mockito.when(detalleService.deleteById(1)).thenReturn("Detalle eliminado.");

        mockMvc.perform(delete("/api/detalles-pedido/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Detalle eliminado."));
    }

    @Test
    void testCalcularTotal() throws Exception {
        Mockito.when(detalleService.calcularTotalPorDetalle(1)).thenReturn(50.0);

        mockMvc.perform(get("/api/detalles-pedido/total/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("50.0"));
    }
}
