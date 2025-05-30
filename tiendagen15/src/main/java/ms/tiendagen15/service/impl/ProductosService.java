package ms.tiendagen15.service.impl;

import ms.tiendagen15.entity.Productos;
import ms.tiendagen15.repository.ProductosRepository;
import ms.tiendagen15.service.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosService implements IProductosService {
    @Autowired
    private ProductosRepository productoRepository;

    @Override
    public List<Productos> readAll() {
        return productoRepository.findAll()
                .stream()
                .filter(p -> Boolean.TRUE.equals(p.getActivo()))
                .toList();
    }

    @Override
    public Optional<Productos> readById(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public Integer create(Productos producto) {
        productoRepository.save(producto);
        return producto.getIdProducto();
    }

    @Override
    public Productos update(Productos producto) {
        return productoRepository.save(producto);
    }

    @Override
    public String deleteById(Integer id) {
        Optional<Productos> opt = productoRepository.findById(id);
        if (opt.isPresent()) {
            Productos p = opt.get();
            p.setActivo(false);
            productoRepository.save(p);
            return "Producto desactivado.";
        }
        return "Producto no encontrado.";
    }

    @Override
   public List<Productos> findByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    @Override
    public Integer sumarStock(Integer x, Integer y) {
        return x + y;
    }

    @Override
    public List<Productos> buscarProductosConPrecioMenorA(Double precio) {
        return productoRepository.findByPrecioLessThan(precio);
    }

    @Override
    public List<Productos> obtenerProductosConStockMenorA5() {
        return productoRepository.findProductosConStockBajo();
    }


}
