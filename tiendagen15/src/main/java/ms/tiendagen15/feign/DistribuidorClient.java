package ms.tiendagen15.feign;

import ms.tiendagen15.model.Distribuidor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="empresa", url = "https://683d2844199a0039e9e45b30.mockapi.io/api/v3")
public interface DistribuidorClient {

    @GetMapping("/empresa")
    List<Distribuidor> getData();

    @GetMapping("/empresa/{id}")
    Distribuidor getById(@PathVariable("id") Integer id);

    @PostMapping("/empresa")
    Distribuidor create(@RequestBody Distribuidor distribuidor);

    @PutMapping("/empresa/{id}")
    Distribuidor update(@PathVariable("id") Integer id, @RequestBody Distribuidor distribuidor);

    @DeleteMapping("/empresa/{id}")
    void delete(@PathVariable("id") Integer id);
}


