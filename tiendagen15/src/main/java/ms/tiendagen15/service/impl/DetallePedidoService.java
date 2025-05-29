package ms.tiendagen15.service.impl;

import ms.tiendagen15.entity.DetallePedido;
import ms.tiendagen15.repository.DetallePedidoRepository;
import ms.tiendagen15.service.IDetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DetallePedidoService implements IDetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public List<DetallePedido> ListaActivos() {
        return List.of();
    }

    @Override
    public Optional<DetallePedido> buscarPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public DetallePedido guaradar(DetallePedido detallePedido) {
        return null;
    }

    @Override
    public boolean borrarlogico(Integer id) {
        return false;
    }

    @Override
    public boolean borrarFisico(Integer id) {
        return false;
    }

    @Override
    public List<DetallePedido> listarActivos() {
        return List.of();
    }

    @Override
    public boolean borrarLogico(Integer id) {
        return false;
    }

    @Override
    public Object guardar(DetallePedido existente) {
        return null;
    }
}
