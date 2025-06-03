package ms.tiendagen15.model;

import ms.tiendagen15.entity.Productos;

public class ProductosDTO {
    private String nombreProducto;
    private Double precio;
    private String categoria;


    public ProductosDTO(Productos producto) {
        this.nombreProducto = producto.getNombreProducto();
        this.precio = producto.getPrecio();
        this.categoria = producto.getCategoria();
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
