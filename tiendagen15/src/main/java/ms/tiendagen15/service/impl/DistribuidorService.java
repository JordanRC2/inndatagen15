package ms.tiendagen15.service.impl;

import ms.tiendagen15.feign.DistribuidorClient;
import ms.tiendagen15.model.Distribuidor;
import ms.tiendagen15.service.IDistribuidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DistribuidorService implements IDistribuidorService {
    @Autowired
    DistribuidorClient distribuidorClient;
    @Override
    public List<Distribuidor> readAll() {
        return distribuidorClient.getData();
    }
}
