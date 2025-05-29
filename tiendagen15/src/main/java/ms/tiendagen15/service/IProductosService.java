package ms.tiendagen15.service;

import ms.tiendagen15.entity.Productos;

import java.util.List;
import java.util.Optional;

public interface IProductosService {
    List<Productos> readAll();
    Optional<Productos> readById(Integer id);
    Integer create(Productos producto);
    Productos update(Productos producto);
    String deleteById(Integer id);
    List<Productos> findByCategoria(String categoria);
    Integer sumarStock(Integer x, Integer y);
}
