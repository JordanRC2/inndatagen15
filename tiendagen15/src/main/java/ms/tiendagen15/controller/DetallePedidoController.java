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
    private IDetallePedidoService service;

    @GetMapping
    public List<DetallePedido> listarActivos() {
        return service.listarActivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> obtenerPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody DetallePedido detalle) {
        try {
            return ResponseEntity.ok(service.guardar(detalle));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody DetallePedido dp) {
        return service.buscarPorId(id)
                .map(existente -> {
                    existente.setProducto(dp.getProducto());
                    return ResponseEntity.ok(service.guardar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/borrar-logico/{id}")
    public ResponseEntity<String> borrarLogico(@PathVariable Integer id) {
        if (service.borrarLogico(id)) return ResponseEntity.ok("Borrado lógico exitoso");
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/borrar-fisico/{id}")
    public ResponseEntity<String> borrarFisico(@PathVariable Integer id) {
        if (service.borrarFisico(id)) return ResponseEntity.ok("Borrado físico exitoso");
        return ResponseEntity.notFound().build();
    }
}
