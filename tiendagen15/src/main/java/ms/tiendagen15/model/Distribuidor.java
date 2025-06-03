package ms.tiendagen15.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@Entity
public class Distribuidor {
    private Integer id;
    private Double costo;
    private String empresa;
    private String contacto;

}