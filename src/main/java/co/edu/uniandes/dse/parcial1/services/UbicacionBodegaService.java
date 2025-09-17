import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UbicacionBodegaService {

   @Autowired
    private UbicacionBodegaRepository ubicacionRepository;

    @Transactional
    public UbicacionBodegaEntity createUbicacion(UbicacionBodegaEntity ubicacion) throws IllegalOperationException {
        if (ubicacion==null){
            throw new IllegalOperationException("La ubicación no puede ser null");
        }
        if (ubicacion.getNumeroEstante()==null || ubicacion.getNumeroEstante() <=0){
            throw new IllegalOperationException("El número del estante debe ser positivo");
        }
        if (ubicacion.getCanasta()==null || ubicacion.getCanasta().isEmpty()){
            throw new IllegalOperationException("Debe haber una canasta obligatoriamente");
        }
        if (ubicacion.getPesoMaximo()==null || ubicacion.getPesoMaximo() <=0){
            throw new IllegalOperationException("El peso debe ser mayor a 0");
        }
        ubicacionRepository.save(ubicacion);
        return ubicacion;
    }
}
