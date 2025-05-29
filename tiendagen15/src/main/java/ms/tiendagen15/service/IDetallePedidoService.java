package ms.tiendagen15.service;

import ms.tiendagen15.entity.DetallePedido;

import java.util.List;
import java.util.Optional;

public interface IDetallePedidoService {
    List<DetallePedido> readAll();
    Optional<DetallePedido> readById(Integer id);
    Integer create(DetallePedido detalle);
    DetallePedido update(DetallePedido detalle);
    String deleteById(Integer id);
    Double calcularTotalPorDetalle(Integer idDetalle);
}
