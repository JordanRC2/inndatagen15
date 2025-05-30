package ms.tiendagen15.repository;

import ms.tiendagen15.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductosRepository extends JpaRepository<Productos,Integer> {
    List<Productos> findByPrecioLessThan(Double precio);

    List<Productos> findByCategoria(String categoria);
    @Query(value = "SELECT * FROM productos WHERE stock < 5 AND activo = true", nativeQuery = true)
    List<Productos> findProductosConStockBajo();

}
