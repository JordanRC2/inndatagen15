package ms.tiendagen15.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import ms.tiendagen15.entity.Productos;
import ms.tiendagen15.service.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductosController {
    @Autowired
    private IProductosService service;

    @GetMapping
    public List<Productos> ListaActivos(){
        return service.ListaActivos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Productos>obtenerPorId(@PathVariable Integer id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Productos crear (@RequestBody Productos productos){
        return service.guardar(productos);
    }
    @PutMapping("/id")
    public ResponseEntity<Productos>actializar(@PathVariable Integer id,@RequestBody Productos p){
        return service.buscarPorId(id)
                .map(existente-> {
                    existente.setNombre(p.getNombre());
                    existente.setPrecio(p.gatPrecio());
                    return ResponseEntity.ok(service.guardar(existente));
        })
                .orElse(ResponseEntity.notFound().build());
        }
        @PutMapping("/borrar-logico/{id}")
    public ResponseEntity<String>borrarLogico(@PathVariable Integer id){
        if(service.borradologico(id)) {
            return ResponseEntity.ok("Borrado Logico exitoso");
        }
        return ResponseEntity.notFound().build();
        }
        @DeleteMapping("/borrar-fisico/{id}")
    public ResponseEntity<String>borrarFisico(@PathVariable Integer id){
        if(service.borradoFisico(id)) {
            return ResponseEntity.ok("Borado fisico exitoso");
        }
        return ResponseEntity.notFound().build();
        }

    }
