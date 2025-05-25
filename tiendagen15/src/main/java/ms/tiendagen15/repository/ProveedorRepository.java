package ms.tiendagen15.repository;

import ms.tiendagen15.entity.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedores, Integer> {
}
