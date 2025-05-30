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
        List<ProductosDTO> productos = productoService.readAll().stream()
                .map(ProductosDTO::new)
                .collect(Collectors.toList());
        Collections.reverse(productos);
        return productos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosDTO> getById(@PathVariable Integer id) {
        return productoService.readById(id)
                .map(ProductosDTO::new)
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


    @GetMapping("/precio-menos-de-20")
    public List<Productos> getProductosConPrecioMenorA20() {
        return productoService.buscarProductosConPrecioMenorA(20.0);
    }
    public List<Productos>getProductosConStockBajo(){
        return productoService.obtenerProductosConStockMenorA5();
    }
}
