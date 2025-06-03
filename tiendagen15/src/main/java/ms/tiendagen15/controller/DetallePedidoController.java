package ms.tiendagen15.controller;

import ms.tiendagen15.entity.DetallePedido;
import ms.tiendagen15.service.IDetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/detalles-pedido")
public class DetallePedidoController {
    @Autowired
    private IDetallePedidoService detalleService;

    @GetMapping
    public List<DetallePedido> getAll() {
        try {
            return detalleService.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Lista vacía si hay error
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> getById(@PathVariable Integer id) {
        try {
            return detalleService.readById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<DetallePedido> create(@RequestBody DetallePedido d) {
        try {
            detalleService.create(d);
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> update(@PathVariable Integer id, @RequestBody DetallePedido d) {
        try {
            return detalleService.readById(id).map(existing -> {
                d.setId(id);
                return ResponseEntity.ok(detalleService.update(d));
            }).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(detalleService.deleteById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al eliminar el detalle_de_pedido.");
        }
    }

    @GetMapping("/total/{id}")
    public ResponseEntity<Double> calcularTotal(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(detalleService.calcularTotalPorDetalle(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}