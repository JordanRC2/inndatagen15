package ms.tiendagen15.controller;

import ms.tiendagen15.model.Distribuidor;
import ms.tiendagen15.service.impl.DistribuidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
public class DistribuidorController {

    @Autowired
    DistribuidorService distribuidorService;

    @GetMapping("/empresa")
    public ResponseEntity<List<Distribuidor>> readAll() {
        try {
            List<Distribuidor> distribuidores = distribuidorService.readAll();
            return ResponseEntity.ok(distribuidores);
        } catch (Exception e) {
            System.err.println("Error en GET /empresa: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/empresa/{id}")
    public ResponseEntity<Distribuidor> readById(@PathVariable Integer id) {
        try {
            Distribuidor distribuidor = distribuidorService.readById(id);
            if (distribuidor != null) {
                return ResponseEntity.ok(distribuidor);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Error en GET /empresa/" + id + ": " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/empresa")
    public ResponseEntity<Distribuidor> create(@RequestBody Distribuidor distribuidor) {
        try {
            Distribuidor nuevo = distribuidorService.create(distribuidor);
            return ResponseEntity.ok(nuevo);
        } catch (Exception e) {
            System.err.println("Error en POST /empresa: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/empresa/{id}")
    public ResponseEntity<Distribuidor> update(@PathVariable Integer id, @RequestBody Distribuidor distribuidor) {
        try {
            Distribuidor actualizado = distribuidorService.update(id, distribuidor);
            if (actualizado != null) {
                return ResponseEntity.ok(actualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Error en PUT /empresa/" + id + ": " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/empresa/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            distribuidorService.delete(id);
            return ResponseEntity.ok("Distribuidor eliminado con éxito");
        } catch (Exception e) {
            System.err.println("Error en DELETE /empresa/" + id + ": " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error al eliminar distribuidor");
        }
    }
}
