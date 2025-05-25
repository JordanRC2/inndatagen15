package ms.tiendagen15.service.impl;

import ms.tiendagen15.entity.Productos;
import ms.tiendagen15.repository.ProductosRepository;
import ms.tiendagen15.service.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosService implements IProductosService {


    @Override
    public List<Productos> ListaActivos() {
        return List.of();
    }

    @Override
    public Optional<Productos> buscarPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public Productos guardar(Productos productos) {
        return null;
    }

    @Override
    public boolean borradologico(Integer id) {
        return false;
    }

    @Override
    public boolean borradoFisico(Integer id) {
        return false;
    }

    @Autowired
    private ProductosRepository productosRepository;
}
