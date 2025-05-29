package ms.tiendagen15.controller;

import ms.tiendagen15.entity.Productos;
import ms.tiendagen15.service.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
@CrossOrigin(origins = "*")
public class ProductosController {
    @Autowired
    private IProductosService productoService;

    @GetMapping
    public List<Productos> getAll() {
        return productoService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Productos> getById(@PathVariable Integer id) {
        return productoService.readById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Productos create(@RequestBody Productos p) {
        productoService.create(p);
        return p;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> update(@PathVariable Integer id, @RequestBody Productos p) {
        return productoService.readById(id).map(existing -> {
            p.setIdProducto(id);
            return ResponseEntity.ok(productoService.update(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return productoService.deleteById(id);
    }

    @GetMapping("/categoria/{cat}")
    public List<Productos> getByCategoria(@PathVariable String cat) {
        return productoService.findByCategoria(cat);
    }
}

