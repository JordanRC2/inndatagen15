package ms.tiendagen15.service;

import ms.tiendagen15.entity.DetallePedido;

import java.util.List;
import java.util.Optional;

public interface IDetallePedidoService {
    List<DetallePedido> ListaActivos();
    Optional<DetallePedido>buscarPorId(Integer id);
    DetallePedido guaradar (DetallePedido detallePedido);
    boolean borrarlogico(Integer id);
    boolean borrarFisico(Integer id);

    List<DetallePedido> listarActivos();

    boolean borrarLogico(Integer id);

    Object guardar(DetallePedido existente);
}
