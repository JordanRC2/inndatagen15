package ms.tiendagen15.service.impl;

import ms.tiendagen15.feign.DistribuidorClient;
import ms.tiendagen15.model.Distribuidor;
import ms.tiendagen15.service.IDistribuidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DistribuidorService implements IDistribuidorService {

    @Autowired
    DistribuidorClient distribuidorClient;

    @Override
    public List<Distribuidor> readAll() {
        try {
            return distribuidorClient.getData();
        } catch (Exception e) {
            System.err.println("Error al obtener todos los distribuidores: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Distribuidor readById(Integer id) {
        try {
            return distribuidorClient.getById(id);
        } catch (Exception e) {
            System.err.println("Error al obtener el distribuidor con ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public Distribuidor create(Distribuidor distribuidor) {
        try {
            return distribuidorClient.create(distribuidor);
        } catch (Exception e) {
            System.err.println("Error al crear distribuidor: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Distribuidor update(Integer id, Distribuidor distribuidor) {
        try {
            return distribuidorClient.update(id, distribuidor);
        } catch (Exception e) {
            System.err.println("Error al actualizar distribuidor con ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            distribuidorClient.delete(id);
        } catch (Exception e) {
            System.err.println("Error al eliminar distribuidor con ID " + id + ": " + e.getMessage());
        }
    }
}
