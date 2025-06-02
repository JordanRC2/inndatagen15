package ms.tiendagen15.controller;

import ms.tiendagen15.model.Distribuidor;
import ms.tiendagen15.service.impl.DistribuidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
public class DistribuidorController {
    @Autowired
    DistribuidorService distribuidorService;
    @GetMapping("/empresa")
    public List<Distribuidor>readAll(){
        return distribuidorService.readAll();
    }
}
