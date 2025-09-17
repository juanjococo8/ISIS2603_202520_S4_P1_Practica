package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MercanciaUbicacionBodegaService {

     @Autowired
    private MercanciaRepository mercanciaRepository;

   @Autowired
    private UbicacionBodegaRepository ubicacionRepository;

    @Transactional
     public void asociarMercanciaAUbicacion(Long mercanciaId, Long ubicacionId) throws EntityNotFoundException {
        MercanciaEntity mercancia = mercanciaRepository.findById(mercanciaId).orElse(null);
        if (mercancia == null) {
            throw new EntityNotFoundException("La mercancía no existe");
        }
        UbicacionBodegaEntity ubicacion = ubicacionRepository.findById(ubicacionId).orElse(null);
        if (ubicacion == null) {
            throw new EntityNotFoundException("La ubicación no existe");
        }
        mercancia.setUbicacion(ubicacion);
        mercanciaRepository.save(mercancia);

        if (ubicacion.getMercancias()==null){
            boolean yaAsociada = false;
            for (int i = 0; i < ubicacion.getMercancias().size(); i++) {
                MercanciaEntity m = ubicacion.getMercancias().get(i);
                if (m.getId() != null && m.getId().equals(mercancia.getId())) {
                    yaAsociada = true;
                    break;
                }
            }
            if (!yaAsociada) {
                ubicacion.getMercancias().add(mercancia);
            }
        }
        ubicacionRepository.save(ubicacion);
    }
}

