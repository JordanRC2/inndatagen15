package ms.tiendagen15.repository;

import ms.tiendagen15.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductosRepository extends JpaRepository<Productos,Integer> {
    List<Productos> findByCategoria(String categoria);
}
