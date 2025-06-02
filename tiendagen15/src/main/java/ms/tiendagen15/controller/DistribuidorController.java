package ms.tiendagen15.controller;

import ms.tiendagen15.model.Distribuidor;
import ms.tiendagen15.service.impl.DistribuidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
public class DistribuidorController {

    @Autowired
    DistribuidorService distribuidorService;

    @GetMapping("/empresa")
    public List<Distribuidor> readAll() {
        return distribuidorService.readAll();
    }

    @GetMapping("/empresa/{id}")
    public Distribuidor readById(@PathVariable Integer id) {
        return distribuidorService.readById(id);
    }

    @PostMapping("/empresa")
    public Distribuidor create(@RequestBody Distribuidor distribuidor) {
        return distribuidorService.create(distribuidor);
    }

    @PutMapping("/empresa/{id}")
    public Distribuidor update(@PathVariable Integer id, @RequestBody Distribuidor distribuidor) {
        return distribuidorService.update(id, distribuidor);
    }

    @DeleteMapping("/empresa/{id}")
    public void delete(@PathVariable Integer id) {
        distribuidorService.delete(id);
    }
}

