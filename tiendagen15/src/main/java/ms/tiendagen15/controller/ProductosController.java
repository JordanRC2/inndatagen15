package ms.tiendagen15.controller;

import ms.tiendagen15.entity.Productos;
import ms.tiendagen15.model.ProductosDTO;
import ms.tiendagen15.service.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/productos")
@CrossOrigin(origins = "*")
public class ProductosController {

    @Autowired
    private IProductosService productoService;

    @GetMapping
    public List<ProductosDTO> getAll() {
        try {
            List<ProductosDTO> productos = productoService.readAll().stream()
                    .map(ProductosDTO::new)
                    .collect(Collectors.toList());
            Collections.reverse(productos);
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosDTO> getById(@PathVariable Integer id) {
        try {
            return productoService.readById(id)
                    .map(ProductosDTO::new)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Productos> create(@RequestBody Productos p) {
        try {
            productoService.create(p);
            return ResponseEntity.ok(p);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> update(@PathVariable Integer id, @RequestBody Productos p) {
        try {
            return productoService.readById(id).map(existing -> {
                p.setIdProducto(id);
                return ResponseEntity.ok(productoService.update(p));
            }).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(productoService.deleteById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al eliminar el producto.");
        }
    }

    @GetMapping("/categoria/{cat}")
    public ResponseEntity<List<Productos>> getByCategoria(@PathVariable String cat) {
        try {
            return ResponseEntity.ok(productoService.findByCategoria(cat));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Collections.emptyList());
        }
    }

    @GetMapping("/precio-menos-de-20")
    public ResponseEntity<List<Productos>> getProductosConPrecioMenorA20() {
        try {
            return ResponseEntity.ok(productoService.buscarProductosConPrecioMenorA(20.0));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Collections.emptyList());
        }
    }

    @GetMapping("/stock-bajo")
    public ResponseEntity<List<Productos>> getProductosConStockBajo() {
        try {
            return ResponseEntity.ok(productoService.obtenerProductosConStockMenorA5());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Collections.emptyList());
        }
    }
}
