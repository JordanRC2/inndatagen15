package ms.tiendagen15.service;

import ms.tiendagen15.model.Distribuidor;

import java.util.List;

public interface IDistribuidorService {
    List<Distribuidor> readAll();
    Distribuidor readById(Integer id);
    Distribuidor create(Distribuidor distribuidor);
    Distribuidor update(Integer id, Distribuidor distribuidor);
    void delete(Integer id);
}

