package ms.tiendagen15.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //Contructor sin argumentos
@AllArgsConstructor //Constructor con todos los argumentos
@Data //getter y setter
@Entity
@Table(name = "proveedores")
public class Proveedores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    @Column(name ="contacto" )
    private String contacto;
    @Column(name ="correo" )
    private String correo;
    @Column(name = "telefono")
    private String telefono;


}
