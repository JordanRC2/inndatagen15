package ms.tiendagen15.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Distribuidor {
    private Integer id;
    private Double costo;
    private String empresa;
    private String contacto;

}