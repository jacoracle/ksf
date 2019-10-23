package com.kasu.service.impl;

import com.kasu.service.DetalleService;
import com.kasu.domain.Detalle;
import com.kasu.repository.DetalleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Detalle}.
 */
@Service
@Transactional
public class DetalleServiceImpl implements DetalleService {

    private final Logger log = LoggerFactory.getLogger(DetalleServiceImpl.class);

    private final DetalleRepository detalleRepository;

    public DetalleServiceImpl(DetalleRepository detalleRepository) {
        this.detalleRepository = detalleRepository;
    }

    /**
     * Save a detalle.
     *
     * @param detalle the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Detalle save(Detalle detalle) {
        log.debug("Request to save Detalle : {}", detalle);
        return detalleRepository.save(detalle);
    }

    /**
     * Get all the detalles.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Detalle> findAll() {
        log.debug("Request to get all Detalles");
        return detalleRepository.findAll();
    }


    /**
     * Get one detalle by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Detalle> findOne(Long id) {
        log.debug("Request to get Detalle : {}", id);
        return detalleRepository.findById(id);
    }

    /**
     * Delete the detalle by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Detalle : {}", id);
        detalleRepository.deleteById(id);
    }
}
