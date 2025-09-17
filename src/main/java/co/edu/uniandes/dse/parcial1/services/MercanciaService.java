package co.edu.uniandes.dse.parcial1.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;
import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MercanciaService {

    @Autowired
    MercanciaRepository mercanciaRepository;

    @Transactional
    public MercanciaEntity createMercancia(MercanciaEntity mercancia) throws IllegalOperationException  {
        if (mercancia.getCodigoBarras()==null || mercancia.getCodigoBarras().isEmpty()) {
            throw new IllegalOperationException("No puede haber una mercancía sin código de barras");
        }
        if (mercancia.getNombre() == null || mercancia.getNombre().isEmpty()) {
            throw new IllegalOperationException("El nombre de la mercancía es obligatorio");
        }
        if (mercancia.getFechaRecepcion()==null || mercancia.getFechaRecepcion().isAfter(LocalDateTime.now())) {
            throw new IllegalOperationException("Debe haber una fecha de recepción y no puede ser posterior a hoy");
        }
        mercanciaRepository.save(mercancia);
        return mercancia;

    }
}