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
    private IProductosService service;

    @GetMapping
    public List<Productos> listarActivos() {
        return service.ListaActivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Productos> obtenerPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Productos crear(@RequestBody Productos productos) {
        return service.guardar(productos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizar(@PathVariable Integer id, @RequestBody Productos p) {
        return service.buscarPorId(id)
                .map(existente -> {
                    existente.setNombreProducto(p.getNombreProducto());
                    existente.setPrecio(p.getPrecio());
                    return ResponseEntity.ok(service.guardar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/borrar-logico/{id}")
    public ResponseEntity<String> borrarLogico(@PathVariable Integer id) {
        if (service.borradologico(id)) {
            return ResponseEntity.ok("Borrado lógico exitoso");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/borrar-fisico/{id}")
    public ResponseEntity<String> borrarFisico(@PathVariable Integer id) {
        if (service.borradoFisico(id)) {
            return ResponseEntity.ok("Borrado físico exitoso");
        }
        return ResponseEntity.notFound().build();
    }
}

