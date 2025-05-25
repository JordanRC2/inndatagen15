package ms.tiendagen15.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "detalles_pedido")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name= "id_detalle")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_producto")
   private Productos productos;
    private Boolean activo = true;

    public Object getProducto() {
        return null;
    }

    public void setProducto(Object producto) {
    }
}
