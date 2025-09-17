package co.edu.uniandes.dse.parcial1.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class UbicacionBodegaEntity extends BaseEntity{

    private Integer numeroEstante;
    private String canasta;
    private Integer pesoMaximo;

    @PodamExclude
    @OneToMany(mappedBy = "ubicacion")
    private List<MercanciaEntity> mercancias = new ArrayList<>();

   
   
    
}
