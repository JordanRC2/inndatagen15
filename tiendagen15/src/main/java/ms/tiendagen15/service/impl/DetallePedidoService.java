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
    public List<DetallePedido> readAll() {
        return detallePedidoRepository.findAll();
    }

    @Override
    public Optional<DetallePedido> readById(Integer id) {
        return detallePedidoRepository.findById(id);
    }

    @Override
    public Integer create(DetallePedido detalle) {
        detallePedidoRepository.save(detalle);
        return detalle.getId();
    }

    @Override
    public DetallePedido update(DetallePedido detalle) {
        return detallePedidoRepository.save(detalle);
    }

    @Override
    public String deleteById(Integer id) {
        Optional<DetallePedido> opt = detallePedidoRepository.findById(id);
        if (opt.isPresent()) {
            detallePedidoRepository.deleteById(id);
            return "Detalle eliminado.";
        }
        return "Detalle no encontrado.";
    }

    @Override
    public Double calcularTotalPorDetalle(Integer idDetalle) {
        return detallePedidoRepository.findById(idDetalle)
                .map(d -> d.getCantidad() * d.getProductos().getPrecio())
                .orElse(0.0);
    }

}
