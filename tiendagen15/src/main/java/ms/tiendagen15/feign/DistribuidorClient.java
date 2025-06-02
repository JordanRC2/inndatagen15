package ms.tiendagen15.feign;

import ms.tiendagen15.model.Distribuidor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name ="empresa", url = "https://683d2844199a0039e9e45b30.mockapi.io/api/v3")
public interface DistribuidorClient {
    @GetMapping("empresa")
    public List<Distribuidor>getData();
}
