package ms.tiendagen15.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ms.tiendagen15.entity.Productos;
import ms.tiendagen15.model.ProductosDTO;
import ms.tiendagen15.service.IProductosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductosController.class)
class ProductosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductosService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Productos producto;

    @BeforeEach
    void setUp() {
        producto = new Productos();
        producto.setIdProducto(1);
        producto.setNombreProducto("Camisa");
        producto.setPrecio(19.99);
        producto.setCategoria("Ropa");
        producto.setStock(10);
        producto.setActivo(true);
    }

    @Test
    void testGetAll() throws Exception {
        when(productoService.readAll()).thenReturn(List.of(producto));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombreProducto", is("Camisa")));
    }

    @Test
    void testGetByIdFound() throws Exception {
        when(productoService.readById(1)).thenReturn(Optional.of(producto));

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto", is("Camisa")));
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(productoService.readById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreate() throws Exception {
        when(productoService.create(any(Productos.class))).thenReturn(1);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto", is("Camisa")));
    }

    @Test
    void testUpdateFound() throws Exception {
        when(productoService.readById(1)).thenReturn(Optional.of(producto));
        when(productoService.update(any(Productos.class))).thenReturn(producto);

        mockMvc.perform(put("/api/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto", is("Camisa")));
    }

    @Test
    void testUpdateNotFound() throws Exception {
        when(productoService.readById(1)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDelete() throws Exception {
        when(productoService.deleteById(1)).thenReturn("Producto desactivado.");

        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto desactivado."));
    }

    @Test
    void testGetByCategoria() throws Exception {
        when(productoService.findByCategoria("Ropa")).thenReturn(List.of(producto));

        mockMvc.perform(get("/api/productos/categoria/Ropa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoria", is("Ropa")));
    }

    @Test
    void testGetProductosConPrecioMenorA20() throws Exception {
        when(productoService.buscarProductosConPrecioMenorA(20.0)).thenReturn(List.of(producto));

        mockMvc.perform(get("/api/productos/precio-menos-de-20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].precio", lessThan(20.0)));
    }

    @Test
    void testGetProductosConStockBajo() throws Exception {
        when(productoService.obtenerProductosConStockMenorA5()).thenReturn(List.of());

        mockMvc.perform(get("/api/productos/getProductosConStockBajo"))
                .andExpect(status().isOk());
    }
}
