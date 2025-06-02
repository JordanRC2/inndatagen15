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

    @Override
    public Distribuidor readById(Integer id) {
        return distribuidorClient.getById(id);
    }

    @Override
    public Distribuidor create(Distribuidor distribuidor) {
        return distribuidorClient.create(distribuidor);
    }

    @Override
    public Distribuidor update(Integer id, Distribuidor distribuidor) {
        return distribuidorClient.update(id, distribuidor);
    }

    @Override
    public void delete(Integer id) {
        distribuidorClient.delete(id);
    }
}

