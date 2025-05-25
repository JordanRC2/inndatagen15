package ms.tiendagen15.service;

import ms.tiendagen15.entity.Productos;

import java.util.List;
import java.util.Optional;

public interface IProductosService {
    List<Productos>ListaActivos();
    Optional<Productos>buscarPorId(Integer id);
    Productos guardar(Productos productos);
    boolean borradologico(Integer id);
    boolean borradoFisico(Integer id);
}
