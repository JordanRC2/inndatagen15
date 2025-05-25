package ms.tiendagen15.repository;

import ms.tiendagen15.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetallePedidoRepository extends JpaRepository <DetallePedido, Integer> {
    List<DetallePedido> findByActivoTrue();
}
