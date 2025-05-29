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
        return detalleService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> getById(@PathVariable Integer id) {
        return detalleService.readById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetallePedido create(@RequestBody DetallePedido d) {
        detalleService.create(d);
        return d;
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> update(@PathVariable Integer id, @RequestBody DetallePedido d) {
        return detalleService.readById(id).map(existing -> {
            d.setId(id);
            return ResponseEntity.ok(detalleService.update(d));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return detalleService.deleteById(id);
    }

    @GetMapping("/total/{id}")
    public Double calcularTotal(@PathVariable Integer id) {
        return detalleService.calcularTotalPorDetalle(id);
    }
}
